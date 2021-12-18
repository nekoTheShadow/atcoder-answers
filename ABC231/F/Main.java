package ABC231.F;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        long[] a = IntStream.range(0, n).mapToLong(unused -> stdin.nextLong()).toArray();
        long[] b = IntStream.range(0, n).mapToLong(unused -> stdin.nextLong()).toArray();
        compress(a);
        compress(b);
        IntStream.range(0, n).forEach(i -> a[i]++);
        IntStream.range(0, n).forEach(i -> b[i]++);
        
        List<Pair<Long, Long>> c = IntStream.range(0, n)
                                                  .mapToObj(i -> new Pair<>(-a[i], b[i]))
                                                  .sorted()
                                                  .collect(Collectors.toList());
        
        long ans = 0;
        Map<Pair<Long, Long>, Long> dup = new HashMap<>();
        BIT bit = new BIT(n+1);
        for (Pair<Long, Long> p : c) {
            dup.put(p, dup.getOrDefault(p, 0L) + 1);
            bit.add(p.getB().intValue(), 1);
            ans += bit.sum(p.getB().intValue());
        }
        
        for (Entry<Pair<Long, Long>, Long> e : dup.entrySet()) {
            ans += e.getValue() * (e.getValue()-1) / 2;
        }
        
        stdout.println(ans);
    }
    
    public void compress(int[] a) {
        int[] b = Arrays.stream(a).sorted().distinct().toArray();
        Map<Integer, Integer> d = IntStream.range(0, b.length).boxed().collect(Collectors.toMap(i -> b[i], Function.identity()));
        IntStream.range(0, a.length).forEach(i -> a[i] = d.get(a[i]));
    }
    
    public void compress(long[] a) {
        long[] b = Arrays.stream(a).sorted().distinct().toArray();
        Map<Long, Integer> d = IntStream.range(0, b.length).boxed().collect(Collectors.toMap(i -> b[i], Function.identity()));
        IntStream.range(0, a.length).forEach(i -> a[i] = d.get(a[i]));
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
    
    /**
     * 数列a1, a2, ... anについて
     * - add(i, x) - aiにxを加算
     * - sum(i)    - a1..aiの合計を取得
     */
    public class BIT {

        private int n;
        private int[] tree;

        public BIT(int n) {
            this.n = n;
            this.tree = new int[n+1];
        }

        public int sum(int i) {
            int s = 0;
            while (i > 0) {
                s += this.tree[i];
                i -= i & -i;
            }
            return s;
        }

        public void add(int i, int x) {
            while (i <= this.n) {
                this.tree[i] += x;
                i += i & -i;
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