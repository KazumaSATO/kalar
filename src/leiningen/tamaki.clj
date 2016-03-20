(ns leiningen.tamaki
  [:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [leiningen.core.eval :as leval]
            [me.raynes.fs :as fs]])

(defn- read-config []
  "TODO use tamaki-core.config.read-config"
  {:deprecated "0.1.2"}
  (-> (io/file "resources" "config.edn") slurp edn/read-string))

(defn- clean [project]
  (let [config (read-config)]
    (fs/delete-dir (io/file "resources" (:dest config)))))




(defn tamaki
  "Automatically write all the project's code."
  {:subtasks [#'clean]}
  [project & args]
  (case (first args)
    "clean" (leval/eval-in-project project '(tamaki-core.file/clean-dest) '(require 'tamaki-core.file))
    "compile" (leval/eval-in-project project '(tamaki-core.server/tcompile) '(require 'tamaki-core.server))
    "similarity" (leval/eval-in-project project '(tamaki.post.post/report-post-similarity) '(require 'tamaki.post.post))
    ; nil            :not-implemented-yet
    (leiningen.core.main/warn "Unknown task.")))

(comment
  (let [command (first args)
          rest-args (rest args)
          nmsp (symbol (str "kalar." command))
          func (symbol (str nmsp "/" command))]
      (leval/eval-in-project project `(~func ~@rest-args) `(require '~nmsp))))
