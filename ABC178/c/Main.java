package c;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public void exec() {
        long n = stdin.nextLong();
        long mod = 1000000000 + 7;
        long ans = MathUtils.modPow(10, n, mod);

        ans += MathUtils.modPow(8, n, mod);
        ans %= mod;

        ans -= MathUtils.modPow(9, n, mod);
        if (ans < 0) ans += mod;

        ans -= MathUtils.modPow(9, n, mod);
        if (ans < 0) ans += mod;

        stdout.println(ans);
    }

    public static class  MathUtils {
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

        public static long pow(long x, long y) {
            long z = 1;
            while (y > 0) {
                if (y % 2 == 0) {
                    x = (x * x);
                    y /= 2;
                } else {
                    z = (z * x);
                    y--;
                }
            }
            return z;
        }

        public static Map<Integer, Integer> primeDivision(int n) {
            Map<Integer, Integer> d = new HashMap<>();
            for (int k = 2; k*k <= n; k++) {
                while (n % k == 0) {
                    d.put(k, d.getOrDefault(k, 0) + 1);
                    n  /= k;
                }
            }

            if (n > 1) {
                d.put(n, d.getOrDefault(n, 0) + 1);
            }

            return d;
        }

        public static int gcd(int x, int y) {
            if (x < y) {
                int tmp = x;
                x = y;
                y= tmp;
            }

            while (y > 0) {
                int mod = x % y;
                x = y;
                y = mod;
            }

            return x;
        }

    }



    private static final Stdin stdin = new Stdin();
    private static final Stdout stdout = new Stdout();

    public static void main(String[] args) {
        try {
            new Main().exec();
        } finally {
            stdout.flush();
        }
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
                    if (line == null) {
                        throw new UncheckedIOException(new EOFException());
                    }
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
            String line = String.format(format, args);
            if (line.endsWith(System.lineSeparator())) {
                stdout.print(line);
            } else {
                stdout.println(line);
            }
        }

        public void println(Object ... objs) {
            String line = Arrays.stream(objs).map(Objects::toString).collect(Collectors.joining(" "));
            stdout.println(line);
        }

        public void printDebug(Object ... objs) {
            String line = Arrays.stream(objs).map(this::deepToString).collect(Collectors.joining(" "));
            stdout.printf("DEBUG: %s%n", line);
            stdout.flush();
        }

        private String deepToString(Object o) {
            if (o == null) {
                return "null";
            }

            // 配列の場合
            if (o.getClass().isArray()) {
                int len = Array.getLength(o);
                String[] tokens = new String[len];
                for (int i = 0; i < len; i++) {
                    tokens[i] = deepToString(Array.get(o, i));
                }
                return "{" + String.join(",", tokens) + "}";
            }

            return Objects.toString(o);
        }

        private void flush() {
            stdout.flush();
        }
    }
}