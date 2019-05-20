package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String[] line = stdin.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        String s = stdin.readLine();
        
        char[] t = s.toCharArray();
        t[k - 1] = Character.toLowerCase(t[k - 1]);
        System.out.println(new String(t));
    }
}
