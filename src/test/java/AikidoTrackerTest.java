import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AikidoTrackerTest {
    AikidoTracker tracker;

    @BeforeEach
    public void setUp() {
        tracker = new AikidoTracker(
                "Test Trainee",
                LocalDate.now());
    }

    @Test
    public void getStudentName(){
        Assertions.assertEquals("Test Trainee", tracker.getStudentName());
    }

    @Test
    public void getStartDate(){
        Assertions.assertEquals(LocalDate.now(), tracker.getStartDate());
    }

    @Test
    public void sessionCalculations(){
        Assertions.assertEquals(0, tracker.getSessions());
        tracker.incrementSessions();
        tracker.incrementSessions();
        tracker.incrementSessions();
        Assertions.assertEquals(3, tracker.getSessions());
        tracker.incrementSessions();
        tracker.incrementSessions();
        Assertions.assertEquals(5, tracker.getSessions());
    }

    @Test
    public void kyuEligibility(){
        Assertions.assertFalse(tracker.checkKyuEligibility());
        for (int i = 0; i < 60; i++){
            tracker.incrementSessions();
        }

        Assertions.assertEquals(60, tracker.getSessions());
        Assertions.assertTrue(tracker.checkKyuEligibility());
    }
}
