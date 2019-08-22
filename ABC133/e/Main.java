package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        int n = stdin.nextInt();
        int k = stdin.nextInt();

        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int a = stdin.nextInt() - 1;
            int b = stdin.nextInt() - 1;
            g.get(a).add(b);
            g.get(b).add(a);
        }

        long[] ans = new long[n];
        ans[0] = k;
        Deque<Tuple> stack = new ArrayDeque<>();
        stack.add(new Tuple(0, 0, -1));
        while (!stack.isEmpty()) {
            Tuple current = stack.pollLast();
            int brother = 0;
            for (int child : g.get(current.node)) {
                if (child == current.parent) continue;

                ans[child] = (current.depth == 0) ? k - brother - 1 : k - brother - 2;
                stack.add(new Tuple(child, current.depth + 1, current.node));
                brother++;
            }
        }

        BigInteger mod = BigInteger.valueOf(1_000_000_007);
        long sum = Arrays.stream(ans).mapToObj(BigInteger::valueOf).reduce(BigInteger.ONE, (x, y) -> x.multiply(y).mod(mod)).longValue();
        System.out.println(sum);
    }

    private static final class Tuple {
        private int node;
        private int depth;
        private int parent;
        public Tuple(int node, int depth, int parent) {
            super();
            this.node = node;
            this.depth = depth;
            this.parent = parent;
        }
    }


    private static final class Stdin {
        private BufferedReader stdin;
        private ArrayDeque<String> tokens;

        public Stdin() {
            this.stdin = new BufferedReader(new InputStreamReader(System.in));
            this.tokens = new ArrayDeque<>();
        }

        public String nextToken() throws IOException {
            if (tokens.isEmpty()) {
                for (String token : stdin.readLine().split(" ")) {
                    tokens.add(token);
                }
            }
            return tokens.poll();
        }

        public int nextInt() throws NumberFormatException, IOException {
            return Integer.parseInt(nextToken());
        }

        public int nextLong() throws NumberFormatException, IOException {
            return Integer.parseInt(nextToken());
        }
    }
}
