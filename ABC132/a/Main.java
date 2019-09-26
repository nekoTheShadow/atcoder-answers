package a;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        List<Long> s = stdin.nextLine()
                            .chars()
                            .boxed()
                            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                            .values()
                            .stream()
                            .collect(Collectors.toList());
        String ans = s.equals(Arrays.asList(2L, 2L)) ? "Yes" : "No";
        System.out.println(ans);
    }

}
