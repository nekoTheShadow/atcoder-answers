package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        Pattern space = Pattern.compile(" ");
        int[] first = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
        int n = first[0];
        int m = first[1];

        List<Set<Integer>> parents = IntStream.range(0, n).mapToObj(unused -> new HashSet<Integer>()).collect(Collectors.toList());
        List<Set<Integer>> children = IntStream.range(0, n).mapToObj(unused -> new HashSet<Integer>()).collect(Collectors.toList());

        for (int i = 0; i < n - 1 + m; i++) {
            int[] line = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
            int a = line[0] - 1;
            int b = line[1] - 1;
            parents.get(b).add(a);
            children.get(a).add(b);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        int[] answers = new int[n];
        Integer root = IntStream.range(0, n).filter(i -> parents.get(i).isEmpty()).boxed().findAny().get();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            Integer parent = queue.pollFirst();
            for (Integer child : children.get(parent)) {
                parents.get(child).remove(parent);
                if (parents.get(child).isEmpty()) {
                    answers[child.intValue()] = parent.intValue() + 1;
                    queue.add(child);
                }
            }
        }

        Arrays.stream(answers).forEach(stdout::println);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
