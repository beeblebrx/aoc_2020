(ns day2.solve
  (:require [clojure.string :as s]))

(defn parse-line [line]
  (let [[_ min max char password] (re-matches #"(\d+)-(\d+) (\w): (\w+)" line)]
    {:min (Integer/parseInt min)
     :max (Integer/parseInt max)
     :char (first char)
     :password password}))

(defn read-input [filename]
  (->> filename
       slurp
       s/split-lines
       (map parse-line)))

(defn valid-occurrences?
  "Part 1: count occurrences of a character in the password"
  [{:keys [min max char password]}]
  (let [times-found (->> password
                         (filter #(= char %))
                         count)]
    (<= min times-found max)))

(defn valid-positions?
  "Part 2: check positions of a character in the password"
  [{:keys [min max char password]}]
  (let [picks (str (nth password (dec min)) (nth password (dec max)))]
    (and
     (some #(= char %) picks)
     (not (every? #(= char %) picks))))
  )

; Part 1
(->> "./src/day2/input.txt"
     read-input
     (filter valid-occurrences?)
     count)

; Part 2
(->> "./src/day2/input.txt"
     read-input
     (filter valid-positions?)
     count)

