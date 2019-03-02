package nikkei2019ex.e;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        IntStream.rangeClosed(1, n).mapToObj(Main::superFizzBuzz).forEach(System.out::println);
    }
    
    public static String superFizzBuzz(int n) {
        List<String> words = new ArrayList<>();
        if (n % 2 == 0) words.add("a");
        if (n % 3 == 0) words.add("b");
        if (n % 4 == 0) words.add("c");
        if (n % 5 == 0) words.add("d");
        if (n % 6 == 0) words.add("e");
        return words.isEmpty() ? Integer.toString(n) : String.join("", words);
    }
}
