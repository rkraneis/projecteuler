(ns de.rkraneis.projecteuler.clojure.test-util
  (:use [clojure.test])
  (:require [de.rkraneis.projecteuler.clojure.util :refer :all]))

(deftest test-baseline (is (= 42 (baseline))))

(deftest test-series-sum
  (is (= 1 (series-sum 1)))
  (is (= 3 (series-sum 2)))
  (is (= 1 (series-sum 1 1)))
  (is (= 2 (series-sum 2 2))))

(deftest test-fib
  (is (= '(0 1 1 2 3 5 8 13 21 34 55 89) (take 12 fib))))

(deftest test-divides
  (is (= true (divides? 4 2)))
  (is (= false (divides? 4 3))))

(deftest test-factors
  (is (= '(5) (factor 5)))
  (is (= '(3 2) (factor 6)))
  (is (= '(2 2 2) (factor 8))))

(deftest test-largest-palindrome
  (is (= 9009 (largest-palindrome 10 100))))

(deftest test-prime
  (is (not (prime? 1)))
  (is (prime? 2))
  (is (prime? 3))
  (is (not (prime? 4)))
  (is (prime? 5))
  (is (not (prime? 6)))
  (is (prime? 7))
  (is (not (prime? 8)))
  (is (not (prime? 9)))
  (is (not (prime? 10)))
  (is (prime? 11))
  (is (not (prime? 12)))
  (is (prime? 13))
  (is (prime? 6857)))
