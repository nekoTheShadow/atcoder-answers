package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        List<Integer> l = Pattern.compile(" ").splitAsStream(stdin.readLine()).map(Integer::valueOf).sorted().collect(Collectors.toList());
        Collections.sort(l);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = l.get(i);
                int b = l.get(j);

                int k = Collections.binarySearch(l, a + b, (p, q) -> p.compareTo(q) >= 0 ? 1 : -1);
                if (k < 0) k = (k + 1) * -1;
                ans += Math.max(0, k - (j + 1));
            }
        }
        stdout.println(ans);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
