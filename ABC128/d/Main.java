package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        
        String[] first = stdin.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int q = Integer.parseInt(first[1]);
        
        int[] s = new int[n];
        int[] t = new int[n];
        int[] x = new int[n];
        int[] d = new int[q];
        for (int i = 0; i < n; i++) {
            String[] line = stdin.readLine().split(" ");
            s[i] = Integer.parseInt(line[0]);
            t[i] = Integer.parseInt(line[1]);
            x[i] = Integer.parseInt(line[2]);
        }
        for (int i = 0; i < q; i++) {
            d[i] = Integer.parseInt(stdin.readLine());
        }
        
        
        int[] answers = new int[q];
        Arrays.fill(answers, -1);
        TreeSet<Tuple> tuples = new TreeSet<>();
        for (int i = 0; i < q; i++) {
            tuples.add(new Tuple(i, d[i]));
        }
        int[] sections = IntStream.range(0, n)
                                  .boxed()
                                  .sorted(Comparator.comparingInt(i -> x[i]))
                                  .mapToInt(Integer::intValue)
                                  .toArray();
        for (int section : sections) {
            Tuple from = new Tuple(-1, s[section] - x[section]);
            Tuple to = new Tuple(-1, t[section] - x[section] );
            List<Tuple> removals = new ArrayList<>(tuples.subSet(from, to));
            for (Tuple tuple : removals) {
                answers[tuple.i] = x[section];
                tuples.remove(tuple);
            }
        }
        
        for (int answer : answers) {
            System.out.println(answer);
        }
    }

    private static class Tuple implements Comparable<Tuple> {
        private int i;
        private int d;
        
        public Tuple(int i, int d) {
            this.i = i;
            this.d = d;
        }
        
        @Override
        public int compareTo(Tuple o) {
            return Integer.compare(d, o.d);
        }
    }
}
