(ns hamming)

(defn distance [x y]
  (if (= (count x) (count y))
    (->>
     (map not= x y)
     (filter true?)
     (count))))
