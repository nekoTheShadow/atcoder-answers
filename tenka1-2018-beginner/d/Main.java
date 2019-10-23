package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());

        if (n == 1) {
            stdout.println("Yes");
            stdout.println("2");
            stdout.println("1 1");
            stdout.println("1 1");
            return ;
        }

        int h = 1;
        int sum = 1;
        while (true) {
            if (sum == n) {
                break;
            }

            if (n < sum) {
                stdout.println("No");
                return ;
            }

            h++;
            sum += h;
        }

        int v = 1;
        int[][] mat = new int[h][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < i + 1; j++) {
                mat[i][j] = v++;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(IntStream.range(0, h).map(i -> mat[i][0]).boxed().collect(Collectors.toList()));
        ans.add(Arrays.stream(mat[h - 1]).boxed().collect(Collectors.toList()));
        ans.add(IntStream.range(0, h).map(i -> mat[i][i]).boxed().collect(Collectors.toList()));
        for (int i = 1; i < h - 1; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < i; j++) row.add(mat[i][j]);
            for (int j = i; j < h; j++) row.add(mat[j][i]);
            ans.add(row);
        }

        stdout.println("Yes");
        stdout.println(ans.size());
        for (List<Integer> row : ans) {
            String line = row.stream().map(Objects::toString).collect(Collectors.joining(" "));
            stdout.printf("%d %s%n", row.size(), line);
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
