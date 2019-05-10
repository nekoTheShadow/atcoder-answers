package d;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        long n = stdin.nextLong();
        long ans = (pow(5, n - 1) * 8) % div;
        System.out.println(ans);
    }
    
    public static long div = 1000000007;
    
    private static Map<Long, Map<Long, Long>> memo = new HashMap<>();
    
    public static long pow(long x, long y) {
        if (y == 0) {
            return 1;
        }
        
        memo.putIfAbsent(x, new HashMap<>());
        
        if (memo.get(x).containsKey(y)) {
            return memo.get(x).get(y);
        }
        
        long v;
        if (y % 2 == 0) {
            v = ((pow(x, y / 2) % div) * (pow(x, y / 2) % div)) % div;
        } else {
            v = ((x % div) * (pow(x, y - 1) % div)) % div;
        }
        memo.get(x).put(y, v);
        
        return v;
    }
}
