package b;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int k = stdin.nextInt();
        int x = stdin.nextInt();

        Set<Integer> v = new HashSet<>();
        for (int i = 0; i < k; i++) v.add(x + i);
        for (int i = 0; i < k; i++) v.add(x - i);
        String ans = v.stream().sorted().map(i -> i.toString()).collect(Collectors.joining(" "));
        System.out.println(ans);
    }
}
