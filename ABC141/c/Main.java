package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int[] tokens = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
        int n = tokens[0];
        int k = tokens[1];
        int q = tokens[2];
        
        int[] score = new int[n];
        for (int i = 0; i < q; i++) {
            int a = Integer.parseInt(stdin.readLine()) - 1;
            score[a]++;
        }
        
        for (int i = 0; i < n; i++) {
            if (k - q + score[i] > 0) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
