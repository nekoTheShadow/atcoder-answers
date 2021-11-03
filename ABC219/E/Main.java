package ABC219.E;
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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        Set<Pair<Integer, Integer>> villages = new HashSet<>();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (stdin.nextString().equals("1")) villages.add(new Pair<>(i, j));
            }
        }
        
        int ans = 0;
        for (int bit = 1; bit < (1<<16); bit++) {
            Set<Pair<Integer, Integer>> inner = new HashSet<>();
            Set<Pair<Integer, Integer>> outer = new HashSet<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if ((bit & (1 << (i*4+j))) != 0) inner.add(new Pair<>(i+1, j+1));
                }
            }
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    Pair<Integer, Integer> pair = new Pair<>(i, j);
                    if (!inner.contains(pair)) outer.add(pair);
                }
            }
           
            
            // すべての村を含むか?
            boolean ok1 = villages.stream().allMatch(inner::contains);
            if (!ok1) continue;
            
            // 堀の中は連結か?
            if (!isConnected(inner)) continue;
            
            // 穴を含まないか?
            if (!isConnected(outer)) continue;
            
            ans++;
        }
        
        stdout.println(ans);
    }
    
    public boolean isConnected(Set<Pair<Integer, Integer>> pairs) {
        Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        for (Pair<Integer, Integer> pair : pairs) {
            queue.addLast(pair);
            break;
        }
        
        List<Pair<Integer, Integer>> diffs = List.of(new Pair<>(1, 0), new Pair<>(-1, 0), new Pair<>(0, 1), new Pair<>(0, -1));
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> cur = queue.removeFirst();
            visited.add(cur);
            for (Pair<Integer, Integer> diff : diffs) {
                Pair<Integer, Integer> nxt = new Pair<>(cur.getA()+diff.getA(), cur.getB()+diff.getB());
                if (pairs.contains(nxt) && !visited.contains(nxt)) queue.add(nxt);
            }
        }
        return pairs.equals(visited);
    }
    
    public class Pair<A extends Comparable<A>, B extends Comparable<B>> implements Comparable<Pair<A, B>> {
        private A a;
        private B b;

        private Pair(A a, B b) {
            this.a = Objects.requireNonNull(a);
            this.b = Objects.requireNonNull(b);;
        }

        public A getA() {
            return a;
        }

        public B getB() {
            return b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Pair<?, ?>) {
                Pair<?, ?> other = (Pair<?, ?>)obj;
                return Objects.equals(this.getA(), other.getA()) && Objects.equals(this.getB(), other.getB());
            }

            return false;
        }

        @Override
        public String toString() {
            return String.format("Pair [a=%s, b=%s]", a, b);
        }

        @Override
        public int compareTo(Pair<A, B> other) {
            int c1 = this.getA().compareTo(other.getA());
            if (c1 == 0) {
                return this.getB().compareTo(other.getB());
            } else {
                return c1;
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