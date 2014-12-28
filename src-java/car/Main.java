package car;

import shared.Network;
import shared.Udp;

import java.io.*;
import java.util.Iterator;

import static shared.IO.println;

public class Main {
    public static void main(String[] args) throws IOException {
        Udp.DEBUG = true;

        // TODO kill self on ssh drop?
        // TODO broadcast own IP (evey 2 seconds) + maintain a list of cars on controller

        Closeable network = Network.Car.start();

        // command line interface
        println("Started. Type 'q' or 'quit' to quit.");
        for (String line : lines(System.in)) {
            if ("quit".equals(line) || "q".equals(line)) {
                System.out.println("Quiting.");
                network.close();
                return;
            } else {
                println(line);
            }
        }
    }
    public static Iterable<String> lines(final InputStream is) {
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new BufferedReader(new InputStreamReader(is)).lines().iterator();
            }
        };
    }
}
