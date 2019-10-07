package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(stdin.readLine());
        int[] a = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();

        int[] b = new int[n + 1];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) b[a[i]] = i + 1;
        set.add(0);
        set.add(n + 1);

        long ans = 0L;
        for (int i = 1; i <= n; i++) {
            long lo = set.lower(b[i]);
            long hi = set.higher(b[i]);
            ans += (b[i] - lo) * (hi - b[i]) * i;
            set.add(b[i]);
        }
        System.out.println(ans);
    }
}
