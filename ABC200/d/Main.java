package ABC200.d;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = stdin.nextInt();

        int len = Math.min(8, a.length);

        Map<Integer, List<List<Integer>>> d = new HashMap<>();
        for (int bit = 1; bit < 1<<len; bit++) {
            int sum = 0;
            List<Integer> lst = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if ((bit & (1<<i)) != 0) {
                    sum += a[i];
                    sum %= 200;
                    lst.add(i+1);
                }
            }

            if (!d.containsKey(sum)) d.put(sum, new ArrayList<>());
            d.get(sum).add(lst);
        }

        for (int k : d.keySet()) {
            if (d.get(k).size()<2) {
                continue;
            }
            stdout.println("Yes");
            stdout.println(d.get(k).get(0).size(), d.get(k).get(0).stream().map(Objects::toString).collect(Collectors.joining(" ")));
            stdout.println(d.get(k).get(1).size(), d.get(k).get(1).stream().map(Objects::toString).collect(Collectors.joining(" ")));
            return ;
        }
        stdout.println("No");
    }

    private static final Stdin stdin = new Stdin(System.in);
    private static final Stdout stdout = new Stdout(System.out);

    public static void main(String[] args) {
        try {
            new Main().exec();
        } finally {
            stdout.flush();
        }
    }

    public static class Stdin {
        private Deque<String> queue;
        private BufferedReader in;
        private Pattern space;

        public Stdin(InputStream in) {
            this.queue = new ArrayDeque<>();
            this.in = new BufferedReader(new InputStreamReader(in));
            this.space = Pattern.compile(" ");
        }

        public String nextString() {
            if (queue.isEmpty()) {
                try {
                    String line = in.readLine();
                    if (line == null) {
                        throw new EOFException();
                    }
                    space.splitAsStream(line).forEach(this.queue::addLast);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
            return queue.removeFirst();
        }

        public int nextInt() {
            return Integer.parseInt(nextString());
        }

        public double nextDouble() {
            return Double.parseDouble(nextString());
        }

        public long nextLong() {
            return Long.parseLong(nextString());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(nextString());
        }
    }

    public static class Stdout {
        private PrintWriter stdout;

        public Stdout(PrintStream stdout) {
            this.stdout =  new PrintWriter(stdout, false);
        }

        public void println(Object ... objs) {
            for (int i = 0, len = objs.length; i < len; i++) {
                stdout.print(objs[i]);
                if (i != len-1) stdout.print(' ');
            }
            stdout.println();
        }

        public void flush() {
            stdout.flush();
        }
    }
}