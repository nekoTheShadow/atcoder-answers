package c.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int[] line = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
        int n = line[0];
        int a = line[1];
        int b = line[2];
        int c = line[3];

        int mod = this.mod.intValue();
        int sum = 0;
        for (int m = n; m < 2 * n; m++) {
            int numer = (modPow(a, n) * modPow(b, m - n) % mod + modPow(b, n) * modPow(a, m - n) % mod) * m * 100 % mod;
            int denom = (100 - c) * modPow(a + b, m) % mod;
            stdout.println(c(m - 1, n - 1));
            stdout.println(numer);
            stdout.println(denom);
            stdout.println(modInverse(denom));
            sum += c(m - 1, n - 1) * numer % mod * modInverse(denom) % mod;
            sum %= mod;
        }
        stdout.println(sum);
    }

    private List<BigInteger> fct = new ArrayList<>();
    private List<BigInteger> inv = new ArrayList<>();
    private BigInteger mod = BigInteger.valueOf(1000000007);
    private int c(int n, int r) {
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

    private int modPow(int x, int y) {
        return BigInteger.valueOf(x).modPow(BigInteger.valueOf(y), mod).intValue();
    }

    private int modInverse(int x) {
        return BigInteger.valueOf(x).modInverse(mod).intValue();
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
