package e;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public void exec() {
        int h = stdin.nextInt();
        int w = stdin.nextInt();
        int n = stdin.nextInt();
        int m = stdin.nextInt();

        boolean[][] p1 = new boolean[h][w];
        boolean[][] p2 = new boolean[h][w];
        boolean[][] p3 = new boolean[h][w];
        boolean[][] p4 = new boolean[h][w]; //電球
        boolean[][] q = new boolean[h][w]; //ブロック
        for (int i = 0; i < n; i++) {
            int a = stdin.nextInt() - 1;
            int b = stdin.nextInt() - 1;
            p1[a][b] = true;
            p2[a][b] = true;
            p3[a][b] = true;
            p4[a][b] = true;
        }
        for (int i = 0; i < m; i++) {
            int c = stdin.nextInt() - 1;
            int d = stdin.nextInt() - 1;
            q[c][d] = true;
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (j+1 < w && !q[i][j] && p1[i][j]) p1[i][j+1] = true;
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = w-1; j >= 0; j--) {
                if (j-1 >= 0 && !q[i][j] && p2[i][j]) p2[i][j-1] = true;
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i+1 < h && !q[i][j] && p3[i][j]) p3[i+1][j] = true;
            }
        }
        for (int i = h-1; i >= 0; i--) {
            for (int j = 0; j < w; j++) {
                if (i-1 >= 0 && !q[i][j] && p4[i][j]) p4[i-1][j] = true;
            }
        }

        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (q[i][j]) continue;
                if (p1[i][j] || p2[i][j] || p3[i][j] || p4[i][j]) count++;
            }
        }
        stdout.println(count);

    }

    private static final Stdin stdin = new Stdin(System.in);
    private static final Stdout stdout = new Stdout();

    public static void main(String[] args) {
        try {
            new Main().exec();
        } finally {
            stdout.flush();
        }
    }

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