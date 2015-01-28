(ns de.rkraneis.projecteuler.clojure.util)

(defn baseline [] 42)

(def fib 
  (lazy-cat
    [0 1]
    (map + fib (rest fib))))

(defn divides [n k] (= 0 (rem n k)))

(defn factor
  "return factors of n"
  ([n] (factor n 2 ())) 
  ([n k acc]
   (if (= 1 n) acc
     (if (divides n k) 
       (recur (quot n k) k (cons k acc))
       (recur n (inc k) acc)))))
