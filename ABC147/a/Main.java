package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

public class Main {
    public void solve(Stdin stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int a1 = stdin.nextInt();
        int a2 = stdin.nextInt();
        int a3 = stdin.nextInt();
        if (a1 + a2 + a3 <= 21) {
            stdout.println("win");
        } else {
            stdout.println("bust");
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }

    public static class Stdin {
        private Deque<String> queue;
        private BufferedReader stdin;
        private Pattern delimiter;

        public Stdin() {
            queue = new ArrayDeque<>();
            stdin = new BufferedReader(new InputStreamReader(System.in));
            delimiter = Pattern.compile(" ");
        }

        public String next() throws IOException {
            if (queue.isEmpty()) {
                delimiter.splitAsStream(stdin.readLine()).forEach(queue::addLast);
            }
            return queue.removeFirst();
        }

        public int nextInt() throws NumberFormatException, IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws NumberFormatException, IOException {
            return Long.parseLong(next());
        }
    }
}
