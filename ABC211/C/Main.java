package ABC211.C;
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
import java.util.Deque;
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        String s = stdin.nextString();

        long[] d = new long[8];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='c') d[0] += 1;
            if (s.charAt(i)=='h') d[1] += d[0];
            if (s.charAt(i)=='o') d[2] += d[1];
            if (s.charAt(i)=='k') d[3] += d[2];
            if (s.charAt(i)=='u') d[4] += d[3];
            if (s.charAt(i)=='d') d[5] += d[4];
            if (s.charAt(i)=='a') d[6] += d[5];
            if (s.charAt(i)=='i') d[7] += d[6];

            d[0] %= 1000000000 + 7;
            d[1] %= 1000000000 + 7;
            d[2] %= 1000000000 + 7;
            d[3] %= 1000000000 + 7;
            d[4] %= 1000000000 + 7;
            d[5] %= 1000000000 + 7;
            d[6] %= 1000000000 + 7;
            d[7] %= 1000000000 + 7;
        }

        stdout.println(d[7]);
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