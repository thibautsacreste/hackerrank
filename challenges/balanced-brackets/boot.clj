#!/usr/bin/env boot

(require '[clojure.string :refer [trim split]])

(def opening (seq "{[("))
(def closing (seq "}])"))
(def matching (zipmap opening closing))

(defn -main [& args]
  (let [n (-> (read-line) trim Integer/parseInt)]
    (loop [i n]
      (when (> i 0)
        (let [brackets (-> (read-line) trim seq)
              final (reduce
                      (fn [stack bracket]
                        (cond
                          (some #{bracket} opening) (conj stack (get matching bracket))
                          (= (last stack) bracket) (pop stack)
                          :else (reduced (conj stack bracket))))

                      []
                      brackets)]
          (if (empty? final) (println "YES") (println "NO")))
        (recur (- i 1))))))
