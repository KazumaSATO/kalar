(ns leiningen.tamaki.post
  [:require [clojure.java.io :as io]
            [me.raynes.fs :as fs]
            [tamaki.post.post :as tpost]
            [tamaki.template.page :as tpage]
            [tamaki.text.html :as thtml]
            [leiningen.tamaki.config :as cnfg]
            [net.cgrand.enlive-html :as ehtml]
            [tamaki.text.similarity :as simi]]
  [:import (java.io StringReader)]
  )

(defn calc-post-similarity
  ([] (calc-post-similarity (-> (cnfg/read-config) :post-dir) (-> (cnfg/read-config) :dest)))
  ([post-dir dest-root]
   (letfn [(path-body [post]
             (let [path (.getPath post)
                   md (tpage/read-postmd path dest-root)
                   body (thtml/extract-text (ehtml/html-resource (StringReader. (:body md))))]
               {:key path  :text body}))
           ]
     (let [key-texts (map #(path-body %) (tpost/post-seq (io/file post-dir)))]
       (for [key-text key-texts]
         {:post (:key key-text)
          :relation (map #(assoc {} :post (:key %) :score (:score %))
                         (simi/calc-similarity (:text key-text) (remove #(= (:key key-text) (:key %)) key-texts)))})))))


