package d;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int[] p = new int[n];
        int[] x = new int[m];
        int[] y = new int[m];
        for (int i = 0; i < n; i++) {
            p[i] = stdin.nextInt() - 1;
        }
        for (int i = 0; i < m; i++) {
            x[i] = stdin.nextInt() - 1;
            y[i] = stdin.nextInt() - 1;
        }

        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < m; i++) {
            uf.union(x[i], y[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (uf.same(i, p[i])) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static final class UnionFind {
        private int[] parent;

        public UnionFind(int size) {
            this.parent = IntStream.range(0, size).toArray();
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            } else {
                parent[x] = find(parent[x]);
                return parent[x];
            }
        }

        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y) {
                parent[x] = y;
            }
        }

        public boolean same(int x, int y) {
            return find(x) == find(y);
        }
    }
}
