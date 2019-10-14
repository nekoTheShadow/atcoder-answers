package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.OptionalInt;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        Pattern space = Pattern.compile(" ");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int[] first = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
        int n = first[0];
        int l = first[1];
        int[] a = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();

        OptionalInt knot = IntStream.range(0, n - 1).filter(i -> a[i] + a[i + 1] >= l).findFirst();
        if (!knot.isPresent()) {
            System.out.println("Impossible");
            System.exit(0);
        }

        System.out.println("Possible");
        for (int i = 0; i < knot.getAsInt(); i++) System.out.println(i + 1);
        for (int i = n - 2; i > knot.getAsInt(); i--) System.out.println(i + 1);
        System.out.println(knot.getAsInt() + 1);
    }

}
