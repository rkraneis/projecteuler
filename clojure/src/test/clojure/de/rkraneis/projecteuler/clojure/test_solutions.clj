(ns de.rkraneis.projecteuler.clojure.test-solutions
  (:use [clojure.test])
  (:require [de.rkraneis.projecteuler.clojure.solutions :refer :all]))

(deftest test-problem1 
  (is (= 233168 (problem1-generated 1000)))
  (is (= 233168 (problem1-filtered 1000))))

(deftest test-problem2
  (is (= 4613732 (problem2 4000000))))

(deftest test-problem3
  (is (= 6857 (problem3 600851475143))))

(deftest test-problem4
  (is (= 906609 (problem4 100 1000))))

(deftest test-problem5
  (is (= 232792560 (problem5))))

(deftest test-problem6
  (is (= 25164150 (problem6 100))))

(deftest test-problem7
  (is (= 104743 (problem7 10001))))