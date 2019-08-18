package c;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int w = stdin.nextInt();
        int h = stdin.nextInt();
        
        int x = w + h - 2;
        int y = Math.min(w - 1, h - 1);
        
        BigInteger m = BigInteger.valueOf(1000000007);
        BigInteger[] fac = new BigInteger[x + 1];
        BigInteger[] inv = new BigInteger[x + 1];
        fac[0] = inv[0] = BigInteger.ONE;
        for (int i = 1; i < fac.length; i++) {
            fac[i] = fac[i - 1].multiply(BigInteger.valueOf(i)).mod(m);
            inv[i] = fac[i].modInverse(m);
        }
        
        // x C y = x! / (x - y)! * y!
        BigInteger ans = fac[x].multiply(inv[x - y].multiply(inv[y]).mod(m)).mod(m);
        System.out.println(ans);
    }
}
