(ns leiningen.tamaki
  [:require [tamaki-core.invoke :as invk]
            [leiningen.core.eval :as leval]])

(defn tamaki
  "Automatically write all the project's code."
  ;{:subtasks [#'clean]}
  [project & args]
  (if (invk/defined-step? (first args))
    (leval/eval-in-project project `(tamaki-core.invoke/build ~@args) '(require 'tamaki-core.invoke))
    (leiningen.core.main/warn "Unknown task.")))
