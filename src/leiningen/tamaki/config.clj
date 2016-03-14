(ns leiningen.tamaki.config
  (:require [tamaki-core.config :as config]))


(defn read-config []
  (config/read-config "resources/config.edn"))
