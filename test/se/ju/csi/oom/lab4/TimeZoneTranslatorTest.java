package se.ju.csi.oom.lab4;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TimeZoneTranslatorTest {

	private DateTime testDateTime;
	private Event testEvent;
	
	@Before
	public void setUp() throws Exception {
		testDateTime = new DateTime(2020, 1, 1, 0, 0, 0);
		
		Set<Participant> participants = new HashSet<Participant>();
		participants.add(new Person("Adam"));
		participants.add(new Person("Johannes"));
		
		testEvent = new Event(
				"Lab re-examination",
				new DateTime("2019-12-31 23:00:00"), // start date
				new DateTime("2020-01-02 00:00:00"), 
				participants,
				new Place("Jönköping University", 50.0, 50.0, 10.0)
		);
	}

	@Test
	public void testShiftTimeZone() {
		DateTime shiftedDateTime = TimeZoneTranslator.shiftTimeZone(testDateTime, 0, 5);
		assertEquals("2020-01-01 05:00:00", shiftedDateTime.toString());
	}

	@Test
	public void testShiftEventTimeZone() {
		Event shiftedEvent = TimeZoneTranslator.shiftEventTimeZone(testEvent, TimeZone.CENTRAL_EUROPEAN_TIME, TimeZone.EASTERN_EUROPEAN_TIME);
		assertEquals("2020-01-01 00:00:00", shiftedEvent.getStartDate().toString());
	}

}
