(ns de.rkraneis.projecteuler.clojure.solutions
  (:require [de.rkraneis.projecteuler.clojure.util :refer :all]))

(defn problem1-generated
  "Find the sum of all the multiples of 3 or 5 below 1000."
  []
  (-  
    (+
      (reduce + (range 0 1000 3))
      (reduce + (range 0 1000 5)))
    (reduce + (range 0 1000 15))))

(defn problem1-filtered
  "Find the sum of all the multiples of 3 or 5 below 1000."
  []
  (reduce + (filter
    #(or 
      (zero? (mod % 3)) 
      (zero? (mod % 5)))
    (range 1000))))


(defn problem2 
  "By considering the terms in the Fibonacci sequence whose values do not
   exceed four million, find the sum of the even-valued terms."
  []
  (reduce + (take-while
    (partial >= 4000000)
    (filter even? fib))))

(defn problem3
  "What is the largest prime factor of the number 600851475143"
 []
 (first (factor 600851475143)))

(defn problem4
  "Find the largest palindrome made from the product of two 3-digit number"
  [] 
  (largest-palindrome 100 1000))

(defn problem5
  "What is the smallest positive number that is evenly divisable by all of the
   numbers from 1 to 20?"
  [] (comment "code a 'least common divisor' function and use that instead of pencil and paper")
  (* 2 2 2 2 3 3 5 7 11 13 17 19))