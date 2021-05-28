package typical90._043;

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

public class Main {

    public void exec() {
        int h = stdin.nextInt();
        int w = stdin.nextInt();
        int rs = stdin.nextInt()-1;
        int cs = stdin.nextInt()-1;
        int rt = stdin.nextInt()-1;
        int ct = stdin.nextInt()-1;
        char[][] s = new char[h][];
        for (int i = 0; i < h; i++) s[i] = stdin.nextString().toCharArray();
        
        long ans = Long.MAX_VALUE;
        long[][][] d = new long[h][w][4];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                d[i][j][0] = Long.MAX_VALUE;
                d[i][j][1] = Long.MAX_VALUE;
                d[i][j][2] = Long.MAX_VALUE;
                d[i][j][3] = Long.MAX_VALUE;
            }
        }
        d[rs][cs][0] = 0;
        d[rs][cs][1] = 0;
        d[rs][cs][2] = 0;
        d[rs][cs][3] = 0;
        
        int[][] diffs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        Deque<Tuple> q = new ArrayDeque<>();
        q.addLast(new Tuple(rs, cs, 0));
        q.addLast(new Tuple(rs, cs, 1));
        q.addLast(new Tuple(rs, cs, 2));
        q.addLast(new Tuple(rs, cs, 3));
        
        while (!q.isEmpty()) {
            Tuple cur = q.removeFirst();
            for (int dir = 0; dir < 4; dir++) {
                int i = diffs[dir][0];
                int j = diffs[dir][1];
                int nx = cur.x + i;
                int ny = cur.y + j;
                if (0 <= nx && nx < h && 0 <= ny && ny < w && s[nx][ny] == '.') {
                    if (cur.dir == dir) {
                        if (d[cur.x][cur.y][cur.dir] < d[nx][ny][dir]) {
                            d[nx][ny][dir] = d[cur.x][cur.y][cur.dir];
                            q.addFirst(new Tuple(nx, ny, dir));
                        }
                    } else {
                        if (d[cur.x][cur.y][cur.dir]+1 < d[nx][ny][dir]) {
                            d[nx][ny][dir] = d[cur.x][cur.y][cur.dir]+1;
                            q.addLast(new Tuple(nx, ny, dir));
                        }
                    }
                }
            }
        }
        
        stdout.println(Arrays.stream(d[rt][ct]).min().getAsLong());
    }
    
    private class Tuple {
        private int x;
        private int y;
        private int dir;
        
        public Tuple(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    
    private List<List<Integer>> nexts;
    private long[] depth;
    
    public long dfs(int prev, int cur) {
        if (depth[cur] != 0) return depth[cur];
        
        long ans = 1;
        for (int nxt : nexts.get(cur)) {
            if (nxt!=prev) {
                ans += dfs(cur, nxt);
            }
        }
        
        depth[cur] = ans;
        return ans;
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