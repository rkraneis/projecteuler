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
object SolutionsBenchmark {

  def main(args: Array[String]): Unit = {
    val className = classOf[SolutionsBenchmark].getSimpleName
    val resultFormat = ResultFormatType.JSON
    val opts = new OptionsBuilder()
      //.include(".*" + className + ".*")
      .include(".*" + className + ".p1.*a.a_[sfg]$")
      //.include(".*" + className + ".problem1.*a.a_g.*")
      //.include(".*" + className + ".problem2.*")
      //.verbosity(VerboseMode.EXTRA)
      //.warmupIterations(20)
      //.warmupTime(TimeValue.milliseconds(500))
      //.measurementIterations(20)
      //.measurementTime(TimeValue.milliseconds(500))
      .timeUnit(TimeUnit.MILLISECONDS)
      .result(classOf[SolutionsBenchmark].getCanonicalName 
              + "/result." 
              + resultFormat.toString.toLowerCase(Locale.ROOT))
      .resultFormat(resultFormat)
      .forks(3)
      .build()
    val runResults:Collection[RunResult] = new Runner(opts).run()
    
    // println(runResults)
  }

}

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State;

import clojure.java.api.Clojure
import clojure.lang.IFn

import de.rkraneis.projecteuler._

@State(Scope.Thread)
class SolutionsBenchmark {


  var cljb, clj1f, clj1g, clj2: IFn = _;
  val NU = "de.rkraneis.projecteuler.clojure.util"
  val NS = "de.rkraneis.projecteuler.clojure.solutions"

  /** Sets up the clojure functions */
  @Setup def setup = {
    Class.forName("clojure.java.api.Clojure")
    Clojure.`var`("clojure.core", "require").invoke(Clojure.read(NS))

    cljb = Clojure.`var`(NU, "baseline")
    clj1f = Clojure.`var`(NS, "problem1-filtered")
    clj1g = Clojure.`var`(NS, "problem1-generated")
    clj2 = Clojure.`var`(NS, "problem2")
  }

  @Benchmark def baseline_clojure = cljb.invoke()
  @Benchmark def baseline_java = java.Util.baseline
  @Benchmark def baseline_scala = scala.baseline
  
  
  @Benchmark def p1_clojure_f = clj1f.invoke()
  @Benchmark def p1_clojure_g = clj1g.invoke()
  @Benchmark def p1_java_lf = java.Solutions.problem1_LoopFiltered
  @Benchmark def p1_java_lg = java.Solutions.problem1_LoopGenerated
  @Benchmark def p1_java_s = java.Solutions.problem1_Series
  @Benchmark def p1_java_sf = java.Solutions.problem1_StreamFiltered
  @Benchmark def p1_java_sg1 = java.Solutions.problem1_StreamGenerated1
  @Benchmark def p1_java_sg2 = java.Solutions.problem1_StreamGenerated2
  @Benchmark def p1_scala_f = scala.Solutions.problem1_Filtered
  @Benchmark def p1_scala_g = scala.Solutions.problem1_Generated
  @Benchmark def p1_scala_s = scala.Solutions.problem1_Series

  @Benchmark def p2_clojure_l = clj2.invoke()
  @Benchmark def p2_java_i = java.Solutions.problem2_Iterative
  @Benchmark def p2_java_s = java.Solutions.problem2_Stream
  @Benchmark def p2_scala_i = scala.Solutions.problem2_Iterative
  @Benchmark def p2_scala_s1 = scala.Solutions.problem2_Stream1
  @Benchmark def p2_scala_s2 = scala.Solutions.problem2_Stream2
}
