package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int[] first = Arrays.stream(stdin.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = first[0];
        int m = first[1];
        
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < m; i++) {
            int[] line = Arrays.stream(stdin.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = line[0] - 1;
            int y = line[1] - 1;
            int z = line[2];
            
            uf.union(x, y);
        }
        
        int ans = IntStream.range(0, n).map(uf::find).boxed().collect(Collectors.toSet()).size();
        System.out.println(ans);
    }
    
    private static class UnionFind {
        private int[] parents;

        public UnionFind(int n) {
            parents = IntStream.range(0, n).toArray();
        }
        
        public int find(int x) {
            if (parents[x] == x) {
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
                parents[y] = x; 
            }
        }
    }
}
