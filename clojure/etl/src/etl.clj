(ns etl
  (:require [clojure.string :as str]))

(defn map-v-to-lowercased-k [k v]
  (hash-map (str/lower-case v) k))

(defn transform-reducer
  [m k v]
  (->> (mapcat (partial map-v-to-lowercased-k k) v)
       (merge m)))

(defn transform [legacy]
  (reduce-kv transform-reducer {} legacy))
