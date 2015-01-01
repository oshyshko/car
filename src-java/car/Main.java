package car;

import shared.Net;
import shared.Udp;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Map;

import static shared.IO.println;

public class Main {

    public static final String BROADCAST_IP = "192.168.88.255"; // TODO get via API

    public static void main(String[] args) throws IOException {
        Udp.DEBUG = false;

        // TODO kill self on ssh drop?

        Net net = new Net("car", BROADCAST_IP);

        // command line interface
        byte id = 0;
        println("Started. Type 'q' or 'quit' to quit.");
        println("Available commands: (q)uit, (s)elf, (p)eers");
        println();
        for (String line : lines(System.in)) {
            switch (line) {
                case "quit":
                case "q":
                    println("Quiting.");
                    net.close();
                    return;

                case "self":
                case "s":
                    net.controller_update(new InetSocketAddress("127.0.0.1", Net.PORT), id++, (byte) 0, (byte) 0);
                    break;

                case "peers":
                case "p":
                    for (Map.Entry<InetSocketAddress, String> kv : net.peer2name().entrySet())
                        println(kv.getKey() + " -- '" + kv.getValue() + "'");
                    break;

                default:
                    println("Unknown command: " + line);
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
