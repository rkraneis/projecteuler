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
object Problem3Benchmark {

  def main(args: Array[String]): Unit = {
    val className = classOf[Problem3Benchmark].getSimpleName
    val resultPath = classOf[Problem3Benchmark].getCanonicalName
    val resultFormat = ResultFormatType.JSON
    val resultExtension = resultFormat.toString.toLowerCase(Locale.ROOT)
    val opts = new OptionsBuilder()
      .include(".*" + className + ".*")
      //.verbosity(VerboseMode.EXTRA)
      //.warmupIterations(20)
      //.warmupTime(TimeValue.milliseconds(500))
      //.measurementIterations(20)
      //.measurementTime(TimeValue.milliseconds(500))
      .timeUnit(TimeUnit.MILLISECONDS)
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
class Problem3Benchmark {

  var clj3: IFn = _
  val NU = "de.rkraneis.projecteuler.clojure.util"
  val NS = "de.rkraneis.projecteuler.clojure.solutions"

  /** Sets up the clojure functions */
  @Setup def setup = {
    Class.forName("clojure.java.api.Clojure")
    Clojure.`var`("clojure.core", "require").invoke(Clojure.read(NS))

    clj3 = Clojure.`var`(NS, "problem3")

    p3b = BigInteger.valueOf(p3)
  }

  @Param(Array("600851475143")) var p3: Long = _
  var p3b: BigInteger = _
  @Benchmark def p3_clojure = clj3.invoke(p3)
  @Benchmark def p3_java_l = java.Solutions.problem3(p3)
  @Benchmark def p3_java_l2 = java.Solutions.problem3_last(p3)
  @Benchmark def p3_java_ln = java.Solutions.problem3_noList(p3)
  @Benchmark def p3_java_b = java.Solutions.problem3_BigInteger(p3b)
  @Benchmark def p3_java_bnl = java.Solutions.problem3_BigInteger_noList(p3b)
  @Benchmark def p3_scala = scala.Solutions.problem3(p3)
}
