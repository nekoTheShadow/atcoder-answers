package d;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int h = stdin.nextInt();
        int w = stdin.nextInt();
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        
        long ans = 0;
        for (int i = 0; i < h - a; i++) {
            ans += (c(b - 1 + i,b - 1) * c(w - 1 - b + h - 1 - i, w - 1 - b)) % mod;
            ans %= mod;
        }
        
        System.out.println(ans);
    }
    
    private static List<BigInteger> fcts = new ArrayList<>();
    private static List<BigInteger> invs = new ArrayList<>();
    private static BigInteger m = BigInteger.valueOf(1000000007);
    private static long mod = m.longValue();
    
    public static long c(int n, int k) {
        if (fcts.isEmpty()) {
            fcts.add(BigInteger.ONE);
            invs.add(BigInteger.ONE);
        }
        
        for (int i = fcts.size(); i <= n; i++) {
            BigInteger x = BigInteger.valueOf(i);
            BigInteger fct = fcts.get(i - 1);
            BigInteger inv = invs.get(i - 1);
            fcts.add(fct.multiply(x).mod(m));
            invs.add(inv.multiply(x.modInverse(m)).mod(m));
        }
        
        return (fcts.get(n).multiply(invs.get(k).multiply(invs.get(n - k)))).mod(m).longValue();
    }
}
