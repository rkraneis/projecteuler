(ns de.rkraneis.projecteuler.clojure.solutions
  (:require [de.rkraneis.projecteuler.clojure.util :refer :all]))
(set! *unchecked-math* :warn-on-boxed)

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
  [] (comment "code a 'least common divisor' function and use that instead of pencil and paper")
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
