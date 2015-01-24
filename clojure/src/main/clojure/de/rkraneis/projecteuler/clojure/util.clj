(ns de.rkraneis.projecteuler.clojure.util)

(defn baseline [] 42)

(def fib 
  (lazy-cat
    [0 1]
    (map + fib (rest fib))))

