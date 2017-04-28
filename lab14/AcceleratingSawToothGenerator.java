import lab14lib.Generator;

import java.lang.management.GarbageCollectorMXBean;

/**
 * Created by Hau on 4/28/17.
 */
public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double factor;
    boolean reset = false;

    public AcceleratingSawToothGenerator(int period, double factor) {
        this.factor = factor;
        this.period = period;
        state = 0;
    }

    private double normalize(int state) {
        double turn = period / 2;
        return (state - turn) / turn;
    }

    public double next() {
        state++;
        state = state % (period - 1);
        if (state == 1 && !reset) {
            period = (int) (period * factor);
        } else {
            reset = false;
        }
        return normalize(state);
    }
}
