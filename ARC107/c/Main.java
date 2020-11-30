package ARC107.c;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        int[][] a = new int[n][];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextIntArray(n);
        }

        UnionFind uf1 = new UnionFind(n);
        UnionFind uf2 = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isOK1(a, n, k, i, j)) {
                    uf1.union(i, j);
                }
                if (isOK2(a, n, k, i, j)) {
                    uf2.union(i, j);
                }
            }
        }

        Map<Integer, Integer> c1 = new HashMap<>();
        Map<Integer, Integer> c2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            c1.put(uf1.find(i), c1.getOrDefault(uf1.find(i), 0)+1);
            c2.put(uf2.find(i), c2.getOrDefault(uf2.find(i), 0)+1);
        }

        long mod = 998244353;
        long sum1 = 1;
        long sum2 = 1;
        for (long v1 : c1.values()) {
            for (int i = 1; i <= v1; i++) {
                sum1 *= i;
                sum1 %= mod;
            }
        }
        for (long v2 : c2.values()) {
            for (int i = 1; i <= v2; i++) {
                sum2 *= i;
                sum2 %= mod;
            }
        }

        long ans = sum1*sum2%mod;
        stdout.println(ans);
    }

    public boolean isOK1(int[][] a, int n, int k, int i, int j) {
        return IntStream.range(0, n).allMatch(x -> a[i][x]+a[j][x] <= k);
    }

    public boolean isOK2(int[][] a, int n, int k, int i, int j) {
        return IntStream.range(0, n).allMatch(x -> a[x][i]+a[x][j] <= k);
    }

    public class UnionFind {
        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            this.parent = IntStream.range(0, n).toArray();
            this.size = new int[n];
            Arrays.fill(this.size, 1);
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean same(int x, int y) {
            return find(x) == find(y);
        }

        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return ;
            }

            if (size[x] < size[y]) {
                parent[x] = y;
                size[y] += size[x];
            } else {
                parent[y] = x;
                size[x] += size[y];
            }

        }

        public int size(int x) {
            return size[find(x)];
        }
    }


    private static final Stdin stdin = new Stdin(System.in);
    private static final Stdout stdout = new Stdout(System.out);
    private static final Stderr stderr = new Stderr(System.err, false);

    public static void main(String[] args) {
        try {
            new Main().exec();
        } finally {
            stdout.flush();
        }
    }

    // ASCII ONLY
    public static class Stdin {
        private InputStream in;
        private byte[] buf;
        private int ptr;
        private int len;

        public Stdin(InputStream in) {
            this.in = in;
            this.buf = new byte[1024];
            this.ptr = 0;
            this.len = 0;
        }

        public String nextString() {
            StringBuilder sb = new StringBuilder();
            byte b;
            while ((b = read()) != -1) {
                sb.appendCodePoint(b);
            }
            return sb.toString();
        }

        public int nextInt() {
            return (int)nextLong();
        }

        public double nextDouble() {
            return Double.parseDouble(nextString());
        }

        public long nextLong() {
            boolean negative = false;
            int x = 0;

            byte b = read();
            if (b == '-') {
                negative = true;
            } else {
                x += b-'0';
            }

            while ((b=read()) != -1) {
                x *= 10;
                x += b-'0';
            }

            return negative ? -x : x;
        }

        private byte read() {
            byte b = readByte();
            if (b == '\r') {
                readByte(); // LFを読み飛ばす
                return -1;
            } else if (b == '\n' || b == ' ') {
                return -1;
            } else {
                return b;
            }
        }

        private byte readByte(){
            if (len == ptr) {
                try {
                    ptr = 0;
                    len = in.read(buf);
                    if (len == -1) return -1;
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
            return buf[ptr++];
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

    public static class Stderr {
        private PrintWriter stderr;
        private boolean debug;

        public Stderr(PrintStream stderr, boolean debug) {
            this.stderr =  new PrintWriter(stderr, false);
            this.debug = debug;
        }

        public void println(Object ... objs) {
            if (!debug) return ;

            stderr.print("DEBUG: ");
            for (int i = 0, len = objs.length; i < len; i++) {
                stderr.print(deepToString(objs[i]));
                if (i != len-1) stderr.print(' ');
            }
            stderr.println();
            stderr.flush();
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
                return "{" + String.join(", ", tokens) + "}";
            }

            return Objects.toString(o);
        }
    }
}