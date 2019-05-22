package a;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int l = stdin.nextInt();
        List<String> s = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            s.add(stdin.next());
        }
        
        String ans = s.stream().sorted().collect(Collectors.joining());
        System.out.println(ans);
    }

}
