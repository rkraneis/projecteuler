/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.java;

import static java.util.stream.IntStream.*;
import static de.rkraneis.projecteuler.java.Util.*;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

public class Solutions {

    public static int problem1_LoopFiltered(final int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (is(i, divisibleByThree.or(divisibleByFive))) {
                sum += i;
            }
        }
        return sum;
    }

    /**
     * simple loop
     */
    public static int problem1_LoopGenerated(final int n) {
        final int m = n - 1;
        return loopSum(m, 3) + seriesSum(m, 5) - seriesSum(m, 15);
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
    public static int problem1_Series(final int n) {
        final int m = n - 1;
        return seriesSum(m, 3) + seriesSum(m, 5) - seriesSum(m, 15);
    }

    private static int seriesSum(int n) {
        return n * (n + 1) / 2;
    }

    private static int seriesSum(int n, int s) {
        return seriesSum(n / s) * s;
    }

    public static int problem1_StreamFiltered(int n) {
        return range(0, n)
                .filter(divisibleByThree.or(divisibleByFive))
                .sum();
    }

    private static final IntPredicate divisibleByThree = m -> (m % 3 == 0);
    private static final IntPredicate divisibleByFive = m -> (m % 5 == 0);

    public static int problem1_StreamGenerated1(int n) {
        n--;
        return iterate(3, m -> m + 3).limit(n / 3).sum()
                + iterate(5, m -> m + 5).limit(n / 5).sum()
                - iterate(3 * 5, m -> m + 3 * 5).limit(n / (3 * 5)).sum();
    }

    public static int problem1_StreamGenerated2(int n) {
        return incWhile(3, m -> m < n).sum()
                + incWhile(5, m -> m < n).sum()
                - incWhile(15, m -> m < n).sum();
    }

    public static int problem2_Iterative(int n) {
        int sum = 0, current = 0;
        int previousPrevious = 0, previous = 1;
        do {
            if (is(current, even)) {
                sum += current;
            }
            current = previous;
            previous += previousPrevious;
            previousPrevious = current;
        } while (current <= n);
        return sum;
    }

    public static int problem2_Stream(int n) {
        return fibWhile(m -> m <= n).filter(even).sum();
    }

    public static long problem3(long n) {
        return factor(n).getFirst();
    }

    public static BigInteger problem3_BigInteger(BigInteger n) {
        return factor(n).getFirst();
    }
}
