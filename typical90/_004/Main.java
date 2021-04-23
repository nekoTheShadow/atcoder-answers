package typical90._004;
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
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public void exec() {
        int h = stdin.nextInt();
        int w = stdin.nextInt();
        long[][] a  = new long[h][w];
        for (int i = 0; i < h; i++) {
            a[i] = stdin.nextLongArray(w);
        }
        
        long[] p = IntStream.range(0, h).mapToLong(i -> Arrays.stream(a[i]).sum()).toArray();
        long[] q = IntStream.range(0, w).mapToLong(j -> IntStream.range(0, h).mapToLong(i -> a[i][j]).sum()).toArray();
        
        long[][] b = new long[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                b[i][j] = p[i]+q[j]-a[i][j];
            }
        }
        for (int i = 0; i < h; i++) {
            stdout.println(Arrays.stream(b[i]).mapToObj(Long::toString).collect(Collectors.joining(" ")));
        }
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
        
        public BigInteger[] nexBigIntegerArray(int n) {
            BigInteger[] a = new BigInteger[n];
            for (int i = 0; i < n; i++) a[i] = nextBigInteger();
            return a;
        }
        
        public List<Integer> nextIntegerList(int n) {
            return nextList(n, this::nextInt);
        }
        
        public List<Long> nextLongList(int n) {
            return nextList(n, this::nextLong);
        }
        
        public List<Double> nextDoubleList(int n) {
            return nextList(n, this::nextDouble);
        }
        
        public List<String> nextStringList(int n) {
            return nextList(n, this::nextString);
        }
        
        public List<BigInteger> nextBigIntegerList(int n) {
            return nextList(n, this::nextBigInteger);
        }
        
        private <T> List<T> nextList(int n, Supplier<T> supplier) {
            List<T> a = new ArrayList<>();
            for (int i = 0; i < n; i++) a.add(supplier.get());
            return a;
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