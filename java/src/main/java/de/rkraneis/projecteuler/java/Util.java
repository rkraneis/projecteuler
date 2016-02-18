/* Copyright (C) 2015 René Kraneis */
package de.rkraneis.projecteuler.java;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class Util {

    public static int baseline() {
        return 42;
    }

    public static int baseline(int n) {
        return n;
    }

    public static IntPredicate even = n -> (n & 1) == 0;

    public static boolean isEven(int i) {
        return even.test(i);
    }

    private static class IncrementGenerator extends AbstractGenerator {

        private int current = 0;
        private final int step;

        public IncrementGenerator(int start, int step, IntPredicate p) {
            super(p);
            this.current = start;
            this.step = step;
        }

        public IncrementGenerator(int step, IntPredicate p) {
            this(0, step, p);
        }

        public IncrementGenerator(IntPredicate p) {
            this(0, 1, p);
        }

        @Override
        protected void advance() {
            current += step;
        }

        @Override
        protected int current() {
            return current;
        }
    }

    public static IntStream incWhile(int step, IntPredicate p) {
        return StreamSupport.intStream(new IncrementGenerator(step, p), false);
    }

    public static IntStream incWhile(int start, int step, IntPredicate p) {
        return StreamSupport.intStream(new IncrementGenerator(start, step, p), false);
    }

    private static class FibonacciGenerator extends AbstractGenerator {

        private int current = 0;
        private int next = 1;

        public FibonacciGenerator(IntPredicate p) {
            super(p);
        }

        @Override
        protected void advance() {
            int nextNext = current + next;
            current = next;
            next = nextNext;
        }

        @Override
        protected int current() {
            return current;
        }
    }

    public static IntStream fibWhile(IntPredicate p) {
        return StreamSupport.intStream(new FibonacciGenerator(p), false);
    }

    /**
     * Calculates the sum of all integers from 0 to n. It uses the fact that
     * <pre>
     *   n
     *  ====
     *  \          n(n+1)
     *   >   i  =  ------    .
     *  /            2
     *  ====
     *  i=0
     * </pre>
     *
     * @param n the integer to sum up to
     *
     * @return sum of all integers from 0 to n
     */
    final public static long sum(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n may not be negative");
        }
        return n * (n + 1L) / 2;
    }

    public static LinkedList<Long> factor(long n) {
        long k = 2;
        LinkedList<Long> acc = new LinkedList<>();
        while (n > 1) {
            if (divides(k, n)) {
                acc.addFirst(k);
                n /= k;
            } else {
                k++;
            }
        }
        return acc;
    }

    public static LinkedList<Long> factorLast(long n) {
        long k = 2;
        LinkedList<Long> acc = new LinkedList<>();
        while (n > 1) {
            if (divides(k, n)) {
                acc.addLast(k);
                n /= k;
            } else {
                k++;
            }
        }
        return acc;
    }

    public static long largestFactor(long n) {
        long k = 2;
        long largestFactor = 1;
        while (n > 1) {
            if (divides(k, n)) {
                largestFactor = k;
                n /= k;
            } else {
                k++;
            }
        }
        return largestFactor;
    }

    private static boolean divides(long k, long n) {
        return 0 == n % k;
    }

    public static LinkedList<BigInteger> factor(BigInteger n) {
        BigInteger k = BigInteger.valueOf(2);
        LinkedList<BigInteger> acc = new LinkedList<>();
        while (n.compareTo(BigInteger.ONE) > 0) {
            if (divides(k, n)) {
                acc.addFirst(k);
                n = n.divide(k);
            } else {
                k = k.add(BigInteger.ONE);
            }
        }
        return acc;
    }

    public static BigInteger largestFactor(BigInteger n) {
        BigInteger k = BigInteger.valueOf(2);
        BigInteger largestFactor = BigInteger.valueOf(1);
        while (n.compareTo(BigInteger.ONE) > 0) {
            if (divides(k, n)) {
                largestFactor = k;
                n = n.divide(k);
            } else {
                k = k.add(BigInteger.ONE);
            }
        }
        return largestFactor;
    }

    private static boolean divides(BigInteger k, BigInteger n) {
        return n.remainder(k).compareTo(BigInteger.ZERO) == 0;
    }
}

abstract class AbstractGenerator extends Spliterators.AbstractIntSpliterator {

    private final IntPredicate mayAdvance;

    protected AbstractGenerator(IntPredicate p) {
        super(Long.MAX_VALUE, 0);
        this.mayAdvance = p;
    }

    protected AbstractGenerator() {
        super(Long.MAX_VALUE, 0);
        this.mayAdvance = i -> true;
    }

    private boolean mayAdvance() {
        return mayAdvance.test(current());
    }

    abstract void advance();

    abstract int current();

    @Override
    public boolean tryAdvance(IntConsumer action) {
        if (!mayAdvance()) {
            return false;
        }
        action.accept(current());
        advance();
        return true;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Integer> action) {
        if (!mayAdvance()) {
            return false;
        }
        action.accept(current());
        advance();
        return true;
    }

}
