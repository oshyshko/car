package car;

import util.Gpio;

import java.io.Closeable;
import java.io.IOException;

public class Driver implements Closeable {
    public static final int L = 2;
    public static final int R = 3;
    public static final int F = 14;
    public static final int B = 15;

    private final Gpio gpio;

    public Driver() {
        gpio = new Gpio();
        gpio.export(L, R, F, B);
        gpio.direction(Gpio.Direction.OUT, L, R, F, B);
    }

    public void close() throws IOException {
        gpio.close();
    }

    public void update(int steering, int speed) {
        gpio.write(steering < 0 ? Gpio.Value.HIGH : Gpio.Value.LOW, L);
        gpio.write(steering > 0 ? Gpio.Value.HIGH : Gpio.Value.LOW, R);
        gpio.write(speed > 0    ? Gpio.Value.HIGH : Gpio.Value.LOW, F);
        gpio.write(speed < 0    ? Gpio.Value.HIGH : Gpio.Value.LOW, B);
    }
}
