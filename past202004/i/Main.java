package past202004.i;
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
        int n = stdin.nextInt();
        int m = 1<<n;
        
        List<int[]> tpls = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = stdin.nextInt();
            tpls.add(new int[] {i, a});
        }
        
        int[] b = new int[m];
        int k = 1;
        while (tpls.size() > 1) {
            List<int[]> wins = new ArrayList<>();
            for (int i = 0; i < tpls.size(); i += 2) {
                int[] tpl1 = tpls.get(i);
                int[] tpl2 = tpls.get(i+1);
                
                int x1 = tpl1[0];
                int a1 = tpl1[1];
                int x2 = tpl2[0];
                int a2 = tpl2[1];
                if (a1 < a2) {
                    b[x1] = k;
                    wins.add(new int[] {x2, a2});
                } else {
                    b[x2] = k;
                    wins.add(new int[] {x1, a1});                    
                }
            }
            tpls = wins;
            k++;
        }
        
        for (int i = 0; i < m; i++) {
            if (b[i]==0) b[i] = k-1;
        }
        
        for (int v : b) stdout.println(v);
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