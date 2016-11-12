(ns robot-name-test
  (:require [clojure.test :refer [deftest is]]
            robot-name))

;; (def robbie (robot-name/robot))

;; (def clutz  (robot-name/robot))

;; (deftest name-matches-expected-pattern
;;   (is (re-find (re-matcher #"[A-Z]{2}\d{3}" (robot-name/robot-name robbie)))))

;; (deftest name-is-persistent
;;   (is (= (robot-name/robot-name robbie) (robot-name/robot-name robbie))))

;; (deftest different-robots-have-different-names
;;   (is (not (= (robot-name/robot-name clutz) (robot-name/robot-name robbie)))))

;; (def original-name (robot-name/robot-name robbie))
;; (robot-name/reset-name robbie)

;; (deftest new-name-matches-expected-pattern
;;   (is (re-find (re-matcher #"[A-Z]{2}\d{3}" (robot-name/robot-name robbie)))))

;; (deftest new-name-is-persistent
;;   (is (= (robot-name/robot-name robbie) (robot-name/robot-name robbie))))

;; (deftest new-name-is-different-than-old-name
;;   (is (not (= original-name (robot-name/robot-name robbie)))))


;; (defn fake-generate-fn [fixed-name-atom new-name]
;;   (fn [] (let [original @fixed-name-atom
;;                _ (reset! fixed-name-atom new-name)]
;;            original)))

;; (deftest checks-if-name-already-used
;;   (let [first-name-generated (atom "john")]
;;     (with-redefs
;;       [robot-name/used-names (atom #{"john"})
;;        robot-name/generate-name (fake-generate-fn first-name-generated "adam")]
;;       (is (= "adam" (robot-name/robot-name (robot-name/robot)))))))

(deftest adds-names-to-used-name-set
  (let [used-names-atom (atom #{})]
    (with-redefs [robot-name/used-names used-names-atom
                  robot-name/generate-name (fn [] "steve")]
      (do
        (robot-name/robot)
        (is (= #{"steve"} @used-names-atom))))))
