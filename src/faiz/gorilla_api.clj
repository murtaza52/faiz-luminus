(ns faiz.gorilla-api
  (:require [faiz.data.api :as api]
            [faiz.utils :as utils]
            [clojure.pprint :as p]))

(def attrs-to-display #{:person/its :person/first-name :person/middle-name :person/last-name :person/mobile})

(def filter-person (utils/filter-map attrs-to-display))

(def remove-entity-type #(dissoc % :common/entity-type))

(defn find-generic [k v]
  (cond
      (= k :db/id) #{[v]}
      :else ((api/find-en k) v)))

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

(def persons-not-receiving-thaali (comp
                                     print-en
                                     format-entities
                                     api/get-en
                                     api/persons-not-receiving-thaali))

(persons-not-receiving-thaali)

(defn create-me [m]
  (api/upsert-en m))

(api/get-en #{[17592186045442]})

(find-generic :person/its 20341280)

(find-me :db/id 17592186045442)

;; Four different concerns of the API
;; search for entity
;; realize the entity
;; format the entity
;; display the entity
