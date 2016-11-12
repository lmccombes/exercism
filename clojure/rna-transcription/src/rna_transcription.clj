(ns rna-transcription)

(def dna-rna-map
  {\G \C
   \C \G
   \A \U
   \T \A})

(defn dna-string-valid? [dna-string]
  (every? #(contains? dna-rna-map %) dna-string))

(defn to-rna [dna-string]
  (assert (dna-string-valid? dna-string))
  (apply str (map dna-rna-map dna-string)))
