(ns faiz.utils
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn read-edn
  [file]
  (-> file
      io/resource
      slurp
      edn/read-string))

(defn read-clj
  [file]
  (-> file
      io/resource
      slurp
      read-string))
