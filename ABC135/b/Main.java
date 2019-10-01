package b;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        long[] p = Pattern.compile(" ").splitAsStream(stdin.nextLine()).mapToLong(Long::parseLong).toArray();


        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long[] q = p.clone();
                long temp = q[i];
                q[i] = q[j];
                q[j] = temp;
                if (isSorted(q)) echo("YES");
            }
        }
        echo(isSorted(p) ? "YES" : "NO");
    }

    private static boolean isSorted(long[] p) {
        for (int i = 0; i < p.length - 1; i++) {
            if (p[i] > p[i + 1]) return false;
        }
        return true;
    }

    private static void echo(String msg) {
        System.out.println(msg);
        System.exit(0);
    }

}
