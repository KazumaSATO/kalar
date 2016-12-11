(ns leiningen.tamaki
  [:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [leiningen.core.eval :as leval]
            [me.raynes.fs :as fs]])



(defn tamaki
  "Automatically write all the project's code."
  ;{:subtasks [#'clean]}
  [project & args]
  (println (leval/eval-in-project project `(tamaki-core.invoke/invoke ~@args) '(require 'tamaki-core.invoke)))
  (case (first args)
    ;    "clean" (leval/eval-in-project project '(tamaki-core.file/clean-dest) '(require 'tamaki-core.file))
    ;"compile" (leval/eval-in-project project '(tamaki-core.server/tcompile) '(require 'tamaki-core.server))
    ;"similarity" (leval/eval-in-project project '(tamaki.post.post/report-post-similarity) '(require 'tamaki.post.post))
    ; nil            :not-implemented-yet
    (leiningen.core.main/warn "Unknown task.")))
