package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

public class Main {
    public void solve(Stdin stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        String s = stdin.next();
        int ans = 0;
        for (int i = 0, len = s.length(); i < len / 2; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(len - i - 1);
            if (c1 != c2) {
                ans++;
            }
        }
        stdout.println(ans);
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
