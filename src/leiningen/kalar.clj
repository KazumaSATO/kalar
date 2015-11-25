(ns leiningen.kalar
  [:use [clojure.java.io :only [file]]
        [leinjacker.eval :only [eval-in-project]]]
  [:require [hiccup.core :as hcore]
            [hiccup.page :as hpage]])

(defn kalar
  "I don't do a lot.
  https://github.com/technomancy/leiningen/blob/master/doc/PLUGINS.md#evaluating-in-project-context"
  [project & args]
  (let [config (symbol (str (:name  project) ".core/config"))
        config-ns (symbol (str (:name  project) ".core"))]
    (eval-in-project project `(println ~config) `(require '~config-ns))
    (eval-in-project project `(println (meta  (nth (vals (ns-publics 'kalar-demo.temp)) 0))) `(require 'kalar-demo.temp))
    ))



