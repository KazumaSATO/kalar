(ns leiningen.tamaki.post-test
  (:require [clojure.test :refer :all]
            [me.raynes.fs :as fs]
            [leiningen.tamaki.post :as tpost]
            [clojure.java.io :as io]))

(def ^:private post-dir "dev-resources/leiningen/tamaki/post")
(def ^:private post-dest "dev-resources/_site")

(deftest test-calc-post-similarity
  (testing "calculation"
    (let [result  (tpost/calc-post-similarity post-dir post-dest)]
      (doseq [entity result]
        (is (fs/exists? (:post entity))))))
  (testing "write similarity"
    (let [dir "dev-resources/_report/"
          written (str dir "similarity.edn")]
      (fs/mkdirs dir)
      (tpost/report-post-similarity written post-dir post-dest)
      (let [from-txt (clojure.edn/read-string (slurp written))]
        (is (= 3 (count from-txt)))))))