package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Pattern space = Pattern.compile(" ");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(stdin.readLine());
        int[] a = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();

        int sum = Arrays.stream(a).sum();
        List<Integer> distances = Arrays.stream(a).map(v -> Math.abs(v * n - sum)).boxed().collect(Collectors.toList());
        Integer min = Collections.min(distances);
        System.out.println(distances.indexOf(min));

    }
}
