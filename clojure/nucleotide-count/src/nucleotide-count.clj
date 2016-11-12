(ns nucleotide-count)

(def nucleotides #{\A \T \C \G})

(defn count [nucleotide dna]
  (clojure.core/count (filter #(= nucleotide %) dna)))

(defn nucleotide-counts [dna]
  (apply merge
        (map #(hash-map %1 (count %1 dna)) nucleotides)))







