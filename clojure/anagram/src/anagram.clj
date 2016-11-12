(ns anagram
  (:require [clojure.string :refer [lower-case]]))

(defn is-anagram? [word possible-anagram]
  (let [word-lc (lower-case word)
        possible-anagram-lc (lower-case possible-anagram)
        letters-needed (merge-with - (frequencies word-lc) (frequencies possible-anagram-lc))]
    (if (not= word-lc possible-anagram-lc)
      (every? zero? (vals letters-needed)))))

(defn anagrams-for [word possible-anagrams]
  (filter (partial is-anagram? word) possible-anagrams))
