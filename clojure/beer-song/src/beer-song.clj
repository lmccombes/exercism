(ns beer-song
  (:require [clojure.string :as str]))

(defn how-many-beers-left [num-of-bottles]
  (cond
    (= num-of-bottles 1) "1 bottle of beer"
    (zero? num-of-bottles) "no more bottles of beer"
    :else (format "%d bottles of beer" num-of-bottles)))

(defn what-to-do [num-of-bottles]
  (cond
    (= num-of-bottles 1) "Take it down and pass it around"
    (zero? num-of-bottles) "Go to the store and buy some more"
    :else "Take one down and pass it around"))

(defn new-num-of-bottles [num-of-bottles]
  (if (pos? num-of-bottles)
    (dec num-of-bottles)
    99))

(defn first-sentence [num-of-bottles]
  (let [beers-left (how-many-beers-left num-of-bottles)]
    (str (str/capitalize beers-left) " on the wall, " beers-left ".")))

(defn second-sentence [num-of-bottles]
  (str (what-to-do num-of-bottles) ", "
       (how-many-beers-left (new-num-of-bottles num-of-bottles))
       " on the wall."))

(defn verse [num-of-bottles]
  (str (first-sentence num-of-bottles)
       "\n"
       (second-sentence num-of-bottles)
       "\n"))

(defn verse-range [start & [end]]
  (let [end-or-zero (or end 0)]
    (reverse (range end-or-zero (inc start)))))

(defn sing [start & [end]]
  (let [verses (map verse (verse-range start end))]
    (apply str (interpose "\n" verses))))
