/* Copyright (C) 2015 René Kraneis */
package de.rkraneis.projecteuler.java;

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

    private static class FibonacciGenerator extends Spliterators.AbstractIntSpliterator {

        private final IntPredicate mayAdvance;
        private int current = 0;
        private int next = 1;

        protected FibonacciGenerator(IntPredicate p) {
            super(Long.MAX_VALUE, 0);
            this.mayAdvance = p;
        }

        private boolean mayAdvance() {
            return mayAdvance.test(current);
        }

        private void advance() {
            int nextNext = current + next;
            current = next;
            next = nextNext;
        }

        @Override
        public boolean tryAdvance(IntConsumer action) {
            if (!mayAdvance()) {
                return false;
            }
            action.accept(current);
            advance();
            return true;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Integer> action) {
            if (!mayAdvance()) {
                return false;
            }
            action.accept(next);
            advance();
            return true;
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
}
