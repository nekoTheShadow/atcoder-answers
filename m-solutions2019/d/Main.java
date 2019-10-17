package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());

        Pattern space = Pattern.compile(" ");
        Map<Integer, List<Integer>> nexts = new HashMap<>();
        int[] a = new int[n - 1];
        int[] b = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            int[] line = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
            a[i] = line[0] - 1;
            b[i] = line[1] - 1;
            nexts.computeIfAbsent(a[i], unused -> new ArrayList<>()).add(b[i]);
            nexts.computeIfAbsent(b[i], unused -> new ArrayList<>()).add(a[i]);
        }
        Deque<Integer> c = space.splitAsStream(stdin.readLine())
                                .map(Integer::new)
                                .sorted(Comparator.reverseOrder())
                                .collect(Collectors.toCollection(ArrayDeque::new));

        int[] score = new int[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer current = queue.pollFirst();
            score[current] = c.pollFirst();
            for (Integer next : nexts.get(current)) {
                if (score[next] == 0) {
                    queue.addLast(next);
                }
            }
        }

        int sum = IntStream.range(0, n - 1).map(i -> Math.min(score[a[i]], score[b[i]])).sum();
        String line = Arrays.stream(score).mapToObj(Integer::toString).collect(Collectors.joining(" "));
        stdout.println(sum);
        stdout.println(line);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
