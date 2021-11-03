import java.util.LinkedList;
import java.util.List;

public class MovingAverage {
    private final List<Integer> window;
    private final int size;
    private double average;

    public MovingAverage(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("size must be above one!");
        }
        window = new LinkedList<>();
        this.size = size;
    }

    public double getAverage(int n) {
        if (window.size() < size) {
            average *= window.size();
        } else {
            average *= window.size();
            average -= window.remove(0);
        }
        average += n;
        window.add(n);

        average /= window.size();
        return average;
    }
}
