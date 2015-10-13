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

@OutputTimeUnit(TimeUnit.SECONDS)
object Problem4Benchmark {

  def main(args: Array[String]): Unit = {
    val className = classOf[Problem4Benchmark].getSimpleName
    val resultPath = classOf[Problem4Benchmark].getCanonicalName
    val resultFormat = ResultFormatType.JSON
    val resultExtension = resultFormat.toString.toLowerCase(Locale.ROOT)
    val opts = new OptionsBuilder()
      .include(".*" + className + ".*")
      //.verbosity(VerboseMode.EXTRA)
      .warmupIterations(5)
      //.warmupTime(TimeValue.milliseconds(500))
      .measurementIterations(5)
      //.measurementTime(TimeValue.milliseconds(500))
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
class Problem4Benchmark {

  var clj4: IFn = _
  val NU = "de.rkraneis.projecteuler.clojure.util"
  val NS = "de.rkraneis.projecteuler.clojure.solutions"

  /** Sets up the clojure functions */
  @Setup def setup = {
    Class.forName("clojure.java.api.Clojure")
    Clojure.`var`("clojure.core", "require").invoke(Clojure.read(NS))

    clj4 = Clojure.`var`(NS, "problem4")

  }

  @Param(Array("100")) var p41: Int = _
  @Param(Array("1000")) var p42: Int = _
  @Benchmark def p4_clojure = clj4.invoke(p41, p42)
  @Benchmark def p4_frege = frege.Solutions.problem4(p41, p42)
}
