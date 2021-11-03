import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovingAverageTest extends Assertions {
    @Test
    public void testConstruct() {
        assertThrows(IllegalArgumentException.class, () -> new MovingAverage(-1));
    }

    @Test
    public void testGetAverage() {
        MovingAverage average = new MovingAverage(5);
        double a = average.getAverage(1);
        assertEquals(1.0, a);
        a = average.getAverage(2);
        assertEquals(1.5, a);
        a = average.getAverage(3);
        assertEquals(2.0, a);
        a = average.getAverage(6);
        assertEquals(3.0, a);
        a = average.getAverage(3);
        assertEquals(3.0, a);

        a = average.getAverage(6);
        assertEquals(4.0, a);
    }
    @Test
    public void testGetAverage1() {
        MovingAverage average = new MovingAverage(3);
        double a = average.getAverage(1);
        assertEquals(1.0, a);
        a = average.getAverage(2);
        assertEquals(1.5, a);
        a = average.getAverage(3);
        assertEquals(2.0, a);
        a = average.getAverage(4);
        assertEquals(3.0, a);
    }
}
