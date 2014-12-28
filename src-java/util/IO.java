package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IO {
    public static void println(String s) {
        System.out.println(s);
    }

    public static Iterable<String> lines(InputStream is) {
        return () -> new BufferedReader(new InputStreamReader(is)).lines().iterator();
    }
}
