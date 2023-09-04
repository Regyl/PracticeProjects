package yandex;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PoisonBuckets {

    private static final int NO_ANSWER = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        String elements = sc.nextLine();
        List<Integer> integers = Stream.of(elements.split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        if (n != integers.size()) {
            throw new RuntimeException();
        }

        int min = integers.get(0);
        int max = integers.get(n - 1);
        if (isSorted(integers)) {
            System.out.print(max - min);
        } else {
            System.out.print(NO_ANSWER);
        }
    }

    private static boolean isSorted(List<Integer> array) {
        int previous = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            int current = array.get(i);
            if (current < previous) {
                return Boolean.FALSE;
            }

            previous = current;
        }

        return Boolean.TRUE;
    }
}
