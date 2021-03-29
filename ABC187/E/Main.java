package ABC187.E;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        int[] a = new int[n-1];
        int[] b = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            a[i] = stdin.nextInt()-1;
            b[i] = stdin.nextInt()-1;
        }
        
        List<List<Integer>> nexts = IntStream.range(0, n).mapToObj(unused -> new ArrayList<Integer>()).collect(Collectors.toList());
        for (int i = 0; i < n-1; i++) {
            nexts.get(a[i]).add(b[i]);
            nexts.get(b[i]).add(a[i]);
        }
        
        int[] d = new int[n];
        Arrays.fill(d, -1);
        d[0] = 0;
        Deque<Integer> que1 = new ArrayDeque<>();
        que1.add(0);
        while (!que1.isEmpty()) {
            int current = que1.removeFirst();
            for (int next : nexts.get(current)) {
                if (d[next] == -1) {
                    d[next] = d[current] + 1;
                    que1.addLast(next);
                }
            }
        }
        
        long[] c = new long[n];
        int q = stdin.nextInt();
        for (int i = 0; i < q; i++) {
            int t = stdin.nextInt();
            int e = stdin.nextInt()-1;
            long x = stdin.nextLong();
            
            int u;
            int v;
            if (t == 1) {
                u = a[e];
                v = b[e];
            } else {
                u = b[e];
                v = a[e];
            }
            
            if (d[u] < d[v]) {
                c[0] += x;
                c[v] -= x;
            } else {
                c[u] += x;
            }
        }
        
        Deque<Integer> que2 = new ArrayDeque<>();
        que2.add(0);
        while (!que2.isEmpty()) {
            int current = que2.removeFirst();
            for (int next : nexts.get(current)) {
                if (d[current] < d[next]) {
                    c[next] += c[current];
                    que2.addLast(next);
                }
            }
        }
        
        Arrays.stream(c).forEach(stdout::println);
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