package ABC215.C;
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
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public void exec() {
        String s = stdin.nextString();
        int n = stdin.nextInt();
        
        Permutations<String> perms = new Permutations<>(Arrays.asList(s.split("")));
        Set<String> set = new HashSet<>();
        for (List<String> perm : perms) {
            set.add(perm.stream().collect(Collectors.joining()));
        }
        List<String> words = new ArrayList<>(set);
        Collections.sort(words);
        stdout.println(words.get(n-1));
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