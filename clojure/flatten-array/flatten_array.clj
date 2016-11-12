(ns flatten-array)

(defn flatten [coll]
  (filter number? (tree-seq sequential? seq coll)))
