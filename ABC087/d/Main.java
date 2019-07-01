package d;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int[] l = new int[m];
        int[] r = new int[m];
        long[] d = new long[m];
        for (int i = 0; i < m; i++) {
            l[i] = stdin.nextInt() - 1;
            r[i] = stdin.nextInt() - 1;
            d[i] = stdin.nextInt();
        }
        
        UnionFind uf = new UnionFind(n);
        boolean ok = true;
        for (int i = 0; i < m; i++) {
            if (uf.isSame(l[i], r[i])) {
                if (uf.diff(l[i], r[i]) != d[i]) {
                    ok = false;
                    break;
                }
            } else {
                uf.union(l[i], r[i], d[i]);
            }
        }
        
        System.out.println(ok ? "Yes" : "No");
    }
    
    private static class UnionFind {
        private int[] parent;
        private long[] weight;
        
        public UnionFind(int n) {
            parent = IntStream.range(0, n).toArray();
            weight = new long[n]; // filled by zero.
        }
        
        public int find(int x) {
            if (parent[x] == x) {
                return x;
            } else {
                int y = find(parent[x]);
                weight[x] += weight[parent[x]];
                parent[x] = y;
                return y;
            }
        }
        
        public void union(int x, int y, long w) {
            int rx = find(x);
            int ry = find(y);
            if (rx != ry) {
                parent[rx] = ry;
                weight[rx] = w - weight[x] + weight[y];
            }
        }
        
        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }
        
        public long diff(int x, int y) {
            return weight[x] - weight[y];
        }
    }
}
