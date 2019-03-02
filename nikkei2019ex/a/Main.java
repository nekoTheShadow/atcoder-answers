package nikkei2019ex.a;

import java.util.Scanner;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String s = stdin.nextLine();
        LongStream.rangeClosed(1, s.length()).forEach(System.out::println);
    }
}
