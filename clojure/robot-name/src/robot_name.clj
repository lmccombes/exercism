(ns robot-name
  (:require [clojure.test :refer [deftest is]]))

(defn robot [] (atom nil))

(def used-names (atom #{}))

(defn name-used? [name]
  (contains? @used-names name))

(defn rand-letter []
  (char (+ 65 (rand-int 26))))

(defn generate-name []
  (format "%c%c%d%d%d"
          (rand-letter)
          (rand-letter)
          (rand-int 10)
          (rand-int 10)
          (rand-int 10)))

(defn remove-name-from-used [name]
  (let [is-name? #(= name %)]
    (swap! used-names #(set (remove is-name? %)))))

(defn reset-name [robot]
  (remove-name-from-used @robot)
  (reset! robot nil))


(defn add-name [robot]
  (loop [new-name (generate-name)]
    (if (contains? @used-names new-name)
      (recur (generate-name))
      (do
        (swap! used-names conj new-name)
        (reset! robot new-name)))))

(defn robot-name [robot]
  (if-let [current @robot]
    current
    (add-name robot)))

;; Below are the extra tests I added to test-drive the checking of unique names

(defn fake-generate-fn [fixed-name-atom new-name]
  (fn [] (let [original @fixed-name-atom
               _ (reset! fixed-name-atom new-name)]
           original)))

(deftest checks-if-name-already-used
  (let [first-name-generated (atom "john")]
    (with-redefs
      [robot-name/used-names (atom #{"john"})
       robot-name/generate-name (fake-generate-fn first-name-generated "adam")]
      (is (= "adam" (robot-name/robot-name (robot-name/robot)))))))

(deftest adds-names-to-used-name-set
  (with-redefs [robot-name/used-names (atom #{})
                robot-name/generate-name (fn [] "steve")]
    (robot-name/robot-name (robot-name/robot))
    (is (= #{"steve"} @robot-name/used-names))))

(deftest removes-old-name-when-name-reset
  (with-redefs [robot-name/used-names (atom #{"jane"})]
    (robot-name/reset-name (atom "jane"))
    (is (= @robot-name/used-names #{}))))
