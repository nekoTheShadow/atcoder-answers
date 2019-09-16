package c;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(stdin.nextLine());
        
        List<Integer> b = Arrays.stream(a).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < n; i++) {
            if (a[i] == b.get(0)) {
                System.out.println(b.get(1));
            } else {
                System.out.println(b.get(0));
            }
        }
    }

}
