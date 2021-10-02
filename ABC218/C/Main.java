package ABC218.C;
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
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        Set<Pair<Integer, Integer>> s = read(n);
        Set<Pair<Integer, Integer>> t = read(n);
        for (int time = 0; time < 4; time++) {
            Pair<Integer, Integer> a = Collections.min(s);
            Pair<Integer, Integer> b = Collections.min(t);
            s = s.stream().map(p -> new Pair<>(p.getA()-a.getA(), p.getB()-a.getB())).collect(Collectors.toSet());
            t = t.stream().map(p -> new Pair<>(p.getA()-b.getA(), p.getB()-b.getB())).collect(Collectors.toSet());
            if (s.equals(t)) {
                stdout.println("Yes");
                return;
            }
            t = t.stream().map(p -> new Pair<>(p.getB(), -p.getA())).collect(Collectors.toSet());
        }
        stdout.println("No");
    }
    
    public Set<Pair<Integer, Integer>> read(int n) {
        Set<Pair<Integer, Integer>> pairs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char[] line = stdin.nextString().toCharArray();
            for (int j = 0; j < n; j++) {
                if (line[j] == '#') pairs.add(new Pair<>(i, j));
            }
        }
        return pairs;
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