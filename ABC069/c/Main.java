package c;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        int[] a = Arrays.stream(stdin.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        Queue<Integer> x = new ArrayDeque<>(); // 奇数
        Queue<Integer> y = new ArrayDeque<>(); // 2の倍数だが4の倍数ではない
        Queue<Integer> z = new ArrayDeque<>(); // 4の倍数
        
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                if (a[i] % 4 == 0) {
                    z.add(a[i]);
                } else {
                    y.add(a[i]);
                }
            } else {
                x.add(a[i]);
            }
        }
        
        List<Integer> b = new ArrayList<>();
        while (!(x.isEmpty() && z.isEmpty())) {
            if (!x.isEmpty()) {
                b.add(x.poll());
            }
            
            if (!z.isEmpty()) {
                b.add(z.poll());
            }
        }
        b.addAll(y);
        
        boolean ok = IntStream.range(0, n - 1).allMatch(i -> (b.get(i) * b.get(i + 1)) % 4 == 0);
        System.out.println(ok ? "Yes" : "No");
    }
}
