(ns faiz.gorilla-api
  (:require [faiz.data.api :as api]
            [faiz.utils :as utils]
            [clojure.pprint :as p]))

(def attrs-to-display #{:person/its :person/first-name :person/middle-name :person/last-name :person/mobile})

(def filter-person (utils/filter-map attrs-to-display))

(def remove-entity-type #(dissoc % :common/entity-type))

(defmulti search (fn [& p] (first p)))

(defmethod search :db/id
  [_ v]
  #{[v]})

(defmethod search :all
  [_ v]
  (api/find-all v))

(defmethod search :default
  [k v]
  (api/find-en k v))

(defn format-entities [coll]
  (->>
        coll
        (map #(into {} %))
        (map remove-entity-type)
        (map utils/normalize-keys)
        p/print-table))

(def print-en p/print-table)

(def find-me (comp
                print-en
                format-entities
                api/get-en
                find-generic))

(defn create-me [m]
  (api/upsert-en m))

(def persons-not-receiving-thaali (comp
                                     print-en
                                     format-entities
                                     api/get-en
                                     api/persons-not-receiving-thaali))

