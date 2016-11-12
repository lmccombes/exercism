(ns bob)

(defn shouting? [s]
  (let [only-letters (filter (fn [^Character ch] (Character/isLetter ch)) s)]
    (and (not-empty only-letters)
         (every? (fn [^Character ch] (Character/isUpperCase ch)) only-letters))))

(defn response-for [says]
  (cond
    (shouting? says) "Whoa, chill out!"
    (empty? (clojure.string/trim says)) "Fine. Be that way!"
    (clojure.string/ends-with? says "?") "Sure."
    :else "Whatever."))

