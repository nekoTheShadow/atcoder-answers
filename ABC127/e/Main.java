package e;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int k = stdin.nextInt();
        
        long ans = 0;
        int t = c(n * m - 2, k - 2);
        for (long i = 0; i < n; i++) ans = (ans + ((n - i) * m * m * i) % 1000000007) % 1000000007;
        for (long i = 0; i < m; i++) ans = (ans + ((m - i) * n * n * i) % 1000000007) % 1000000007;
        System.out.println((ans * t) % 1000000007);
    }
    
    private static List<BigInteger> fct = new ArrayList<>();
    private static List<BigInteger> inv = new ArrayList<>();
    private static BigInteger mod = BigInteger.valueOf(1000000007);
    private static int c(int n, int r) {
        if (fct.isEmpty()) {
            fct.add(BigInteger.ONE);
            inv.add(fct.get(0).modInverse(mod));
        }

        for (int i = fct.size(); i <= n; i++) {
            fct.add(fct.get(i - 1).multiply(BigInteger.valueOf(i)).mod(mod));
            inv.add(fct.get(i).modInverse(mod));
        }

        return fct.get(n).multiply(inv.get(n - r).multiply(inv.get(r)).mod(mod)).mod(mod).intValue();
    }
}
