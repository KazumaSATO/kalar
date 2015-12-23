(ns leiningen.kalar
  [:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [me.raynes.fs :as fs]])

(defn- read-config []
  (-> (io/file "resources" "config.edn") slurp edn/read-string))

(defn- clean [project]
  (let [dest (read-config)]
    (fs/delete-dir (io/file "resources" (:dest dest)))))


(defn- compile [project]
  nil)

(defn kalar
  "Automatically write all the project's code."
  {:subtasks [#'clean]}
  [project & [sub-name]]
  (case sub-name
    "clean" (clean project)
    "compile" (compile project)
    nil            :not-implemented-yet
    (leiningen.core.main/warn "Unknown task.")))
