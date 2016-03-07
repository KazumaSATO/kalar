(ns leiningen.tamaki.post
  [:require [clojure.java.io :as io]
            [me.raynes.fs :as fs]
            [tamaki.post.post :as tpost]
            [leiningen.tamaki.config :as cnfg]
            [markdown.core :as md]])

(defn calc-post-similarity
  ([] (calc-post-similarity (-> (cnfg/read-config) :post-dir)))
  ([post-dir]
   (letfn [(tohtml [md-file]
             (md/md-to-html-string (slurp md-file)))]
     (let [posts (tpost/post-seq (io/file post-dir))]
       (println (map #(assoc {} :file (fs/absolute %) :text (tohtml %)) posts))))))