/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.scala

import org.junit._
import Assert._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner

import Solutions._

@RunWith(classOf[JUnitRunner])
class SolutionsTest extends FunSuite with Matchers {

  test("problem 1 filtered") { problem1_Filtered(1000) should be(233168) }
  test("problem 1 generated") { problem1_Generated(1000) should be(233168) }
  test("problem 1 series") { problem1_Series(1000) should be(233168) }

  test("problem 2 iterative") { problem2_Iterative(4000000) should be(4613732) }
  test("problem 2 stream1") { problem2_Stream1(4000000) should be(4613732) }
  test("problem 2 stream2") { problem2_Stream2(4000000) should be(4613732) }
  
  test("problem 3") { problem3(600851475143L) should be(6857) }
}
