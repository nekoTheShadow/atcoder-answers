package c;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        int[] a = Pattern.compile(" ").splitAsStream(stdin.nextLine()).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(a);

        List<List<Integer>> history = new ArrayList<>();
        int lo = a[0];
        int hi = a[n - 1];
        for (int i = 1; i < n - 1; i++) {
            if (a[i] > 0) {
                history.add(Arrays.asList(lo, a[i]));
                lo = lo - a[i];
            } else {
                history.add(Arrays.asList(hi, a[i]));
                hi = hi - a[i];
            }
        }
        history.add(Arrays.asList(hi, lo));

        PrintWriter out = new PrintWriter(System.out, false);
        out.println(hi - lo);
        for (List<Integer> row : history) {
            out.printf("%d %d%n", row.get(0), row.get(1));
        }
        out.flush();
    }
}
