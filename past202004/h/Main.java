package past202004.h;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        char[][] a = new char[n][];
        for (int i = 0; i < n; i++) a[i] = stdin.nextString().toCharArray();
        
        int p = -1;
        int q = -1;
        List<List<Integer>> mat = new ArrayList<>();
        for (int i = 0; i < 11; i++) mat.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j]=='S') {
                    mat.get(0).add(i*m+j);
                    p = i*m+j;
                } else if (a[i][j]=='G') {
                    mat.get(10).add(i*m+j);
                    q = i*m+j;
                } else {
                    mat.get(a[i][j]-'0').add(i*m+j);
                }
            }
        }
        
        Dijkstra dijkstra = new Dijkstra(n*m);
        for (int i = 0; i < 10; i++) {
            for (int s : mat.get(i)) {
                for (int t : mat.get(i+1)) {
                    int x1 = s/m;
                    int y1 = s%m;
                    int x2 = t/m;
                    int y2 = t%m;
                    dijkstra.add(s, t, Math.abs(x1-x2)+Math.abs(y1-y2));
                }
            }
        }
        
        long ans = dijkstra.run(p)[q];
        if (ans==Long.MAX_VALUE) {
            stdout.println(-1);
        } else {
            stdout.println(ans);
        }
    }
    
    public class Dijkstra {
        private int n;
        private Map<Integer, List<Edge>> edges;

        public Dijkstra(int n) {
            this.n = n;
            this.edges = new HashMap<>();
        }

        public void add(int from, int to, long cost) {
            this.edges.computeIfAbsent(from, unused -> new ArrayList<>()).add(new Edge(to, cost));
        }

        public long[] run(int start) {
            long[] score = new long[n];
            Arrays.fill(score, Long.MAX_VALUE);
            score[start] = 0;

            PriorityQueue<Tuple> q = new PriorityQueue<>();
            q.add(new Tuple(start, 0));
            while (!q.isEmpty()) {
                Tuple t = q.remove();
                if (!edges.containsKey(t.current)) continue; // 次がない場合
                if (score[t.current] < t.cost) continue; // cost情報が古い場合

                for (Edge e : edges.get(t.current)) {
                    if (t.cost + e.cost < score[e.next]) {
                        score[e.next] = t.cost + e.cost;
                        q.add(new Tuple(e.next, score[e.next]));
                    }
                }
            }
            return score;
        }

        private class Edge {
            private int next;
            private long cost;

            public Edge(int next, long cost) {
                this.next = next;
                this.cost = cost;
            }
        }

        private class Tuple implements Comparable<Tuple>{
            private int current;
            private long cost;

            public Tuple(int current, long cost) {
                this.current = current;
                this.cost = cost;
            }

            @Override
            public int compareTo(Tuple other) {
                return Long.compare(this.cost, other.cost);
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