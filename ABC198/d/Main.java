package ABC198.d;
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
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public void exec() {
        String s1 = stdin.nextString();
        String s2 = stdin.nextString();
        String s3 = stdin.nextString();
        
        List<Character> chars = new ArrayList<>();
        for (String s : new String[] {s1, s2, s3}) {
            for (char ch : s.toCharArray()) {
                if (!chars.contains(ch)) chars.add(ch);
            }
        }
        
        int n = chars.size();
        if (n > 10) {
            stdout.println("UNSOLVABLE");
            return ;
        }
        
        
        for (List<Integer> comb : new Combinations<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), n)) {
            for (List<Integer> perms : new Permutations<>(comb)) {
                String t1 = s1, t2 = s2, t3 = s3;
                for (int i = 0; i < n; i++) {
                   t1 = t1.replace(chars.get(i), (char)(perms.get(i)+'0'));
                   t2 = t2.replace(chars.get(i), (char)(perms.get(i)+'0'));
                   t3 = t3.replace(chars.get(i), (char)(perms.get(i)+'0'));
                }

                if (t1.startsWith("0")) continue;
                if (t2.startsWith("0")) continue;
                if (t3.startsWith("0")) continue;
                if (Long.parseLong(t1)+Long.parseLong(t2) == Long.parseLong(t3)) {
                    stdout.println(t1);
                    stdout.println(t2);
                    stdout.println(t3);
                    return ;
                }
            }
        }
        stdout.println("UNSOLVABLE");
    }
    
    public class Combinations<T> implements Iterable<List<T>>{
        private List<T> src;
        private int n;

        public Combinations(List<T> src, int n) {
            this.src = src;
            this.n = n;
        }

        @Override
        public Iterator<List<T>> iterator() {
            return new CombinationsIterator<>(src, n);
        }

        private class CombinationsIterator<S> implements Iterator<List<S>> {
            private List<S> src;
            private int m;

            private int len;
            private int bit;
            private List<S> ptr;

            public CombinationsIterator(List<S> src, int m) {
                this.src = src;
                this.len = src.size();
                this.m = m;
                this.bit = 0;
            }

            @Override
            public boolean hasNext() {
                while (bit < (1<<len)) {
                    if (m<=0 || Integer.bitCount(bit)==m) {
                        this.ptr = new ArrayList<>();
                        for (int i = 0; i < len; i++) {
                            if ((bit&(1<<i))!=0) ptr.add(src.get(i));
                        }
                        bit++;
                        return true;
                    } else {
                        bit++;
                    }
                }
                return false;
            }

            @Override
            public List<S> next() {
                return this.ptr;
            }
        }
    }
    
    public  class Permutations<T> implements Iterable<List<T>>{
        private List<T> list;
        public Permutations(List<T> list) {
            this.list = list;
        }

        @Override
        public Iterator<List<T>> iterator() {
            return new PermutationIterator<>(list);
        }

        private class PermutationIterator<S> implements Iterator<List<S>> {
            private List<S> list;
            private int[] a;
            private int n;

            public PermutationIterator(List<S> list) {
                this.list = list;
                this.n = list.size();
            }
      
            @Override
            public boolean hasNext() {
                if (a==null) {
                    this.a = IntStream.range(0, n).toArray();
                    return true;
                }
                
                int i = n - 1;
                while (i-1 >= 0 && a[i-1] > a[i]) i--;
                if (i==0) return false;
                
                int j = i;
                while (j+1 < n && a[i-1] < a[j+1]) j++;
                
                swap(i-1, j);
                
                int s = i;
                int t = n-1;
                while (s < t) swap(s++, t--);
                
                return true;
            }
            
            private void swap(int x, int y) {
                int tmp = a[x];
                a[x] = a[y];
                a[y] = tmp;
            }

            @Override
            public List<S> next() {
                return Arrays.stream(a).mapToObj(i -> list.get(i)).collect(Collectors.toList());
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