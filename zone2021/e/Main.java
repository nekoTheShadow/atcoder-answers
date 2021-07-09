package zone2021.e;
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
import java.util.PriorityQueue;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        int r = stdin.nextInt();
        int c = stdin.nextInt();
        long[][] a = new long[r][];
        long[][] b = new long[r][];
        for (int i = 0; i < r; i++) a[i] = stdin.nextLongArray(c-1);
        for (int i = 0; i < r-1; i++) b[i] = stdin.nextLongArray(c);
        
        long[][] d = new long[r][c];
        for (long[] row : d) Arrays.fill(row, Long.MAX_VALUE/2-1);
        d[0][0] = 0;
        
        PriorityQueue<Tuple> q = new PriorityQueue<>();
        q.add(new Tuple(0, 0, 0));
        while (!q.isEmpty()) {
            Tuple cur = q.remove();
            
            if (cur.x==r-1 && cur.y==c-1) break;
            if (d[cur.x][cur.y] < cur.cost) continue;
            
            if (cur.y+1<c && cur.cost+a[cur.x][cur.y]<d[cur.x][cur.y+1]) {
                d[cur.x][cur.y+1] = cur.cost+a[cur.x][cur.y];
                q.add(new Tuple(cur.x, cur.y+1, cur.cost+a[cur.x][cur.y]));
            }
            if (cur.y-1>=0 && cur.cost+a[cur.x][cur.y-1]<d[cur.x][cur.y-1]) {
                d[cur.x][cur.y-1] = cur.cost+a[cur.x][cur.y-1];
                q.add(new Tuple(cur.x, cur.y-1, cur.cost+a[cur.x][cur.y-1]));
            }
            if (cur.x+1<r && cur.cost+b[cur.x][cur.y]<d[cur.x+1][cur.y]) {
                d[cur.x+1][cur.y] = cur.cost+b[cur.x][cur.y];
                q.add(new Tuple(cur.x+1, cur.y, cur.cost+b[cur.x][cur.y]));
            }
            for (int i = 1; i <=cur.x; i++) {
                if (cur.cost+i+1 < d[cur.x-i][cur.y]) {
                    d[cur.x-i][cur.y] = cur.cost+i+1;
                    q.add(new Tuple(cur.x-i, cur.y, cur.cost+i+1));
                }
            }
        }
        stdout.println(d[r-1][c-1]);
    }
    
    private class Tuple implements Comparable<Tuple>{
        private int x;
        private int y;
        private long cost;

        public Tuple(int x, int y, long cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Tuple other) {
            return Long.compare(this.cost, other.cost);
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