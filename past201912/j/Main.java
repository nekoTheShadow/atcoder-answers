package past201912.j;
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
    
    int h;
    int w;
    long[][] a;
    int[][] diffs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public void exec() {
        h = stdin.nextInt();
        w = stdin.nextInt();
        a = new long[h][];
        for (int i = 0; i < h; i++) a[i] = stdin.nextLongArray(w);
        
        long[][] cost1 = dijkstra(h-1, 0);
        long[][] cost2 = dijkstra(h-1, w-1);
        long[][] cost3 = dijkstra(0, w-1);
        
        long ans = Long.MAX_VALUE;
        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                ans = Math.min(ans, cost1[x][y]+cost2[x][y]+cost3[x][y]-a[x][y]*2);
            }
        }
        stdout.println(ans);
        
    }
    
    private long[][] dijkstra(int sx, int sy) {
        long[][] score = new long[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(score[i], Long.MAX_VALUE);
        }
        score[sx][sy]=0;
        
        PriorityQueue<Tuple> q = new PriorityQueue<>();
        q.add(new Tuple(new int[] {sx, sy}, 0));
        while (!q.isEmpty()) {
            Tuple t = q.remove();
            if (score[t.current[0]][t.current[1]] < t.cost) continue;
            
            for (int[] diff : diffs) {
                int x = t.current[0];
                int y = t.current[1];
                int nx = x + diff[0];
                int ny = y + diff[1];
                if (0<=nx && nx<h && 0<=ny && ny<w && score[x][y]+a[nx][ny]<score[nx][ny]) {
                    score[nx][ny] = score[x][y]+a[nx][ny];
                    q.add(new Tuple(new int[] {nx, ny}, score[nx][ny]));
                }
            }
        }
        
        return score;
    }
    
    private class Tuple implements Comparable<Tuple>{
        private int[] current;
        private long cost;

        public Tuple(int[] current, long cost) {
            this.current = current;
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