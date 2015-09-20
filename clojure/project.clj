(defproject de.rkraneis.projecteuler/projecteuler-clojure "0.1-SNAPSHOT"
  :min-lein-version "2.0.0"
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :target-path "target/%s"
  :main nil
  :aot [de.rkraneis.projecteuler.clojure.solutions]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.namespace "0.2.11"]])

(comment "lein test-refresh" for automatic testing)
