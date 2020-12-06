(ns day1.solve
  (:require [clojure.string :as s]))

(defn read-input [filename]
  (->> filename
       slurp
       s/split-lines
       (map #(Integer/parseInt %))
       set))

(defn multiply-two
  "Finds two numbers that sum to the given number, and returns their product."
  [filename sum]
  (let [numbers (read-input filename)]
    (for [a numbers
          b numbers
          :when (= sum (+ a b))
          :when (< a b)]
      (* a b))))

(multiply-two "./src/day1/input.txt" 2020)

(defn multiply-three
  "Finds three numbers that sum to the given number, and returns their product."
  [filename sum]
  (let [numbers (read-input filename)]
    (for [a numbers
          b numbers
          c numbers
          :when (= sum (+ a b c))
          :when (< a b c)]
      (* a b c))))

(multiply-three "./src/day1/input.txt" 2020)