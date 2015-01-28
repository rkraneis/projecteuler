(ns de.rkraneis.projecteuler.clojure.test-util
  (:use [clojure.test])
  (:require [de.rkraneis.projecteuler.clojure.util :refer :all]))

(deftest test-baseline (is (= 42 (baseline))))

(deftest test-fib
  (is (= '(0 1 1 2 3 5 8 13 21 34 55 89) (take 12 fib))))

(deftest test-divides 
  (is (= true (divides 4 2)))
  (is (= false (divides 4 3)))) 

(deftest test-factors
  (is (= '(5) (factor 5)))
  (is (= '(3 2) (factor 6)))
  (is (= '(2 2 2) (factor 8))))
