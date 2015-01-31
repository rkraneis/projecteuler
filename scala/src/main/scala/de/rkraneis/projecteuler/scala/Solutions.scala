/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.scala

object Solutions {

  /* P1: Find the sum of all the multiples of 3 or 5 below 1000. */
  def problem1_Generated =
    (0 until 1000 by 3).sum + (0 until 1000 by 5).sum -
      (0 until 1000 by 15).sum // we have counted these twice

  def problem1_Filtered =
    (0 until 1000).view.filter(n => n % 3 == 0 || n % 5 == 0).sum

  def problem1_Series =
    seriesSum(999, 3) + seriesSum(999, 5) - seriesSum(999, 15)

  def seriesSum(n: Int): Int = n * (n + 1) / 2

  def seriesSum(n:Int, s:Int):Int = seriesSum(n / s) * s

  /* P2: By considering the terms in the Fibonacci sequence whose values do not 
   * exceed four million, find the sum of the even-valued terms.
   */
  def problem2_Iterative = {
    var sum, current = 0
    var previousPrevious = 0
    var previous = 1
    do {
      if (current % 2 == 0) sum += current
      current = previous
      previous += previousPrevious
      previousPrevious = current
    } while (current <= 4000000)
    sum
  }

  def problem2_Stream1 =
    fib.view.takeWhile(_ <= 4000000).filter(_ % 2 == 0).sum

  def problem2_Stream2 =
    fib2.view.takeWhile(_ <= 4000000).filter(_ % 2 == 0).sum
  
  def problem3 = factor(600851475143L).head
}
