package b;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        int[] p = Pattern.compile(" ").splitAsStream(stdin.nextLine()).mapToInt(Integer::parseInt).toArray();

        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            int[] q = IntStream.of(p[i], p[i + 1], p[i + 2]).sorted().toArray();
            if (p[i + 1] == q[1]) ans++;
        }

        System.out.println(ans);
    }

}
