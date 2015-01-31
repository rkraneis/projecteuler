/* Copyright (C) 2015 René Kraneis */
package de.rkraneis.projecteuler.java;

import java.util.ArrayList;
import java.util.List;
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

        protected void advance() {
            current += step;
        }

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

        protected void advance() {
            int nextNext = current + next;
            current = next;
            next = nextNext;
        }

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
     * @return sum of all integers from 0 to n
     */
    final public static long sum(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n may not be negative");
        }
        return n * (n + 1L) / 2;
    }

    public static List<Long> factor(long n) {
        long k = 2;
        List<Long> acc = new ArrayList<>();
        while (n > 1) {
            if (divides(k, n)) {
                acc.add(k);
                n /= k;
            } else {
                k++;
            }
        }
        return acc;
    }

    private static boolean divides(long k, long n) {
        return 0 == n % k;
    }
}

abstract class AbstractGenerator extends Spliterators.AbstractIntSpliterator {

    private final IntPredicate mayAdvance;

    protected AbstractGenerator(IntPredicate p) {
        super(Long.MAX_VALUE, 0);
        this.mayAdvance = p;
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
