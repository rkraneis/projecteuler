/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.scala

/** Solutions for Project Euler Problems in Scala. */
object `package` {

  def baseline = 42

  val fib: Stream[Int] = 0 #:: fib.scanLeft(1)(_ + _)
  val fib2 = Stream.iterate( (0,1) ) { case (a,b)=>(b,a+b)  }.map(_._1)

  /** Returns the n-th fibonacci number (recursive version). */
  def fibRec(n: Int): BigInt = {
    @annotation.tailrec
    def fib0(n: Int, prev: BigInt = 0, next: BigInt = 1): BigInt = n match {
      case 0 => prev
      case 1 => next
      case _ => fib0(n - 1, next, (next + prev))
    }
    fib0(n)
  }

  /** Returns the n-th fibonacci number (iterative version). */
  def fibIt(n: Int): BigInt = n match {
    case 0 => 0
    case 1 => 1
    case _ =>
      var a, b = 1
      for (m <- 2 until n) { var c = a; a += b; b = c }; a
  }
}
