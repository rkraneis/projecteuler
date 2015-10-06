/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.java;

import org.junit.Test;
import static org.junit.Assert.*;

import static de.rkraneis.projecteuler.java.Solutions.*;
import java.math.BigInteger;
import static org.hamcrest.CoreMatchers.is;

public class SolutionsTest {

    @Test
    public void test_Problem1_Filtered() {
        assertThat(problem1_LoopFiltered(1000), is(233168));
    }

    @Test
    public void test_Problem1_Generated1() {
        assertEquals(233168, problem1_LoopGenerated(1000));
    }

    @Test
    public void test_Problem1_Generated2() {
        assertEquals(233168, problem1_Series(1000));
    }

    @Test
    public void test_Problem1_Streams_Filtered() {
        assertEquals(233168, problem1_StreamFiltered(1000));
    }

    @Test
    public void test_Problem1_Streams_Generated() {
        assertEquals(233168, problem1_StreamGenerated1(1000));
    }

    @Test
    public void test_Problem1_Streams_Generated2() {
        assertEquals(233168, problem1_StreamGenerated2(1000));
    }

    @Test
    public void test_Problem2_Iterative() {
        assertEquals(4613732, problem2_Iterative(4000000));
    }

    @Test
    public void test_Problem2_Stream() {
        assertEquals(4613732, problem2_Stream(4000000));
    }

    @Test
    public void test_Problem3() {
        assertEquals(6857, problem3(600851475143L));
        assertEquals(6857, problem3_last(600851475143L));
        assertEquals(6857, problem3_noList(600851475143L));
    }

    @Test
    public void test_Problem3_BigInteger() {
        assertEquals(BigInteger.valueOf(6857),
                problem3_BigInteger(BigInteger.valueOf(600851475143L)));
        assertEquals(BigInteger.valueOf(6857),
                problem3_BigInteger_noList(BigInteger.valueOf(600851475143L)));
    }
}
