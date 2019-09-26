package d;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        for (int i = 1; i <= k; i++) {
            System.out.println(c(k - 1, i - 1).multiply(c(n - k + 1, i)).mod(mod).intValue());
        }
    }

    private static List<BigInteger> fct = new ArrayList<>();
    private static List<BigInteger> inv = new ArrayList<>();
    private static BigInteger mod = BigInteger.valueOf(1000000007);
    private static BigInteger c(int n, int r) {
        if (n < r) {
            return BigInteger.ZERO;
        }

        if (fct.isEmpty()) {
            fct.add(BigInteger.ONE);
            inv.add(fct.get(0).modInverse(mod));
        }

        for (int i = fct.size(); i <= n; i++) {
            fct.add(fct.get(i - 1).multiply(BigInteger.valueOf(i)).mod(mod));
            inv.add(fct.get(i).modInverse(mod));
        }

        return fct.get(n).multiply(inv.get(n - r).multiply(inv.get(r)).mod(mod)).mod(mod);
    }
}
