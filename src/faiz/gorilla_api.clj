(ns faiz.gorilla-api
  (:require [faiz.data.api :as api]
            [faiz.utils :as utils]
            [clojure.pprint :as p]))

(def attrs-to-display #{:person/its :person/first-name :person/middle-name :person/last-name :person/mobile})

(def filter-person (utils/filter-map attrs-to-display))

(def remove-entity-type #(dissoc % :common/entity-type))

(def mumineen-not-receiving-thaali
  #(->>
    (api/persons-not-receiving-thaali)
    api/realize-person
    (map filter-person)
    (map utils/normalize-keys)
    p/print-table))

(defn find-me [k v]
  (let [en (cond
             (= k :db/id) [(api/retrieve-by-id v)]
             :else ((api/find-en k) v))]
    (->>
        en
        (map #(into {} %))
        (map remove-entity-type)
        (map utils/normalize-keys)
        p/print-table)))

(defn create-me [m]
  (api/upsert-en m))
