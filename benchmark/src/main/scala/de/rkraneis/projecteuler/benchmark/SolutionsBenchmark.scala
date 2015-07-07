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
      //.include(".*" + className + ".p1.*a.a_[sfg]$")
      //.include(".*" + className + ".problem1.*a.a_g.*")
      //.include(".*" + className + ".problem2.*")
      .include(".*" + className + ".p3.*")
      //.verbosity(VerboseMode.EXTRA)
      //.warmupIterations(20)
      //.warmupTime(TimeValue.milliseconds(500))
      //.measurementIterations(20)
      //.measurementTime(TimeValue.milliseconds(500))
      .timeUnit(TimeUnit.MILLISECONDS)
      .result(classOf[SolutionsBenchmark].getCanonicalName()
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
class SolutionsBenchmark {

  var cljb, clj1f, clj1g, clj2, clj3: IFn = _
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
    clj3 = Clojure.`var`(NS, "problem3")
    
    p3b = BigInteger.valueOf(p3)
  }

  @Benchmark def baseline1_clojure = cljb.invoke
  @Benchmark def baseline1_java = java.Util.baseline
  @Benchmark def baseline1_scala = scala.baseline
  
  @Param(Array("23")) var pb: Int = _
  @Benchmark def baseline2_clojure = cljb.invoke(pb)
  @Benchmark def baseline2_java = java.Util.baseline(pb)
  @Benchmark def baseline2_scala = scala.baseline(pb)
  
  @Param(Array("1000")) var p1: Int = _
  @Benchmark def p1_clojure_f = clj1f.invoke(p1)
  @Benchmark def p1_clojure_g = clj1g.invoke(p1)
  @Benchmark def p1_java_lf = java.Solutions.problem1_LoopFiltered(p1)
  @Benchmark def p1_java_lg = java.Solutions.problem1_LoopGenerated(p1)
  @Benchmark def p1_java_s = java.Solutions.problem1_Series(p1)
  @Benchmark def p1_java_sf = java.Solutions.problem1_StreamFiltered(p1)
  @Benchmark def p1_java_sg1 = java.Solutions.problem1_StreamGenerated1(p1)
  @Benchmark def p1_java_sg2 = java.Solutions.problem1_StreamGenerated2(p1)
  @Benchmark def p1_scala_f = scala.Solutions.problem1_Filtered(p1)
  @Benchmark def p1_scala_g = scala.Solutions.problem1_Generated(p1)
  @Benchmark def p1_scala_s = scala.Solutions.problem1_Series(p1)

  @Param(Array("4000000")) var p2: Int = _
  @Benchmark def p2_clojure_l = clj2.invoke(p2)
  @Benchmark def p2_java_i = java.Solutions.problem2_Iterative(p2)
  @Benchmark def p2_java_s = java.Solutions.problem2_Stream(p2)
  @Benchmark def p2_scala_i = scala.Solutions.problem2_Iterative(p2)
  @Benchmark def p2_scala_s1 = scala.Solutions.problem2_Stream1(p2)
  @Benchmark def p2_scala_s2 = scala.Solutions.problem2_Stream2(p2)

  @Param(Array("600851475143")) var p3: Long = _
  var p3b: BigInteger = _
  @Benchmark def p3_clojure = clj2.invoke(p3)
  @Benchmark def p3_java_l = java.Solutions.problem3(p3)
  @Benchmark def p3_java_ln = java.Solutions.problem3_noList(p3)
  @Benchmark def p3_java_b = java.Solutions.problem3_BigInteger(p3b)
  @Benchmark def p3_scala = scala.Solutions.problem3(p3)
}
