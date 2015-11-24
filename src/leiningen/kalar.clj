(ns leiningen.kalar
  ;  (:import (java.io File))
  [:use [clojure.java.io :only [file]]]
  [:require [clj-yaml.core :as yaml]])

(defn kalar
  "I don't do a lot."
  [project & args]
  (let [possible-config-dest (map #(file % "config.yml") (:resource-paths project))
        config (first (filter #(.exists %) possible-config-dest))]
    (println (yaml/parse-string (slurp (.getAbsolutePath config))))))

