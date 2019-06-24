
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int l = stdin.nextInt();

        int[] tastes = IntStream.rangeClosed(1, n).map(i -> l + i - 1).toArray();
        int sum = Arrays.stream(tastes).sum();
        int ans = Arrays.stream(tastes)
            .map(taste -> sum - taste)
            .boxed()
            .min(Comparator.comparing(taste -> Math.abs(sum - taste)))
            .get()
            .intValue();
        System.out.println(ans);
    }
}