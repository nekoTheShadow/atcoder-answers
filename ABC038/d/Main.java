package d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        List<Tuple> tuples = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int w = stdin.nextInt();
            int h = stdin.nextInt();
            tuples.add(new Tuple(w, h));
        }
        
        Collections.sort(tuples, Comparator.comparingInt(Tuple::getW).thenComparing(Comparator.comparingInt(Tuple::getH).reversed()));

        List<Integer> lis = new ArrayList<>();
        lis.add(tuples.get(0).getH());
        for (Tuple tuple : tuples) {
            int h = tuple.getH();
            if (lis.get(lis.size() - 1) < h) {
                lis.add(h);
            } else {
                int i = Collections.binarySearch(lis, h);
                if (i < 0) {
                    i = ~i;
                }
                lis.set(i, h);
            }
        }
        System.out.println(lis.size());
    }
    private static final class Tuple {
        private int w;
        private int h;

        public Tuple(int w, int h) {
            this.w = w;
            this.h = h;
        }
        public int getW() {
            return w;
        }
        public int getH() {
            return h;
        }
    }
}
