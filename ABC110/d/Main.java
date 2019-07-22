package d;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();

        Map<Integer, Integer> d = new HashMap<>();
        int t = m;
        for (int x = 2; x * x <= t; x++) {
            while (t % x == 0) {
                d.put(x, d.getOrDefault(x, 0) + 1);
                t /= x;
            }
        }
        if (t > 1) {
            d.put(t, d.getOrDefault(t, 0) + 1);
        }

        BigInteger ans = BigInteger.ONE;
        for (int v : d.values()) {
            ans = ans.multiply(c(n + v - 1, v)).mod(mod);
        }
        System.out.println(ans);
    }

    private static List<BigInteger> fct = new ArrayList<>();
    private static List<BigInteger> inv = new ArrayList<>();
    private static BigInteger mod = BigInteger.valueOf(1000000007);
    private static BigInteger c(int n, int r) {
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