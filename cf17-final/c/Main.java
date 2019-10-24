package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public void solve(final BufferedReader stdin, final PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        Map<Integer, Long> d = Pattern.compile(" ")
                                      .splitAsStream(stdin.readLine())
                                      .map(Integer::valueOf)
                                      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        d.put(0, d.getOrDefault(0, 0L) + 1);

        List<List<Integer>> times = new ArrayList<>();
        times.add(new ArrayList<>());
        for (Entry<Integer, Long> e : d.entrySet()) {
            if (e.getValue() > 2) {
                stdout.println(0);
                return ;
            }

            if (e.getValue() == 1) {
                List<List<Integer>> temp = new ArrayList<>();
                for (List<Integer> time : times) {
                    List<Integer> time1 = new ArrayList<>(time);
                    List<Integer> time2 = new ArrayList<>(time);
                    time1.add(e.getKey());
                    time2.add(e.getKey() * -1);
                    temp.add(time1);
                    temp.add(time2);
                }
                times = temp;
            } else {
                // e.getValue() == 2
                for (List<Integer> time : times) {
                    time.add(e.getKey());
                    time.add(e.getKey() * -1);
                }
            }
        }

        int max = -1;
        for (List<Integer> time : times) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < time.size(); i++) {
                for (int j = i + 1; j < time.size(); j++) {
                    int x = Math.abs(time.get(i) - time.get(j));
                    int y = Math.min(x, 24 - x);
                    min = Math.min(min, y);
                }
            }
            max = Math.max(max, min);
        }

        stdout.println(max);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
