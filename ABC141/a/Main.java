package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        List<String> weathers = Arrays.asList("Sunny", "Cloudy", "Rainy");
        int x = (weathers.indexOf(s) + 1) % 3;
        System.out.println(weathers.get(x));
    }
}
