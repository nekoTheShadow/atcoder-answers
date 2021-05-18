package typical90._029;
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
import java.util.function.LongBinaryOperator;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class Main {

    public void exec() {
        int w = stdin.nextInt();
        int n = stdin.nextInt();
        
        LazySegmentTree s = new LazySegmentTree(w+1, Long::max, 0);
        
        for (int i = 0; i < n; i++) {
            int l = stdin.nextInt()-1;
            int r = stdin.nextInt()-1;
            long x = s.get(l, r+1)+1;
            stdout.println(x);
            s.update(l, r+1, x);
        }
    }
    
    public class LazySegmentTree {
        
        private int n;
        private LongBinaryOperator op;
        private long e;
        private long[] node;
        private long[] lazy;
        private boolean[] flag;
        
        public LazySegmentTree(int size, LongBinaryOperator op, long e) {
            this.op = op;
            this.e = e;
            
            this.n = 1;
            while (this.n < size) this.n *= 2;

            this.node = new long[2*this.n-1];
            this.lazy = new long[2*this.n-1];
            this.flag = new boolean[2*this.n-1];
            Arrays.fill(this.node, this.e);
            Arrays.fill(this.lazy, this.e);
        }
        
        public void update(int a, int b, long x) {
            update(a, b, x, 0, 0, n);
        }
        
        private void update(int a, int b, long x, int k, int l, int r) {
            eval(k, l, r);
            if(r<=a || b<=l)return;
            if(a<=l && r<=b){
                flag[k] = true;
                lazy[k] = x;
                eval(k, l, r);
            } else {
                update(a, b, x, 2*k+1, l, (l+r)/2);
                update(a, b, x, 2*k+2, (l+r)/2, r);
                node[k] = op.applyAsLong(node[2*k+1],node[2*k+2]);
            }
        }
        
        public long get(int a, int b) {
            return get(a, b, 0, 0, n);
        }
        
        private long get(int a, int b, int k, int l, int r) {
            eval(k, l, r);
            if(r<=a || b<=l) return e;
            if(a<=l && r<=b) return node[k];
            long vl = get(a, b, 2*k+1, l, (l+r)/2);
            long vr = get(a, b, 2*k+2, (l+r)/2, r);
            return op.applyAsLong(vl, vr);
        }
        
        
        private void eval(int k, int l, int r) {
            if (!flag[k]) return;
            
            node[k] = lazy[k];
            if(l+1 < r){
                lazy[2*k+1] = lazy[2*k+2] = lazy[k];
                flag[2*k+1] = flag[2*k+2] = true;
            }
            flag[k] = false;
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