(ns leap)

(defn divisible-by? [year number]
  (zero? (rem year number)))

(defn is-exclusion? [year]
  (and (divisible-by? year 100)
       (not (divisible-by? year 400))))

(defn leap-year? [year]
  (and (divisible-by? year 4)
       (not (is-exclusion? year))))





