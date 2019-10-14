package a;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        int c = stdin.nextInt();

        Tuple tuple = new Tuple(a, b, c);
        Set<Tuple> tuples = new HashSet<>();
        while (tuple.x % 2 == 0 && tuple.y % 2 == 0 && tuple.z % 2 == 0) {
            if (tuples.contains(tuple)) {
                System.out.println(-1);
                System.exit(0);
            }

            tuples.add(tuple);
            tuple = new Tuple(tuple.y / 2 + tuple.z / 2, tuple.z / 2 + tuple.x / 2, tuple.x / 2 + tuple.y / 2);
        }
        System.out.println(tuples.size());
    }

    private static final class Tuple {
        private int x;
        private int y;
        private int z;

        public Tuple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Tuple)) {
                return false;
            }

            Tuple that = (Tuple) obj;
            return this.x == that.x && this.y == that.y && this.z == that.z;
        }
    }
}
