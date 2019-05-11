package e;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int q = stdin.nextInt();

        TreeSet<Long> a = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            long x = stdin.nextLong();
            if (a.contains(x)) {
                a.remove(x);
            } else {
                a.add(x);
            }
        }
        
        long[] l = new long[q];
        long[] r = new long[q];
        long[] x = new long[q];
        for (int i = 0; i < q; i++) {
            l[i] = stdin.nextLong();
            r[i] = stdin.nextLong();
            x[i] = stdin.nextLong();
        }
        
        long[] answers = new long[q];
        for (int i = 0; i < q; i++) {
            Set<Long> sub = a.subSet(l[i], r[i] + 1);
            long answer = 0;
            long counter = 0;
            for (Iterator<Long> it = sub.iterator(); it.hasNext(); ) {
                answer ^= it.next();
                counter++;
                it.remove();
            }
            
            answers[i] = answer;
            if (counter % 2 != 0) {
                if (a.contains(x[i])) {
                    a.remove(x[i]);
                } else {
                    a.add(x[i]);
                }
            }
        }
        
        Arrays.stream(answers).forEach(System.out::println);
    }
}
