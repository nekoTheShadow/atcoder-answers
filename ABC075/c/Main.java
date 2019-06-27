package c;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int[] a = new int[m];
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = stdin.nextInt() - 1;
            b[i] = stdin.nextInt() - 1;
        }
        
        int ans = 0;
        for (int skip = 0; skip < m; skip++) {
            UnionFind uf = new UnionFind(n);
            for (int i = 0; i < m; i++) {
                if (i != skip) {
                    uf.union(a[i], b[i]);
                }
            }
            
            long c = IntStream.range(0, n).map(uf::find).boxed().collect(Collectors.toSet()).size();
            if (c > 1) {
                ans++;
            }
        }
        
        System.out.println(ans);
    }
    
    private static class UnionFind {
        private int[] parent;
        
        public UnionFind(int n) {
            parent = IntStream.range(0, n).toArray();
        }
        
        public int find(int x) {
            if (x == parent[x]) {
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
    }
}