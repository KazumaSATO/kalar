(ns leiningen.tamaki.post-test
  (:require [clojure.test :refer :all]
            [me.raynes.fs :as fs]
            [leiningen.tamaki.post :as tpost]
            [clojure.java.io :as io]))

(deftest test-calc-post-similarity
  (testing "calculation"
    (let [result  (tpost/calc-post-similarity "dev-resources/leiningen/tamaki/post" "dev-resources/_site")]
      (doseq [entity result]
        (is (fs/exists? (:post entity)))))))