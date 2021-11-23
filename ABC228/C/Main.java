package ABC228.C;
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
import java.util.Arrays;
import java.util.Deque;
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        int[] p = new int[n];
        int[] q = new int[n];
        for (int i = 0; i < n; i++) {
            int p1 = stdin.nextInt();
            int p2 = stdin.nextInt();
            int p3 = stdin.nextInt();
            p[i] = p1+p2+p3;
            q[i] = p1+p2+p3;
        }
        Arrays.sort(q);
        reverse(q);
        
        for (int v : p) {
            if (q[k-1] <= v+300) {
                stdout.println("Yes");
            } else {
                stdout.println("No");
            }
        }
    }
    
    public int bisectLeft(int[] a, int x) {
        int ng = -1;
        int ok = a.length;
        while (Math.abs(ok-ng) > 1) {
            int mi = (ok+ng)/2;
            if (a[mi] >= x) {
                ok = mi;
            } else {
                ng = mi;
            }
        }
        return ok;
    }
    
    public void reverse(int[] a) {
        for (int i = 0, n = a.length; i < n/2; i++) {
            int t = a[i];
            a[i] = a[n-i-1];
            a[n-i-1] = t;
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