package car;

import shared.Network;
import shared.Udp;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Iterator;

import static shared.IO.println;

public class Main {
    public static void main(String[] args) throws IOException {
        Udp.DEBUG = true;

        // TODO kill self on ssh drop?
        // TODO broadcast own IP (every 2 seconds) + maintain a list of cars on controller

        Network car = new Network();

        // command line interface
        byte id = 0;
        println("Started. Type 'q' or 'quit' to quit.");
        for (String line : lines(System.in)) {
            switch (line) {
                case "quit":
                case "q":
                    System.out.println("Quiting.");
                    car.close();
                    return;

                case "self":
                case "s":
                    car.controller_update(new InetSocketAddress("127.0.0.1", Network.PORT), id++, (byte) 0, (byte) 0);
                    break;

                default:
                    println(line);
                    break;
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
