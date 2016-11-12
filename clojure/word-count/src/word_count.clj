(ns word-count
  (:require [clojure.string :as string]))

(defn remove-punctuation [s]
  (string/replace s #"[^\w\s]" ""))

(defn split-on-whitespace [s]
  (string/split s #"\s+"))

(defn word-count [s]
  (-> s
      remove-punctuation
      string/lower-case
      split-on-whitespace
      frequencies))
