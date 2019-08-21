package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        
        int max = (n - 1) * (n - 2) / 2;
        if (max < k) {
            System.out.println(-1);
            System.exit(0);
        }
        
        List<Integer> l = new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            l.add(1);
            r.add(i + 2);
        }
        
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                x.add(i);
                y.add(j);
            }
        }
        
        for (int i = 0; i < max - k; i++) {
            l.add(x.get(i));
            r.add(y.get(i));
        }
        
        System.out.println(l.size());
        for (int j = 0; j < l.size(); j++) {
            System.out.printf("%d %d%n", l.get(j), r.get(j));
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
