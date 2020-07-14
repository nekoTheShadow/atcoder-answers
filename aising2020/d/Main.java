package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        String x = stdin.nextString();

        long c = IntStream.range(0, n).filter(i -> x.charAt(i) == '1').count();
        if (c == 0) {
            for (int i = 0; i < n; i++) stdout.println(1);
            return ;
        }


        long p1 = 0;
        long p2 = 0;
        for (int i = 0; i < n; i++) {
            if (x.charAt(i) == '1') {
                p1 += MathUtils.modPow(2, n-i-1, c+1);
                p1 %= c+1;
                if (c != 1) {
                    p2 += MathUtils.modPow(2, n-i-1, c-1);
                    p2 %= c-1;
                }

            }
        }


        for (int i = 0; i < n; i++) {
            if (x.charAt(i) == '1') {
                if (c == 1) {
                    stdout.println(0);
                } else {
                    long q = p2 - MathUtils.modPow(2, n-i-1, c-1);
                    if (q < 0) q += c-1;
                    stdout.println(f(q));
                }
            } else {
                long q = p1 + MathUtils.modPow(2, n-i-1, c+1);
                q %= c+1;
                stdout.println(f(q));
            }
        }
    }

    public long f(long n) {
        if (n==0) {
            return 1;
        }
        return f(n%Long.bitCount(n))+1;
    }

    private static class MathUtils {
        private MathUtils() {}

        public static long modPow(long x, long y, long mod) {
            long z = 1;
            while (y > 0) {
                if (y % 2 == 0) {
                    x = (x * x) % mod;
                    y /= 2;
                } else {
                   z = (z * x) % mod;
                    y--;
                }
            }
            return z;
        }

        public static long modInv(long x, long mod) {
            return modPow(x, mod - 2, mod);
        }
    }

    private static final Stdin stdin = new Stdin();
    private static final Stdout stdout = new Stdout();

    public static void main(String[] args) {
        new Main().exec();
        stdout.flush();
    }

    public static class Stdin {
        private BufferedReader stdin;
        private Deque<String> tokens;
        private Pattern delim;

        public Stdin() {
            stdin = new BufferedReader(new InputStreamReader(System.in));
            tokens = new ArrayDeque<>();
            delim = Pattern.compile(" ");
        }

        public String nextString() {
            try {
                if (tokens.isEmpty()) {
                    String line = stdin.readLine();
                    delim.splitAsStream(line).forEach(tokens::addLast);
                }
                return tokens.pollFirst();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
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

        public String[] nextStringArray(int n) {
            String[] a = new String[n];
            for (int i = 0; i < n; i++) a[i] = nextString();
            return a;
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        public double[] nextDoubleArray(int n) {
            double[] a = new double[n];
            for (int i = 0; i < n; i++) a[i] = nextDouble();
            return a;
        }

        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }
    }

    public static class Stdout {
        private PrintWriter stdout;

        public Stdout() {
            stdout =  new PrintWriter(System.out, false);
        }

        public void printf(String format, Object ... args) {
            stdout.printf(format, args);
        }

        public void println(Object ... objs) {
            String line = Arrays.stream(objs).map(this::deepToString).collect(Collectors.joining(" "));
            stdout.println(line);
        }

        private String deepToString(Object o) {
            if (o == null || !o.getClass().isArray()) {
                return Objects.toString(o);
            }

            int len = Array.getLength(o);
            String[] tokens = new String[len];
            for (int i = 0; i < len; i++) {
                tokens[i] = deepToString(Array.get(o, i));
            }
            return "{" + String.join(",", tokens) + "}";
        }

        public void flush() {
            stdout.flush();
        }
    }
}