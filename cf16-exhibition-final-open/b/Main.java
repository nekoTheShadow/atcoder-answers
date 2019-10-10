package b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        double x1 = Double.parseDouble(stdin.next());
        double y1 = Double.parseDouble(stdin.next());
        double x2 = Double.parseDouble(stdin.next());
        double y2 = Double.parseDouble(stdin.next());
        double x3 = Double.parseDouble(stdin.next());
        double y3 = Double.parseDouble(stdin.next());

        double a = Math.hypot(x1 - x2, y1 - y2);
        double b = Math.hypot(x2 - x3, y2 - y3);
        double c = Math.hypot(x3 - x1, y3 - y1);

        double s = Math.abs((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 -y3)); // 三角形の面積 * 2
        double r = s / (a + b + c); // 内接円の半径

        double d = Math.max(a, Math.max(b, c));
        double x = d / (2 + d / r);
        System.out.println(x);
    }
}
