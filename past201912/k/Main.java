package past201912.k;
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
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        
        LCA lca = new LCA(n);
        
        int root = -1;
        for (int i = 0; i < n; i++) {
            int p = stdin.nextInt();
            if (p==-1) {
                root = i;
            } else {
                lca.add(p-1, i);
            }
        }
        
        lca.build(root);
        
        int q = stdin.nextInt();
        while(q-->0) {
            int a = stdin.nextInt()-1;
            int b = stdin.nextInt()-1;
            if (b == lca.lca(a, b)) {
                stdout.println("Yes");
            } else {
                stdout.println("No");
            }
        }
    }
    
    public class LCA {
         
        private int n;
        private List<List<Integer>> g;
     
        public LCA(int n){
            this.n = n;
            this.g = IntStream.range(0, n).mapToObj(unused -> new ArrayList<Integer>()).collect(Collectors.toList());
        }
        
        public void add(int parent, int child) {
            g.get(parent).add(child);
        }
        
        private int k;
        private int[] depth;
        private int[][] pars;
        
        public void build(int root) {
          k = Integer.toBinaryString(n).length();
          depth = new int[n];
          pars = new int[n][k+1];
          Arrays.fill(depth, -1);
          dfs(root, -1, 0);
          for (int i = 0; i < k; i++) {
              for (int j = 0; j < n; j++) {
                  if(pars[j][i] < 0) {
                      pars[j][i+1] = -1;
                  } else {
                      pars[j][i+1] = pars[ pars[j][i] ][i];
                  }
              }
          }
        }
     
        private void dfs(int v, int p, int i) {
            pars[v][0] = p;
            depth[v] = i;
            for(int c : g.get(v)){
                if(depth[c] != -1) continue;
                dfs(c, v, i+1);
            }
        }
     
        public int lca(int a, int b){
            if(depth[a] < depth[b]){
                int t = b;
                b = a;
                a = t;
            }
            for (int i = 0; i <= k; i++) {
                if(((depth[a] - depth[b]) >> i & 1) == 1){
                    a = pars[a][i];
                }
            }
            if(a == b) return a;
            for (int i = k; i >= 0; i--) {
                if(pars[a][i] != pars[b][i]){
                    a = pars[a][i];
                    b = pars[b][i];
                }
            }
            return pars[a][0];
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