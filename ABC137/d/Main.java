package d;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int[] a = new int[n]; // a日後
        int[] b = new int[n]; // 報酬
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextInt();
            b[i] = stdin.nextInt();
        }

        PriorityQueue<Integer> q1 = new PriorityQueue<>(Comparator.comparingInt(i -> a[i])); // すぐに報酬がもらえるものから取り出す
        PriorityQueue<Integer> q2 = new PriorityQueue<>(Comparator.comparingInt(i -> b[i] * -1)); // 報酬が大きいものから取り出す。
        IntStream.range(0, n).forEach(q1::add);

        int ans = 0;
        for (int d = m; d >= 0; d--) {
            while (!q1.isEmpty()) {
                int x = q1.peek();
                if (a[x] + d > m) {
                    break;
                }
                q2.add(q1.poll());
            }

            if (!q2.isEmpty()) {
                int x = q2.poll();
                ans += b[x];
            }
        }

        System.out.println(ans);
    }
}
