/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler.benchmark

import java.util.Collection
import java.util.Locale
import java.util.TreeSet
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.results.RunResult
import org.openjdk.jmh.results.format.ResultFormatType
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.options.VerboseMode
import org.openjdk.jmh.runner.options.TimeValue

@OutputTimeUnit(TimeUnit.MILLISECONDS)
object Problem1Benchmark {

  def main(args: Array[String]): Unit = {
    val className = classOf[Problem1Benchmark].getSimpleName
    val resultPath = classOf[Problem1Benchmark].getCanonicalName
    val resultFormat = ResultFormatType.JSON
    val resultExtension = resultFormat.toString.toLowerCase(Locale.ROOT)
    val opts = new OptionsBuilder()
      .include(".*" + className + ".*")
      //.verbosity(VerboseMode.EXTRA)
      .warmupIterations(10)
      //.warmupTime(TimeValue.milliseconds(500))
      .measurementIterations(10)
      //.measurementTime(TimeValue.milliseconds(500))
      .timeUnit(TimeUnit.MICROSECONDS)
      .result(resultPath + "/result." + resultExtension)
      .resultFormat(resultFormat)
      .forks(3)
      .build()
    val runResults: Collection[RunResult] = new Runner(opts).run()

    // println(runResults)
  }

}

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

import java.math.BigInteger

import clojure.java.api.Clojure
import clojure.lang.IFn

import de.rkraneis.projecteuler._

@State(Scope.Thread)
class Problem1Benchmark {

  var clj1f, clj1g, clj1s: IFn = _
  val NU = "de.rkraneis.projecteuler.clojure.util"
  val NS = "de.rkraneis.projecteuler.clojure.solutions"

  /** Sets up the clojure functions */
  @Setup def setup = {
    Class.forName("clojure.java.api.Clojure")
    Clojure.`var`("clojure.core", "require").invoke(Clojure.read(NS))

    clj1f = Clojure.`var`(NS, "problem1-filtered")
    clj1g = Clojure.`var`(NS, "problem1-generated")
    clj1s = Clojure.`var`(NS, "problem1-series")
  }

  @Param(Array("1000")) var p1: Int = _
//  @Benchmark def p1_clojure_f = clj1f.invoke(p1)
  @Benchmark def p1_clojure_g = clj1g.invoke(p1)
  @Benchmark def p1_clojure_s = clj1s.invoke(p1)
//  @Benchmark def p1_java_lf = java.Solutions.problem1_LoopFiltered(p1)
//  @Benchmark def p1_java_lg = java.Solutions.problem1_LoopGenerated(p1)
//  @Benchmark def p1_java_s = java.Solutions.problem1_Series(p1)
//  @Benchmark def p1_java_sf = java.Solutions.problem1_StreamFiltered(p1)
//  @Benchmark def p1_java_sg1 = java.Solutions.problem1_StreamGenerated1(p1)
//  @Benchmark def p1_java_sg2 = java.Solutions.problem1_StreamGenerated2(p1)
//  @Benchmark def p1_scala_f = scala.Solutions.problem1_Filtered(p1)
//  @Benchmark def p1_scala_g = scala.Solutions.problem1_Generated(p1)
//  @Benchmark def p1_scala_s = scala.Solutions.problem1_Series(p1)
}
