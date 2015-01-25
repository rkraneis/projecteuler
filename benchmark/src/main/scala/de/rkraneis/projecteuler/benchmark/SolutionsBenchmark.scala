/* Copyright 2015 René Kraneis */
package de.rkraneis.projecteuler

import _root_.java.util.Collection
import _root_.java.util.TreeSet
import _root_.java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.results.RunResult
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.options.VerboseMode
import org.openjdk.jmh.runner.options.TimeValue

@OutputTimeUnit(TimeUnit.MILLISECONDS)
object SolutionsBenchmark {

  def main(args: Array[String]): Unit = {
    val className = classOf[SolutionsBenchmark].getSimpleName
    val opts = new OptionsBuilder()
      //.include(".*" + className + ".*")
      .include(".*" + className + ".problem1.*")
      //.include(".*" + className + ".problem2.*")
      //.verbosity(VerboseMode.EXTRA)
      .warmupIterations(5)
      //.warmupTime(TimeValue.microseconds(500))
      .measurementIterations(5)
      //.measurementTime(TimeValue.milliseconds(500))
      .timeUnit(TimeUnit.MILLISECONDS)
      .forks(1)
      .build()
    val runResults:Collection[RunResult] = new Runner(opts).run()
    
    // println(runResults)
  }

}

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State;

import _root_.clojure.java.api.Clojure
import _root_.clojure.lang.IFn

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
  
  
  @Benchmark def problem1_clojure_f = clj1f.invoke()
  @Benchmark def problem1_clojure_g = clj1g.invoke()
  @Benchmark def problem1_java_f = java.Solutions.problem1_Filtered
  @Benchmark def problem1_java_g1 = java.Solutions.problem1_Generated1
  @Benchmark def problem1_java_g2 = java.Solutions.problem1_Generated2
  @Benchmark def problem1_java_sf = java.Solutions.problem1_Stream_Filtered
  @Benchmark def problem1_java_sg = java.Solutions.problem1_Stream_Generated
  @Benchmark def problem1_java_sg2 = java.Solutions.problem1_Stream_Generated2
  @Benchmark def problem1_scala_f = scala.Solutions.problem1_Filtered
  @Benchmark def problem1_scala_g = scala.Solutions.problem1_Generated

  @Benchmark def problem2_clojure = clj2.invoke()
  @Benchmark def problem2_scala_i = scala.Solutions.problem2_Iterative
  @Benchmark def problem2_scala_r = scala.Solutions.problem2_Recursive
  @Benchmark def problem2_scala_s1 = scala.Solutions.problem2_Stream1
  @Benchmark def problem2_scala_s2 = scala.Solutions.problem2_Stream2
  @Benchmark def problem2_java_i = java.Solutions.problem2_Iterative
  @Benchmark def problem2_java_s = java.Solutions.problem2_Stream
}
