package typical90._021;
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
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        SCC scc = new SCC(n);
        for (int i = 0; i < m; i++) {
            int a = stdin.nextInt()-1;
            int b = stdin.nextInt()-1;
            scc.add(a, b);
        }
        
        BigInteger ans = scc.run()
                            .stream()
                            .map(group -> BigInteger.valueOf(group.size()))
                            .map(x -> x.multiply(x.subtract(BigInteger.ONE)).divide(BigInteger.TWO))
                            .reduce(BigInteger.ZERO, BigInteger::add);
        stdout.println(ans);
    }
    
    public class SCC {
        private List<List<Integer>> g1;
        private List<List<Integer>> g2;
        
        public SCC(int n) {
            this.g1 = new ArrayList<>();
            this.g2 = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                g1.add(new ArrayList<>());
                g2.add(new ArrayList<>());
            }
        }
        
        public void add(int from, int to) {
            this.g1.get(from).add(to);
            this.g2.get(to).add(from);
        }
        
        public List<List<Integer>> run() {
            Set<Integer> used1 = new HashSet<>();
            List<Integer> order = new ArrayList<>();
            for (int start = 0; start < this.g1.size(); start++) dfs1(start, used1, order);
 
            Collections.reverse(order);
            List<List<Integer>> groups = new ArrayList<>();
            Set<Integer> used2 = new HashSet<>();
            for (Integer cur : order) {
                List<Integer> group = new ArrayList<>();
                dfs2(cur, used2, group);
                if (!group.isEmpty()) groups.add(group);
            }
            
            return groups;
        }
        
        private void dfs1(Integer cur, Set<Integer> used, List<Integer> order) {
            if (used.contains(cur)) return ;
            
            used.add(cur);
            for (Integer nxt : this.g1.get(cur)) {
                if (!used.contains(nxt)) dfs1(nxt, used, order);
            }
            order.add(cur);
        }
        
        private void dfs2(Integer cur, Set<Integer> used, List<Integer> group) {
            if (used.contains(cur)) return ;
            
            used.add(cur);
            group.add(cur);
            for (Integer nxt : this.g2.get(cur)) {
                if (!used.contains(nxt)) dfs2(nxt, used, group);
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

        public String[] nextStringArray(int n) {
            String[] a = new String[n];
            for (int i = 0; i < n; i++) a[i] = nextString();
            return a;
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        public double[] nextDoubleArray(int n) {
            double[] a = new double[n];
            for (int i = 0; i < n; i++) a[i] = nextDouble();
            return a;
        }

        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }
        
        public BigInteger[] nexBigIntegerArray(int n) {
            BigInteger[] a = new BigInteger[n];
            for (int i = 0; i < n; i++) a[i] = nextBigInteger();
            return a;
        }
        
        public List<Integer> nextIntegerList(int n) {
            return nextList(n, this::nextInt);
        }
        
        public List<Long> nextLongList(int n) {
            return nextList(n, this::nextLong);
        }
        
        public List<Double> nextDoubleList(int n) {
            return nextList(n, this::nextDouble);
        }
        
        public List<String> nextStringList(int n) {
            return nextList(n, this::nextString);
        }
        
        public List<BigInteger> nextBigIntegerList(int n) {
            return nextList(n, this::nextBigInteger);
        }
        
        private <T> List<T> nextList(int n, Supplier<T> supplier) {
            List<T> a = new ArrayList<>();
            for (int i = 0; i < n; i++) a.add(supplier.get());
            return a;
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