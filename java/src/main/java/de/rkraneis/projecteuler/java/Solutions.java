/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.java;

import static java.util.stream.IntStream.*;
import static de.rkraneis.projecteuler.java.Util.*;
import java.util.stream.IntStream;

public class Solutions {

    public static int problem1_LoopFiltered() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    /**
     * simple loop
     */
    public static int problem1_LoopGenerated() {
        return loopSum(999, 3) + seriesSum(999, 5) - seriesSum(999, 15);
    }

    private static int loopSum(final int n, final int s) {
        int sum = 0;
        for (int i = 0; i <= n; i += s) {
            sum += i;
        }
        return sum;
    }

    /**
     * arithmetic sum
     */
    public static int problem1_Series() {
        return seriesSum(999, 3) + seriesSum(999, 5) - seriesSum(999, 15);
    }

    private static int seriesSum(int n) {
        return n * (n + 1) / 2;
    }

    private static int seriesSum(int n, int s) {
        return seriesSum(n / s) * s;
    }

    public static int problem1_StreamFiltered() {
        return range(0, 1000)
                .filter(n -> (n % 3 == 0 || n % 5 == 0))
                .sum();
    }

    public static int problem1_StreamGenerated1() {
        return iterate(3, n -> n + 3).limit(999 / 3).sum()
                + iterate(5, n -> n + 5).limit(999 / 5).sum()
                - iterate(3 * 5, n -> n + 3 * 5).limit(999 / (3 * 5)).sum();
    }

    public static int problem1_StreamGenerated2() {
        return incWhile(3, n -> n < 1000).sum()
                + incWhile(5, n -> n < 1000).sum()
                - incWhile(15, n -> n < 1000).sum();
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
