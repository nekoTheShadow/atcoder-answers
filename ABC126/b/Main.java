package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        
        int a = Integer.parseInt(s.substring(0, 2));
        int b = Integer.parseInt(s.substring(2));
        boolean yymm = 01 <= b && b <= 12;
        boolean mmyy = 01 <= a && a <= 12;
        
        String ans = (yymm && mmyy) ? "AMBIGUOUS" :
                      yymm          ? "YYMM"      :
                      mmyy          ? "MMYY"      : "NA";
        System.out.println(ans);
    }
}
