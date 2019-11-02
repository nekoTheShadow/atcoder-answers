package e;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        Pattern space = Pattern.compile(" ");
        int[] first = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
        int n = first[0];
        int m = first[1];

        Map<Tuple, List<Tuple>> nexts = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int[] line = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
            int u = line[0];
            int v = line[1];
            nexts.computeIfAbsent(new Tuple(u, 0), unused -> new ArrayList<>()).add(new Tuple(v, 1));
            nexts.computeIfAbsent(new Tuple(u, 1), unused -> new ArrayList<>()).add(new Tuple(v, 2));
            nexts.computeIfAbsent(new Tuple(u, 2), unused -> new ArrayList<>()).add(new Tuple(v, 0));
        }

        int[] line = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
        int s = line[0];
        int t = line[1];

        Deque<Tuple> q = new ArrayDeque<>();
        Map<Tuple, Integer> score = new HashMap<>();
        Tuple start = new Tuple(s, 0);
        q.addLast(start);
        score.put(start, 0);
        while (!q.isEmpty()) {
            Tuple current = q.pollFirst();
            if (!nexts.containsKey(current)) {
                continue;
            }

            for (Tuple next : nexts.get(current)) {
                if (score.get(current) + 1 < score.getOrDefault(next, Integer.MAX_VALUE)) {
                    score.put(next, score.get(current) + 1);
                    q.addLast(next);
                }
            }
        }

        Tuple goal = new Tuple(t, 0);
        if (score.containsKey(goal)) {
            stdout.println(score.get(goal) / 3);
        } else {
            stdout.println(-1);
        }
    }

    private static class Tuple {
        private int node;
        private int mod;

        public Tuple(int node, int mod) {
            this.node = node;
            this.mod = mod;
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, mod);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Tuple)) {
                return false;
            }

            Tuple other = (Tuple) obj;
            return this.node == other.node && this.mod == other.mod;
        }
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
