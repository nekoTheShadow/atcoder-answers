package past202004.g;
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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        int q = stdin.nextInt();
        Deque<String[]> que = new ArrayDeque<>();
        while (q-- > 0) {
            int query = stdin.nextInt();
            
            if (query==1) {
                String c = stdin.nextString(); // 英子文字
                String x = stdin.nextString(); // 整数
                que.addLast(new String[] {c, x});
            }
            
            if (query==2) {
                Map<String, Long> h = new HashMap<>();
                long d = stdin.nextLong();
                while (d > 0) {
                    if (que.isEmpty()) break;
                    
                    String[] arr = que.peekFirst();
                    String c = arr[0];
                    long x = Long.parseLong(arr[1]);
                    if (x <= d) {
                        d -= x;
                        h.put(c, h.getOrDefault(c, 0L) + x);
                        que.removeFirst();
                    } else {
                        arr[1] = String.valueOf(x-d);
                        h.put(c, h.getOrDefault(c, 0L) + d);
                        break;
                    }
                }

                stdout.println(h.values().stream().mapToLong(v -> v*v).sum());
            }
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