(ns leiningen.kalar
  ;  (:import (java.io File))
  [:use [clojure.java.io :only [file]]
        [leinjacker.eval :only [eval-in-project]]]
  [:require [clj-yaml.core :as yaml]
            [hiccup.core :as hcore]
            [hiccup.page :as hpage]])

(defn kalar
  "I don't do a lot.
  https://github.com/technomancy/leiningen/blob/master/doc/PLUGINS.md#evaluating-in-project-context"
  [project & args]
  (println (symbol (str (:name  project) ".core/config")))
  (println (symbol (str (:name  project) ".core")))
  (let [a (symbol (str (:name  project) ".core/config"))
        ]
    (eval-in-project project `(println ~a) '(require 'kalar-demo.core))))


(defn load-project [project]
  (let [possible-config-dest (map #(file % "config.yml") (:resource-paths project))
        config (first (filter #(.exists %) possible-config-dest))]
    (println (yaml/parse-string (slurp (.getAbsolutePath config))))))
