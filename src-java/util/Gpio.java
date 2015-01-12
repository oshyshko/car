package util;

import shared.Errors;
import shared.Threads;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

public class Gpio implements Closeable {
    private HashSet<Integer> exported = new HashSet<>();

    public static void main(String[] args) throws IOException {
        try (Gpio g = new Gpio()) {
            g.export(13, 10, 15, 17);
            g.mode(Direction.OUT, 13, 10, 15, 17);

            while (true) {
                g.write(Value.LOW, 13, 10, 15, 17);
                Threads.sleep(1000);
                g.write(Value.HIGH, 13, 10, 15, 17);
            }
        }
    }

    public static enum Direction {
        IN("in"),
        OUT("out");

        private String value;
        private Direction(String value) { this.value = value; }
    }

    public static enum Value {
        LOW("0"),
        HIGH("1");

        private String value;
        private Value(String value) { this.value = value; }
    }

    public void close() throws IOException {
        for (Integer v : exported)
            unexport(v);
    }

    public void export(int port) {
        spit("/sys/class/gpio/export", "" + port);
        exported.add(port);
    }

    public void unexport(int port) throws IOException {
        spit("/sys/class/gpio/unexport", "" + port);
    }

    public void direction(Direction d, int port) {
        spit("/sys/class/gpio/gpio" + port + "/direction", d.value);
    }

    public void write(Value v, int port) {
        spit("/sys/class/gpio/gpio" + port + "/value", v.value);
    }

    // varargs
    public void export(int... ports)                 { for (int i : ports) export(i); }
    public void mode(Direction m, int... ports)      { for (int i : ports) direction(m, i); }
    public void write(Value v, int... ports)         { for (int i : ports) write(v, i); }
    public void direction(Direction d, int... ports) { for (int i : ports) direction(d, i); }

    // utils
    private void spit(String path, String value) {
        try {
            try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(value.getBytes());
            }
        } catch (IOException e) {
            Errors.die(e);
        }
    }
}
