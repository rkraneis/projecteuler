    # Run complete. Total time: 00:22:46

    Benchmark                       (p1)   Mode  Cnt    Score    Error   Units
    Problem1Benchmark.p1_clojure_f  1000  thrpt   60    0,018 ±  0,001  ops/us
    Problem1Benchmark.p1_scala_f    1000  thrpt   60    0,079 ±  0,001  ops/us
    Problem1Benchmark.p1_java_sf    1000  thrpt   60    0,118 ±  0,001  ops/us
    Problem1Benchmark.p1_clojure_g  1000  thrpt   60    0,147 ±  0,006  ops/us
    Problem1Benchmark.p1_java_sg1   1000  thrpt   60    0,161 ±  0,006  ops/us
    Problem1Benchmark.p1_java_sg2   1000  thrpt   60    0,308 ±  0,001  ops/us
    Problem1Benchmark.p1_java_lf    1000  thrpt   60    0,508 ±  0,003  ops/us
    Problem1Benchmark.p1_java_lg    1000  thrpt   60    8,411 ±  0,048  ops/us
    Problem1Benchmark.p1_scala_g    1000  thrpt   60   37,017 ±  1,441  ops/us
    Problem1Benchmark.p1_java_s     1000  thrpt   60  108,149 ±  0,511  ops/us
    Problem1Benchmark.p1_scala_s    1000  thrpt   60  108,190 ±  0,523  ops/us


    # Run complete. Total time: 00:05:22

    Benchmark                       (p1)   Mode  Cnt    Score    Error   Units
    Problem1Benchmark.p1_clojure_f  1000  thrpt   30    0,018 ±  0,001  ops/us
    Problem1Benchmark.p1_clojure_g  1000  thrpt   30    0,146 ±  0,003  ops/us
    Problem1Benchmark.p1_clojure_s  1000  thrpt   30    3,156 ±  0,168  ops/us
    Problem1Benchmark.p1_java_s     1000  thrpt   30  104,919 ±  3,171  ops/us
    Problem1Benchmark.p1_scala_s    1000  thrpt   30  106,376 ±  0,838  ops/us


    # Run complete. Total time: 00:02:09

    Benchmark                       (p1)   Mode  Cnt  Score   Error   Units
    Problem1Benchmark.p1_clojure_g  1000  thrpt   30  0,142 ± 0,007  ops/us
    Problem1Benchmark.p1_clojure_s  1000  thrpt   30  7,327 ± 0,165  ops/us

added type hints (two fold performance improvement)


    # Run complete. Total time: 00:02:09

    Benchmark                       (p1)   Mode  Cnt  Score   Error   Units
    Problem1Benchmark.p1_clojure_g  1000  thrpt   30  0,144 ± 0,003  ops/us
    Problem1Benchmark.p1_clojure_s  1000  thrpt   30  7,661 ± 0,467  ops/us


some more type hints

    # Run complete. Total time: 00:02:10

    Benchmark                       (p1)   Mode  Cnt  Score   Error   Units
    Problem1Benchmark.p1_clojure_g  1000  thrpt   30  0,123 ± 0,008  ops/us
    Problem1Benchmark.p1_clojure_s  1000  thrpt   30  7,986 ± 0,093  ops/us


frege

    # Run complete. Total time: 00:31:24

    Benchmark                       (p1)   Mode  Cnt   Score    Error   Units
    Problem1Benchmark.p1_frege_f    1000  thrpt   60   0,006 ±  0,001  ops/us
    Problem1Benchmark.p1_frege_g    1000  thrpt   60   0,011 ±  0,001  ops/us
    Problem1Benchmark.p1_clojure_f  1000  thrpt   60   0,014 ±  0,001  ops/us
    Problem1Benchmark.p1_scala_f    1000  thrpt   60   0,069 ±  0,002  ops/us
    Problem1Benchmark.p1_java_sf    1000  thrpt   60   0,091 ±  0,001  ops/us
    Problem1Benchmark.p1_clojure_g  1000  thrpt   60   0,112 ±  0,003  ops/us
    Problem1Benchmark.p1_java_sg1   1000  thrpt   60   0,129 ±  0,002  ops/us
    Problem1Benchmark.p1_java_sg2   1000  thrpt   60   0,240 ±  0,008  ops/us
    Problem1Benchmark.p1_java_lf    1000  thrpt   60   0,396 ±  0,006  ops/us
    Problem1Benchmark.p1_clojure_s  1000  thrpt   60   6,388 ±  0,100  ops/us
    Problem1Benchmark.p1_java_lg    1000  thrpt   60   7,916 ±  0,037  ops/us
    Problem1Benchmark.p1_frege_s    1000  thrpt   60  25,345 ±  0,385  ops/us
    Problem1Benchmark.p1_scala_g    1000  thrpt   60  32,633 ±  0,445  ops/us
    Problem1Benchmark.p1_java_s     1000  thrpt   60  89,167 ±  1,764  ops/us
    Problem1Benchmark.p1_scala_s    1000  thrpt   60  90,317 ±  2,252  ops/us