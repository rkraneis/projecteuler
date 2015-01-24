/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.scala

object Solutions {

  /* P1: Find the sum of all the multiples of 3 or 5 below 1000. */
  def problem1_Generated =
    (0 until 1000 by 3).sum + (0 until 1000 by 5).sum -
      (0 until 1000 by 15).sum // we have counted these twice

  def problem1_Filtered =
    (0 until 1000).view.filter(n => n % 3 == 0 || n % 5 == 0).sum

  /* P2: By considering the terms in the Fibonacci sequence whose values do not 
   * exceed four million, find the sum of the even-valued terms.
   */
  def problem2_Iterative = {
    var sum, fib = 0
    var a = 0
    var b = 1
    do {
      if (fib % 2 == 0) sum += fib
      fib = b
      b += a
      a = fib
    } while (fib <= 4000000)
    sum
  }

  def problem2_Recursive = {
    problem2(fibRec)
  }

  def problem2_Stream1 =
    fib.view.takeWhile(_ <= 4000000).filter(_ % 2 == 0).sum

  def problem2_Stream2 =
    fib2.view.takeWhile(_ <= 4000000).filter(_ % 2 == 0).sum

  val FourMillion = 4 * 1000 * 1000;
  def problem2(fib: Int => BigInt) = {
    var f, s: BigInt = 0;
    var n = 0;
    while (f < FourMillion) {
      n += 3
      f = fib(n)
      if (f < FourMillion) {
        if (f % 2 == 0) {
          s += f
        }
      }
    }
    s
  }
}
