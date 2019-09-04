package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        int n = stdin.nextInt();
        int m = stdin.nextInt();

        // key=言語 vals=使用者
        List<List<Integer>> d = IntStream.range(0, m).mapToObj(unused -> new ArrayList<Integer>()).collect(Collectors.toList());
        for (int i = 0; i < n; i++) {
            int k = stdin.nextInt();
            for (int j = 0; j < k; j++) {
                int l = stdin.nextInt() - 1;
                d.get(l).add(i);
            }
        }

        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < m; i++) {
            List<Integer> vals = d.get(i);
            for (int j = 1; j < vals.size(); j++) {
                uf.union(vals.get(0), vals.get(j));
            }
        }

        long count = IntStream.range(0, n).map(uf::find).distinct().count();
        String ans = (count == 1) ? "YES" : "NO";
        System.out.println(ans);
    }

    private static class UnionFind {
        private int[] parents;

        public UnionFind(int size) {
            parents = IntStream.range(0, size).toArray();
        }

        public int find(int x) {
            if (x == parents[x]) {
                return x;
            } else {
                parents[x] = find(parents[x]);
                return parents[x];
            }
        }

        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y) {
                parents[x] = y;
            }
        }

    }

    private static final class Stdin {
        private BufferedReader stdin;
        private ArrayDeque<String> tokens;

        public Stdin() {
            this.stdin = new BufferedReader(new InputStreamReader(System.in));
            this.tokens = new ArrayDeque<>();
        }

        public String nextToken() throws IOException {
            if (tokens.isEmpty()) {
                for (String token : stdin.readLine().split(" ")) {
                    tokens.add(token);
                }
            }
            return tokens.poll();
        }

        public int nextInt() throws NumberFormatException, IOException {
            return Integer.parseInt(nextToken());
        }

        public int nextLong() throws NumberFormatException, IOException {
            return Integer.parseInt(nextToken());
        }
    }
}
