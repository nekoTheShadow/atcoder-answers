package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String[] line = stdin.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        long[] a = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToLong(Long::parseLong).toArray();
        
        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Arrays.stream(a).forEach(pq::add);
        for (int i = 0; i < m; i++) {
            pq.add(pq.poll() / 2L);
        }
        
        long ans = pq.stream().mapToLong(Long::longValue).sum();
        System.out.println(ans);
    }
}
