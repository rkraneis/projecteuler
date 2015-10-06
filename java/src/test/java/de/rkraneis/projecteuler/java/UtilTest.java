/* Copyright (C) 2015 René Kraneis */
package de.rkraneis.projecteuler.java;

import org.junit.Test;
import static org.junit.Assert.*;

import static de.rkraneis.projecteuler.java.Util.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class UtilTest {

    @Test
    public void testBaseline() {
        assertEquals(42, baseline());
    }

    @Test
    public void testFibWhile() {
        int[] expected = new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
        int[] actual = fibWhile(n -> n <= 89).toArray();
        assertArrayEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sum_NegativeOne() {
        sum(-1);
    }

    @Test
    public void sum_Zero() {
        assertEquals(0, sum(0));
    }

    @Test
    public void sum_One() {
        assertEquals(1, sum(1));
    }

    @Test
    public void sum_Two() {
        assertEquals(3, sum(2));
    }

    @Test
    public void sum_MaxMinusOne() {
        assertEquals(2305843005992468481L, sum(Integer.MAX_VALUE - 1));
    }

    @Test
    public void sum_IntMax() {
        assertEquals(2305843008139952128L, sum(Integer.MAX_VALUE));
    }

    @Test
    public void testFactor() {
        long[] expecteds = {5L, 2L, 2L};
        long[] actuals = factor(20).stream().mapToLong(n -> n).toArray();
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testFactorLargest() {
        long expected = 5L;
        long actual = largestFactor(20);
        assertEquals(expected, actual);
    }

    @Test
    public void testFactorBigInteger() {
        BigInteger[] expecteds = {BigInteger.valueOf(5), BigInteger.valueOf(2), BigInteger.valueOf(2)};
        BigInteger[] actuals
                = factor(BigInteger.valueOf(20)).toArray(new BigInteger[3]);
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testFactorLargestBigInteger() {
        BigInteger expected = BigInteger.valueOf(5);
        BigInteger actual = largestFactor(BigInteger.valueOf(20));
        assertEquals(expected, actual);
    }
}
