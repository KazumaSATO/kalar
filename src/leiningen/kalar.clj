(ns leiningen.kalar)

(defn kalar
  "I don't do a lot."
  [project & args]
  (println  (:resource-paths project)))
