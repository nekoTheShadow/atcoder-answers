package zone2021.c;
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
        int n = stdin.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        long[] c = new long[n];
        long[] d = new long[n];
        long[] e = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextLong();
            b[i] = stdin.nextLong();
            c[i] = stdin.nextLong();
            d[i] = stdin.nextLong();
            e[i] = stdin.nextLong();
        }
        
        long ok = 0;
        long ng = 1000000001L;
        while (Math.abs(ok-ng)>1) {
            long mi = (ok+ng)/2;
            
            boolean[] has = new boolean[1<<5];
            for (int i = 0; i < n; i++) {
                int mask = 0;
                if (a[i] >= mi) mask |= 1<<0;
                if (b[i] >= mi) mask |= 1<<1;
                if (c[i] >= mi) mask |= 1<<2;
                if (d[i] >= mi) mask |= 1<<3;
                if (e[i] >= mi) mask |= 1<<4;
                has[mask] = true;
            }
            
            boolean isOK = false;
            for (int m1 = 0; m1 < 1<<5; m1++) {
                for (int m2 = 0; m2 < 1<<5; m2++) {
                    for (int m3 = 0; m3 < 1<<5; m3++) {
                        if (has[m1] && has[m2] && has[m3] && (m1|m2|m3)==(1<<5)-1) isOK=true;
                    }
                }
            }
            
            
            if (isOK) {
                ok = mi;
            } else {
                ng = mi;
            }
        }
        stdout.println(ok);
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