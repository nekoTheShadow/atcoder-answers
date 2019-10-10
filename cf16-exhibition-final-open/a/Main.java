package a;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = Integer.parseInt(stdin.nextLine());
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) points.add(new Point(Integer.parseInt(stdin.nextLine()), true));
        for (int i = 0; i < n; i++) points.add(new Point(Integer.parseInt(stdin.nextLine()), false));
        Collections.sort(points, Comparator.comparing(point -> point.x));

        long ans = 1;
        long mod = 1_000_000_000 + 7;
        Deque<Point> pcs = new ArrayDeque<>();
        Deque<Point> powers = new ArrayDeque<>();
        for (Point point : points) {
            if (point.pc) {
                if (powers.isEmpty()) {
                    pcs.addLast(point);
                } else {
                    ans = (ans * powers.size()) % mod;
                    powers.pollLast();

                }
            } else {
                if (pcs.isEmpty()) {
                    powers.addLast(point);
                } else {
                    ans = (ans * pcs.size()) % mod;
                    pcs.pollLast();
                }
            }
        }

        System.out.println(ans);
    }

    private static class Point {
        private int x;
        private boolean pc;

        public Point(int x, boolean pc) {
            super();
            this.x = x;
            this.pc = pc;
        }
    }
}
