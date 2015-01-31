/* Copyright (C) 2015 René Kraneis */

package de.rkraneis.projecteuler.scala

import org.junit._
import Assert._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PackageTest extends FunSuite with Matchers {
  test("baseline") { baseline should be(42) }
  test("fib") { fib.take(12) should be(Stream[Int](0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89)) }
  test("fib2") { fib2.take(12) should be(Stream[Int](0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89)) }
  test("factor") { factor(20) should be(List[Int](5, 2, 2)) }
}
