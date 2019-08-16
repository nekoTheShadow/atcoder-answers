package d;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String s = stdin.next();
        int x = stdin.nextInt();
        int y = stdin.nextInt();

        int[] t = Arrays.stream(s.split("T")).mapToInt(String::length).toArray();
        int[] vx = IntStream.range(0, t.length).filter(i -> i % 2 == 0).map(i -> t[i]).toArray();
        int[] vy = IntStream.range(0, t.length).filter(i -> i % 2 != 0).map(i -> t[i]).toArray();

        if (ok(vx, x, true) && ok(vy, y, false)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static boolean ok(int[] v, int z, boolean first) {
        Set<Integer> s = new HashSet<>();
        s.add(0);
        for (int i = 0; i < v.length; i++) {
            int d = v[i];
            if (i == 0 && first) {
                s = s.stream().flatMap(x -> Stream.of(x + d)).collect(Collectors.toSet());
            } else {
                s = s.stream().flatMap(x -> Stream.of(x + d, x - d)).collect(Collectors.toSet());
            }
        }
        return s.contains(z);
    }
}

