package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public void solve(final BufferedReader stdin, final PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        String[] w = stdin.readLine().split(" ");

        Map<Character, Integer> d = new HashMap<>();
        d.put('b', 1);
        d.put('c', 1);
        d.put('d', 2);
        d.put('w', 2);
        d.put('t', 3);
        d.put('j', 3);
        d.put('f', 4);
        d.put('q', 4);
        d.put('l', 5);
        d.put('v', 5);
        d.put('s', 6);
        d.put('x', 6);
        d.put('p', 7);
        d.put('m', 7);
        d.put('h', 8);
        d.put('k', 8);
        d.put('n', 9);
        d.put('g', 9);
        d.put('z', 0);
        d.put('r', 0);

        List<String> answers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> answer = new ArrayList<>();
            for (int j = 0; j < w[i].length(); j++) {
                char c = Character.toLowerCase(w[i].charAt(j));
                if (d.containsKey(c)) {
                    answer.add(d.get(c));
                }
            }

            if (!answer.isEmpty()) {
                String line = answer.stream().map(Objects::toString).collect(Collectors.joining());
                answers.add(line);
            }
        }

        stdout.println(String.join(" ", answers));
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
