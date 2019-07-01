package b;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int h = stdin.nextInt();
        int w = stdin.nextInt();
        char[][] s = new char[h][w];
        for (int i = 0; i < h; i++) {
            s[i] = stdin.next().toCharArray();
        }
        
        int[] d = {-1, 0, 1};
        int[][] t = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (s[i][j] == '#') {
                    continue;
                }
                
                for (int dx : d) {
                    for (int dy : d) {
                        int x = i + dx;
                        int y = j + dy;
                        if (0 <= x && x < h && 0 <= y && y < w && s[x][y] == '#') {
                            t[i][j]++;
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (s[i][j] == '#') {
                    System.out.print(s[i][j]);
                } else {
                    System.out.print(t[i][j]);
                }
            }
            System.out.println();
        }
    }
}