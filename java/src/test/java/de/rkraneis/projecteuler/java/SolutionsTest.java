/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.java;

import org.junit.Test;
import static org.junit.Assert.*;

import static de.rkraneis.projecteuler.java.Solutions.*;

public class SolutionsTest {

    @Test
    public void test_Problem1_Filtered() {
        assertEquals(233168, problem1_LoopFiltered());
    }

    @Test
    public void test_Problem1_Generated1() {
        assertEquals(233168, problem1_LoopGenerated());
    }

    @Test
    public void test_Problem1_Generated2() {
        assertEquals(233168, problem1_Series());
    }

    @Test
    public void test_Problem1_Streams_Filtered() {
        assertEquals(233168, problem1_StreamFiltered());
    }

    @Test
    public void test_Problem1_Streams_Generated() {
        assertEquals(233168, problem1_StreamGenerated1());
    }

    @Test
    public void test_Problem1_Streams_Generated2() {
        assertEquals(233168, problem1_StreamGenerated2());
    }

    @Test
    public void test_Problem2_Iterative() {
        assertEquals(4613732, problem2_Iterative());
    }

    @Test
    public void test_Problem2_Stream() {
        assertEquals(4613732, problem2_Stream());
    }

    @Test
    public void test_Problem3() {
        assertEquals(6857, problem3());
    }
}
