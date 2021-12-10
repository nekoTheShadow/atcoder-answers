package ABC230.E;
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
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        long n = stdin.nextLong();
        List<QuotientRange> quotientRanges = QuotientRange.solve(n);
        
        long ans = 0;
        for (QuotientRange quotientRange : quotientRanges) {
            ans += quotientRange.getZ() * (quotientRange.getY() - quotientRange.getX() + 1);
        }
        stdout.println(ans);
    }
    
 // cf. https://ei1333.github.io/luzhiled/snippets/math/quotient-range.html
 // 自然数nの商 floor(n/i) (1<=i<=n) の値の種類数を求める
 // 戻り値: // x<=i<=yを満たす整数の商がz個存在する。
 public static class QuotientRange {
     public static List<QuotientRange> solve(long n) {
         List<QuotientRange> ranges = new ArrayList<>();
         
         long m;
         for (m = 1; m*m <= n; m++) {
             ranges.add(new QuotientRange(m, m, n/m));
         }
         
         for (long i = m; i >= 1; i--) {
             long l = n / (i + 1) + 1;
             long r = n / i;
             if (l <= r && ranges.get(ranges.size()-1).getY() < l) {
                 ranges.add(new QuotientRange(l, r, n/l));
             }
         }
         return ranges;
     }
     
     // x<=i<=yを満たす整数の商がz個存在する。
     private long x;
     private long y;
     private long z;
     
     private QuotientRange(long x, long y, long z) {
         this.x = x;
         this.y = y;
         this.z = z;
     }
     public long getX() {
         return x;
     }
     public long getY() {
         return y;
     }
     public long getZ() {
         return z;
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