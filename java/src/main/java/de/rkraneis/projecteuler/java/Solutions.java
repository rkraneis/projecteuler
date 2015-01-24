/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.java;

import static java.util.stream.IntStream.*;
import static de.rkraneis.projecteuler.java.Util.*;

public class Solutions {

    public static int problem1_Filtered() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    public static int problem1_Generated1() {
        int sum = 0;
        for (int i = 0; i < 1000; i += 3) {
            sum += i;
        }
        for (int i = 0; i < 1000; i += 5) {
            sum += i;
        }
        for (int i = 0; i < 1000; i += 15) {
            sum -= i;
        }
        return sum;
    }

    public static int problem1_Generated2() {
        return sum(3, 1000) + sum(5, 1000) - sum(15, 1000);
    }

    private static int sum(final int step, final int bound) {
        int sum = 0;
        for (int i = 0; i < bound; i += step) {
            sum += i;
        }
        return sum;
    }

    public static int problem1_Stream_Filtered() {
        return range(0, 1000)
                .filter(n -> (n % 3 == 0 || n % 5 == 0))
                .sum();
    }

    public static int problem1_Stream_Generated() {
        return iterate(3, n -> n + 3).limit(999 / 3).sum()
                + iterate(5, n -> n + 5).limit(999 / 5).sum()
                - iterate(3 * 5, n -> n + 3 * 5).limit(999 / (3 * 5)).sum();
    }

    public static int problem2_Iterative() {
        int sum = 0, current = 0;
        int previousPrevious = 0, previous = 1;
        do {
            if (current % 2 == 0) {
                sum += current;
            }
            current = previous;
            previous += previousPrevious;
            previousPrevious = current;
        } while (current <= 4000000);
        return sum;
    }

    public static int problem2_Stream() {
        return fibWhile(n -> n <= 4000000).filter(n -> n % 2 == 0).sum();
    }
}
