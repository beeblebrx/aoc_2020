(ns day3.solve
  (:require [clojure.string :as s]))

(defn read-input [filename]
  (->> filename
       slurp
       s/split-lines))

(defn take-steps-right
  "Add n to position and take modulus of width"
  [width n position]
  (mod (+ n position) width))

(defn count-trees [forest steps-right steps-down]
  "Count trees in the path
   - count width of input
   - make a lazy seq of column positions by adding steps-right to previous position and taking modulus of width
   - take a column from every steps-down row using positions from previous seq
   - filter only spots with trees
   - count how many trees you got"
  (let [width (count (first forest))
        columns (iterate (partial take-steps-right width steps-right) 0)
        spots (map nth (take-nth steps-down forest) columns)
        trees (filter #(= \# %) spots)]
    (count trees)))

; Part 1
(count-trees (read-input "./src/day3/input.txt") 3 1)

; Part 2
(let [forest (read-input "./src/day3/input.txt")]
  (* (count-trees forest 1 1)
     (count-trees forest 3 1)
     (count-trees forest 5 1)
     (count-trees forest 7 1)
     (count-trees forest 1 2)))
