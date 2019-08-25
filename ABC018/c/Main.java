package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        int r = stdin.nextInt() + 2;
        int c = stdin.nextInt() + 2;
        int k = stdin.nextInt();
        char[][] s = new char[r][c];
        for (char[] row : s) Arrays.fill(row, 'x');
        for (int i = 1; i <= r - 2; i++) {
            char[] line = stdin.nextToken().toCharArray();
            for (int j = 0; j < line.length; j++) s[i][j + 1] = line[j];
        }
        
        int[][] score = new int[r][c];
        Deque<Tuple> q = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (s[i][j] == 'o') {
                    score[i][j] = Integer.MAX_VALUE;
                } else {
                    q.add(new Tuple(i, j));
                }
            }
        }
        
        List<Tuple> diffs = Arrays.asList(
            new Tuple( 0,  1), 
            new Tuple( 0, -1), 
            new Tuple( 1,  0), 
            new Tuple(-1,  0)
        );
        
        while (!q.isEmpty()) {
            Tuple t = q.poll();
            for (Tuple diff : diffs) {
                int x = t.x + diff.x;
                int y = t.y + diff.y;
                if (0 <= x && x < r 
                        && 0 <= y && y < c 
                        && s[x][y] == 'o' 
                        && score[t.x][t.y] + 1 < score[x][y]) {
                    score[x][y] = score[t.x][t.y] + 1;
                    q.add(new Tuple(x, y));
                }
            }
        }
        
        int count = 0;
        for (int[] row : score) {
            for (int v : row) {
                if (v >= k) count++;
            }
        }
        System.out.println(count);
    }
    
    private static class Tuple {
        private int x;
        private int y;
        
        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
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
