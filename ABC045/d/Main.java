package d;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        long h = stdin.nextLong();
        long w = stdin.nextLong();
        int n = stdin.nextInt();
        
        Map<Tuple, Integer> counter = new HashMap<>();
        int[] d = new int[] { 0, 1, 2 };
        long[] ans = new long[10];
        ans[0] =  (h - 2) * (w - 2);
        
        
        for (int i = 0; i < n; i++) {
            int a = stdin.nextInt() - 1;
            int b = stdin.nextInt() - 1;
            
            for (int dx : d) {
                for (int dy : d) {
                    int x = a - dx;
                    int y = b - dy;
                    if (0 <= x && x < h - 2 && 0 <= y && y < w - 2) {
                        Tuple t = new Tuple(x, y);
                        int before = counter.getOrDefault(t, 0);
                        int after = before + 1;
                        
                        counter.put(t, after);
                        
                        ans[before]--;
                        ans[after]++;
                    }
                }
            }
        }
        
        Arrays.stream(ans).forEach(System.out::println);
    }
    
    public static class Tuple {
        private int x;
        private int y;
        
        public Tuple(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Tuple)) {
                return false;
            }
            
            Tuple other = (Tuple) obj;
            return this.x == other.x && this.y == other.y;
        }
    }
}
