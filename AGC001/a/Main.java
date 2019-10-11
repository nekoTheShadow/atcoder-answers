package a;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = Integer.parseInt(stdin.nextLine());
        int[] l = Pattern.compile(" ").splitAsStream(stdin.nextLine()).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(l);
        int ans = IntStream.range(0, l.length).filter(i -> i % 2 == 0).map(i -> l[i]).sum();
        System.out.println(ans);
    }
}
