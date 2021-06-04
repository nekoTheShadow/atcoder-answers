package typical90._051;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public void exec() {
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        long p = stdin.nextLong();
        long[] a = stdin.nextLongArray(n);
        
        List<Long> a1 = IntStream.range(0, n/2).mapToLong(i -> a[i]).boxed().collect(Collectors.toList());
        List<Long> a2 = IntStream.range(n/2, n).mapToLong(i -> a[i]).boxed().collect(Collectors.toList());
        
        Map<Integer, List<Long>> d1 = new HashMap<>();
        Map<Integer, List<Long>> d2 = new HashMap<>();
        
        for (List<Long> b : new Combinations<>(a1, -1)) {
            int key = b.size();
            long val = b.stream().mapToLong(Long::longValue).sum();
            d1.computeIfAbsent(key, unused -> new ArrayList<Long>()).add(val);
        }
        for (List<Long> b : new Combinations<>(a2, -1)) {
            int key = b.size();
            long val = b.stream().mapToLong(Long::longValue).sum();
            d2.computeIfAbsent(key, unused -> new ArrayList<Long>()).add(val);
        }
        
        d2.values().forEach(Collections::sort);
        
        long ans = 0;
        for (Integer k1 : d1.keySet()) {
            List<Long> vals2 = d2.getOrDefault(k-k1, Collections.emptyList());
            for (Long v1 : d1.get(k1)) {
                ans += bisectRight(vals2, p-v1);
            }
        }
        stdout.println(ans);
    }
    
    public <T> int bisectRight(List<? extends Comparable<? super T>> a, T x) {
        int ng = -1;
        int ok = a.size();
        while (Math.abs(ok-ng) > 1) {
            int mi = (ok+ng)/2;
            if (a.get(mi).compareTo(x) > 0) {
                ok = mi;
            } else {
                ng = mi;
            }
        }
        return ok;
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