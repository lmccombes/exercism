(ns grade-school
  (:require [clojure.walk :as walk]))

(defn add [db name grade]
  (update db grade (fnil conj []) name))

(defn grade [db v]
  (db v []))

(defn sorted [db]
  (into (sorted-map)
        (for [[grade names] db]
          [grade (apply vector (sort names))])))
