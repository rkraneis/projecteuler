(ns de.rkraneis.projecteuler.clojure.test-solutions
  (:use [clojure.test])
  (:require [de.rkraneis.projecteuler.clojure.solutions :refer :all]))

(deftest test-problem1 
  (is (= 233168 (problem1-generated)))
  (is (= 233168 (problem1-filtered))))

(deftest test-problem2
  (is (= 4613732 (problem2))))

(deftest test-problem3
  (is (= 6857 (problem3))))

(deftest test-problem4
  (is (= 906609 (problem4))))

(deftest test-problem5
  (is (= 232792560 (problem5))))

(deftest test-problem6
  (is (= 25164150 (problem6))))