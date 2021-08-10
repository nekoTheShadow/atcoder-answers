package ABC213.E;
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
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        int h = stdin.nextInt();
        int w = stdin.nextInt();
        String[] s = new String[h];
        for (int i = 0; i < h; i++) s[i] = stdin.nextString();
        
        int[][] diffs1 = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        List<int[]> diffs2 = new ArrayList<>();
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (1<=Math.abs(i)+Math.abs(j) && Math.abs(i)+Math.abs(j)<=3) {
                    diffs2.add(new int[] {i, j});
                }
            }
        }
        
        int[][] dist = new int[h][w];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;
        
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[] {0, 0, 0});
        while (!que.isEmpty()) {
            int[] tpl = que.removeFirst();
            int v = tpl[0];
            int x = tpl[1];
            int y = tpl[2];
            for (int[] diff : diffs1) {
                int nx = x + diff[0];
                int ny = y + diff[1];
                if (0<=nx && nx<h && 0<=ny && ny<w && s[nx].charAt(ny)=='.' && v<dist[nx][ny]) {
                    dist[nx][ny] = v;
                    que.addFirst(new int[] {v, nx, ny});
                }
            }
            
            for (int[] diff : diffs2) {
                int nx = x + diff[0];
                int ny = y + diff[1];
                if (0<=nx && nx<h && 0<=ny && ny<w && v+1<dist[nx][ny]) {
                    dist[nx][ny] = v+1;
                    que.addLast(new int[] {v+1, nx, ny});
                }
            }
        }
        
        stdout.println(dist[h-1][w-1]);
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