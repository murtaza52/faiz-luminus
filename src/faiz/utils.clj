(ns faiz.utils
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.string :as st]
            [camel-snake-kebab :as camel]))


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

(defn filter-map [ks]
  (fn [coll]
   (into
     {}
     (filter
        #(some ks [(first %)])
        coll))))

(def normalize (comp name camel/->CamelCaseWithSpace))

(defn normalize-keys [m]
  (into {}
        (map (fn [[k v]]
               [(normalize k) v])
             m)))

