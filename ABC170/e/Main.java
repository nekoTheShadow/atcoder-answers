package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public void exec(Stdin stdin, PrintWriter stdout) {
        int m = 2 * 100000;
        int n = stdin.readInt();
        int q = stdin.readInt();
        int[] a = new int[n]; // レート
        int[] b = new int[n]; // 所属幼稚園
        for (int i = 0; i < n; i++) {
            a[i] = stdin.readInt();
            b[i] = stdin.readInt() - 1;
        }

        List<TreeMap<Integer, Integer>> kinders = IntStream.range(0, m).mapToObj(unused -> new TreeMap<Integer, Integer>()).collect(Collectors.toList());
        TreeMap<Integer, Integer> maxs = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            TreeMap<Integer, Integer> kinder = kinders.get(b[i]);
            kinder.put(a[i], kinder.getOrDefault(a[i], 0) + 1);
        }
        for (int i = 0; i < m; i++) {
            TreeMap<Integer, Integer> kinder = kinders.get(i);
            if (!kinder.isEmpty()) {
                Integer max = kinders.get(i).lastKey();
                maxs.put(max, maxs.getOrDefault(max, 0) + 1);
            }
        }

        for (int i = 0; i < q; i++) {
            int c = stdin.readInt() - 1; // 幼児
            int d = stdin.readInt() - 1; // 転院先

            // 消し込み
            maxs.put(kinders.get(b[c]).lastKey(), maxs.get(kinders.get(b[c]).lastKey()) - 1);
            if (maxs.get(kinders.get(b[c]).lastKey()) == 0) maxs.remove(kinders.get(b[c]).lastKey());
            kinders.get(b[c]).put(a[c], kinders.get(b[c]).get(a[c]) - 1);
            if (kinders.get(b[c]).get(a[c]) == 0) kinders.get(b[c]).remove(a[c]);
            if (!kinders.get(b[c]).isEmpty()) {
                maxs.put(kinders.get(b[c]).lastKey(), maxs.getOrDefault(kinders.get(b[c]).lastKey(), 0) + 1);
            }

            // 入れ込み
            b[c] = d;
            if (!kinders.get(b[c]).isEmpty())  {
                maxs.put(kinders.get(b[c]).lastKey(), maxs.get(kinders.get(b[c]).lastKey()) - 1);
                if (maxs.get(kinders.get(b[c]).lastKey()) == 0) maxs.remove(kinders.get(b[c]).lastKey());
            }
            kinders.get(b[c]).put(a[c], kinders.get(b[c]).getOrDefault(a[c], 0) + 1);
            maxs.put(kinders.get(b[c]).lastKey(), maxs.getOrDefault(kinders.get(b[c]).lastKey(), 0) + 1);

            stdout.println(maxs.firstKey());
        }
    }

    public static void main(String[] args) {
        Stdin stdin = new Stdin();
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().exec(stdin, stdout);
        stdout.flush();
    }


    public static class Stdin {
        private BufferedReader stdin;
        private Deque<String> tokens;
        private Pattern delim;

        public Stdin() {
            stdin = new BufferedReader(new InputStreamReader(System.in));
            tokens = new ArrayDeque<>();
            delim = Pattern.compile(" ");
        }

        public String nextString() {
            if (tokens.isEmpty()) {
                delim.splitAsStream(readLine()).forEach(tokens::addLast);
            }
            return tokens.pollFirst();
        }

        public int readInt() {
            return Integer.parseInt(nextString());
        }

        private String readLine() {
            try {
                return stdin.readLine();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }
}
