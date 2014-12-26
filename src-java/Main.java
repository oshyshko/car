import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Started. Type 'q' or 'quit' to quit.");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String line = in.readLine();

                if ("quit".equals(line) || "q".equals(line)) {
                    System.out.println("Quiting.");
                    System.exit(0);
                } else {
                    System.out.println(line);
                }
            }
        }
    }
}
