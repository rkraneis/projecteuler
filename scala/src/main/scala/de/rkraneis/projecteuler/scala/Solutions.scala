/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.scala

object Solutions {

  /* P1: Find the sum of all the multiples of 3 or 5 below 1000. */
  def problem1_Generated(n: Int) =
    (0 until n by 3).sum + (0 until n by 5).sum -
      (0 until n by 15).sum // we have counted these twice

  def problem1_Filtered(n: Int) =
    (0 until n).view.filter(m => m % 3 == 0 || m % 5 == 0).sum

  def problem1_Series(n: Int) = {
    val m = n-1
    seriesSum(m, 3) + seriesSum(m, 5) - seriesSum(m, 15)
  }

  def seriesSum(n: Int): Int = n * (n + 1) / 2

  def seriesSum(n:Int, s:Int):Int = seriesSum(n / s) * s

  /* P2: By considering the terms in the Fibonacci sequence whose values do not 
   * exceed four million, find the sum of the even-valued terms.
   */
  def problem2_Iterative(n: Int) = {
    var sum, current = 0
    var previousPrevious = 0
    var previous = 1
    do {
      if (current % 2 == 0) sum += current
      current = previous
      previous += previousPrevious
      previousPrevious = current
    } while (current <= n)
    sum
  }

  def problem2_Stream1(n: Int) =
    fib.view.takeWhile(_ <= n).filter(_ % 2 == 0).sum

  def problem2_Stream2 (n: Int) =
    fib2.view.takeWhile(_ <= n).filter(_ % 2 == 0).sum
  
  def problem3(n: Long) = factor(n).head
}
