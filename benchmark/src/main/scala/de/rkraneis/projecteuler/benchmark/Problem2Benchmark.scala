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
object Problem2Benchmark {

  def main(args: Array[String]): Unit = {
    val className = classOf[Problem2Benchmark].getSimpleName
    val resultPath = classOf[Problem2Benchmark].getCanonicalName
    val resultFormat = ResultFormatType.JSON
    val resultExtension = resultFormat.toString.toLowerCase(Locale.ROOT)
    val opts = new OptionsBuilder()
      .include(".*" + className + ".*")
      //.verbosity(VerboseMode.EXTRA)
      //.warmupIterations(20)
      //.warmupTime(TimeValue.milliseconds(500))
      //.measurementIterations(20)
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
class Problem2Benchmark {

  var clj2: IFn = _
  val NU = "de.rkraneis.projecteuler.clojure.util"
  val NS = "de.rkraneis.projecteuler.clojure.solutions"

  /** Sets up the clojure functions */
  @Setup def setup = {
    Class.forName("clojure.java.api.Clojure")
    Clojure.`var`("clojure.core", "require").invoke(Clojure.read(NS))

    clj2 = Clojure.`var`(NS, "problem2")
  }

  @Param(Array("4000000")) var p2: Int = _
  @Benchmark def p2_clojure_l = clj2.invoke(p2)
  @Benchmark def p2_java_i = java.Solutions.problem2_Iterative(p2)
  @Benchmark def p2_java_s = java.Solutions.problem2_Stream(p2)
  @Benchmark def p2_scala_i = scala.Solutions.problem2_Iterative(p2)
  @Benchmark def p2_scala_s1 = scala.Solutions.problem2_Stream1(p2)
  @Benchmark def p2_scala_s2 = scala.Solutions.problem2_Stream2(p2)

}
