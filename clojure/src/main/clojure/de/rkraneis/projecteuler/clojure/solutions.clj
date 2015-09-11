(ns de.rkraneis.projecteuler.clojure.solutions
  (:require [de.rkraneis.projecteuler.clojure.util :refer :all]))

(defn problem1-generated
  "Find the sum of all the multiples of 3 or 5 below 1000."
  [n]
  (-
    (+
      (reduce + (range 0 n 3))
      (reduce + (range 0 n 5)))
    (reduce + (range 0 n 15))))

(defn problem1-filtered
  "Find the sum of all the multiples of 3 or 5 below 1000."
  [n]
  (reduce + (filter
    #(or
      (zero? (mod % 3))
      (zero? (mod % 5)))
    (range n))))

(defn problem1-series
  "Find the sum of all the multiples of 3 or 5 below 1000."
  [^long n]
  (let [m (long (- n 1))]
    (-
      (+
        (series-sum m 3) (series-sum m 5))
      (series-sum m 15))))

(defn problem2
  "By considering the terms in the Fibonacci sequence whose values do not
  exceed four million, find the sum of the even-valued terms."
  [n]
  (reduce + (take-while
    (partial >= n)
    (filter even? fib))))

(defn problem3
  "What is the largest prime factor of the number 600851475143"
 [n]
 (first (factor n)))

(defn problem4
  "Find the largest palindrome made from the product of two 3-digit number"
  [n m]
  (largest-palindrome n m))

(defn problem5
  "What is the smallest positive number that is evenly divisable by all of the
  numbers from 1 to 20?"
  [] (comment "code a 'greatest common divisor' function and use that instead of pencil and paper")
  (* 2 2 2 2 3 3 5 7 11 13 17 19))

(defn problem6
  "Find the difference between the sum of the squares of the first one hundred
  natural numbers and the square of the sum."
  [n]
  (- (#(* % %) (reduce + (range 1 (+ n 1))))
     (reduce + (map #(* % %) (range 1 (+ n 1))))))

(defn problem7
  "By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
  that the 6th prime is 13.
  What is the 10 001st prime number?"
  [n] (comment "lazyly evaluate all numbers and take the 10 001st")
  (last (take n (filter #(not (nil? %)) (map prime? (range))))))

(defn problem8
  "The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.

  73167176531330624919225119674426574742355349194934
  96983520312774506326239578318016984801869478851843
  85861560789112949495459501737958331952853208805511
  12540698747158523863050715693290963295227443043557
  66896648950445244523161731856403098711121722383113
  62229893423380308135336276614282806444486645238749
  30358907296290491560440772390713810515859307960866
  70172427121883998797908792274921901699720888093776
  65727333001053367881220235421809751254540594752243
  52584907711670556013604839586446706324415722155397
  53697817977846174064955149290862569321978468622482
  83972241375657056057490261407972968652414535100474
  82166370484403199890008895243450658541227588666881
  16427171479924442928230863465674813919123162824586
  17866458359124566529476545682848912883142607690042
  24219022671055626321111109370544217506941658960408
  07198403850962455444362981230987879927244284909188
  84580156166097919133875499200524063689912560717606
  05886116467109405077541002256983155200055935729725
  71636269561882670428252483600823257530420752963450

  Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?"
  [n d]
  (key
    (last
      (into (sorted-map)
            (map #(vector (apply * (map char-to-int %)) %)
                 (partition d 1 n))))))