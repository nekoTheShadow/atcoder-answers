package d;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        long[] t = new long[m];
        long[] l = new long[m];
        long[] r = new long[m];
        for (int i = 0; i < m; i++) {
            t[i] = stdin.nextLong();
            l[i] = stdin.nextLong() - 1;
            r[i] = stdin.nextLong() - 1;
        }
        
        TreeSet<Long> bambooes = LongStream.range(0, n).boxed().collect(Collectors.toCollection(TreeSet::new));
        long ans = 0;
        for (int i = m - 1; i >= 0; i--) {
            List<Long> removals = new ArrayList<>(bambooes.subSet(l[i], r[i] + 1));
            ans += removals.size() * t[i];
            bambooes.removeAll(removals);
        }
        
        System.out.println(ans);
    }
}
