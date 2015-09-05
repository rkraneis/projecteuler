(ns de.rkraneis.projecteuler.clojure.util)

(defn baseline ([] 42) ([n] n))

(def fib 
  (lazy-cat
    [0 1]
    (map + fib (rest fib))))

(defn divides? [n k] (zero? (rem n k)))

(defn factor
  "return factors of n"
  ([n] (factor n 2 ())) 
  ([n k acc]
   (if (= 1 n) acc
     (if (divides? n k) 
       (recur (quot n k) k (cons k acc))
       (recur n (inc k) acc)))))

(defn largest-palindrome
  "returns the largest palindrome made from the product of two numbers in 
   (range m n)"
  [m n]
  (first (sort #(> %1 %2)
               (filter #(= (str %) (clojure.string/reverse (str %))) 
                       (set (for [x (range m n) y (range m n)] (* x y)))))))

(defn prime?
  "tell if n is prime"
  ([n]
    (if (> 2 n) nil
      (prime? n 2)))
  ([n k]
    (if (> (* k k) n) n
      (if (divides? n k) nil
        (recur n (inc k))))))