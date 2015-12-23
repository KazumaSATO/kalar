(ns leiningen.kalar
  [:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [leiningen.core.eval :as leval]
            [me.raynes.fs :as fs]])

(defn- read-config []
  (-> (io/file "resources" "config.edn") slurp edn/read-string))

(defn- clean [project]
  (let [config (read-config)]
    (fs/delete-dir (io/file "resources" (:dest config)))))


(defn- kompile [project]
  "FIXME"
  (leval/eval-in-project project '(kalar-core.server/load-plugins) '(require 'kalar-core.server)))

(defn kalar
  "Automatically write all the project's code."
  {:subtasks [#'clean]}
  [project & [sub-name]]
  (case sub-name
    "clean" (clean project)
    ;    "compile" (kompile project)
    nil            :not-implemented-yet
    (leiningen.core.main/warn "Unknown task.")))
