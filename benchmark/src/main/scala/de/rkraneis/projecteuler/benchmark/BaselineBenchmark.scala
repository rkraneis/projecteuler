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
object BaselineBenchmark {

  def main(args: Array[String]): Unit = {
    val className = classOf[BaselineBenchmark].getSimpleName
    val resultFormat = ResultFormatType.JSON
    val opts = new OptionsBuilder()
      .include(".*" + className + ".*")
      //.verbosity(VerboseMode.EXTRA)
      //.warmupIterations(20)
      //.warmupTime(TimeValue.milliseconds(500))
      //.measurementIterations(20)
      //.measurementTime(TimeValue.milliseconds(500))
      .timeUnit(TimeUnit.MICROSECONDS)
      .result(className
        + "/result."
        + resultFormat.toString.toLowerCase(Locale.ROOT))
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
class BaselineBenchmark {

  var cljb, cljbh: IFn = _
  val NU = "de.rkraneis.projecteuler.clojure.util"
  val NS = "de.rkraneis.projecteuler.clojure.solutions"

  /** Sets up the clojure functions */
  @Setup def setup = {
    Class.forName("clojure.java.api.Clojure")
    Clojure.`var`("clojure.core", "require").invoke(Clojure.read(NS))

    cljb = Clojure.`var`(NU, "baseline")
  }

  @Benchmark def baseline1_clojure = cljb.invoke
  @Benchmark def baseline1_java = java.Util.baseline
  @Benchmark def baseline1_scala = scala.baseline
  @Benchmark def baseline1_groovy = groovy.Util.baseline

  @Param(Array("23")) var pb: Int = _
  @Benchmark def baseline2_clojure = cljb.invoke(pb)
  @Benchmark def baselineh2_clojure = cljbh.invoke(pb)
  @Benchmark def baseline2_java = java.Util.baseline(pb)
  @Benchmark def baseline2_scala = scala.baseline(pb)
  @Benchmark def baseline2_groovy = groovy.Util.baseline(pb)
}
