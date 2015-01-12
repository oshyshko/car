package car;

import shared.Errors;
import shared.Net;
import shared.Udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;

import static shared.IO.println;

public class Main {

    public static void main(String[] args) throws IOException {
        Udp.DEBUG = false;

        // TODO kill self on ssh drop?

        Map<InetAddress, InetAddress> addresses2broadcasts = Udp.address2broadcast();
        println("My addresses + broadcasts are: " + addresses2broadcasts);
        println();

        InetAddress self = null;
        InetAddress broadcast = null;

        for (Map.Entry<InetAddress, InetAddress> kv : addresses2broadcasts.entrySet()) {
            if (kv.getKey().equals(InetAddress.getByName("192.168.21.21"))) {
                self = kv.getKey();
                broadcast = kv.getValue();
            }
        }

        if (self == null)
            Errors.die("Can't find preset 192.168.21.21 among " + addresses2broadcasts + " (TODO implement selection)");


        try (Driver d = new Driver()) {
            Net net = new Net(Net.KIND_CAR, self, broadcast) {
                protected void all_onShout(InetAddress from) {
                    // do nothing
                }

                protected void car_onUpdate(InetAddress from, byte id, byte steering, byte speed) {
                    d.update(steering, speed);
                    car_updateReceived(from, id);
                }

                protected void controller_onUpdateConfirm(InetAddress from, byte id, long ping) {
                    println("~ " + ping);
                }
            };

            // command line interface
            byte id = 1;

            println("Available commands: (q)uit");
            println("                    (s)elf update");
            println("                    (p)eers list");
            println("                    (d)ebug on/off");
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
                        net.controller_update(self, id++, (byte) 0, (byte) 0);
                        break;

                    case "peers":
                    case "p":
                        for (Map.Entry<InetAddress, Byte> kv : net.peer2kind().entrySet())
                            println(kv.getKey() + " -- of kind " + kv.getValue() +
                                    (net.isSelf(kv.getKey()) ? " (self)" : ""));
                        break;

                    case "debug":
                    case "d":
                        println("Debugging is now " + (Udp.DEBUG ? "OFF" : "ON"));

                        Udp.DEBUG = !Udp.DEBUG;
                        break;

                    default:
                        println("Unknown command: " + line);
                        break;
                }
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
