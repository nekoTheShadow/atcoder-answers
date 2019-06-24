package d;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextInt();
            b[i] = stdin.nextInt();
        }

        // 締め切りの若い順番に仕事を進める。
        int[] idxs = IntStream.range(0, n)
                              .boxed()
                              .sorted(Comparator.comparing(i -> b[i]))
                              .mapToInt(Integer::intValue)
                              .toArray();
        int t = 0;
        boolean ok = true;
        for (int idx : idxs) {
            t += a[idx];
            if (b[idx] < t) {
                ok = false;
                break;
            } 
        }
        System.out.println(ok ? "Yes" : "No");
    }
}