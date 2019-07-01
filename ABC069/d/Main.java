package d;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int h = stdin.nextInt();
        int w = stdin.nextInt();
        int n = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextInt();
        }
        
        int[] color = new int[h * w];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < a[i]; j++) {
                color[k + j] = i + 1;
            }
            k += a[i];
        }

        int[][] matrix = new int[h][w];
        int x = 0;
        int y = 0;
        int d = 1;
        for (int i = 0; i < h * w; i++) {
            matrix[x][y] = color[i];
            if (d == 1 && y == w - 1) {
                x += 1;
                d = -1;
            } else if (d == -1 && y == 0) {
                x += 1;
                d = 1;
            } else {
                y += d;
            }
        }
        
        for (int i = 0; i < h; i++) {
            String line = Arrays.stream(matrix[i]).mapToObj(Integer::toString).collect(Collectors.joining(" "));
            System.out.println(line);
        }
    }
}
