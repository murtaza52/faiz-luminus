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

(defmethod search :all
  [_ v]
  (api/find-all v))

(defmethod search :default
  [k v]
  (api/find-en k v))

;; search where attr is given
;; this is usually a list

;; search where value is given
;; why cant i just search with the value

;; searh where :db/id is given
;; search where


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
                search))

(defn create-me [m]
  (api/upsert-en m))

(def person
  {:common/entity-type :common.entity-type/person
   :person/first-name "Mustafa"
   :person/middle-name "Shoeb"
   :person/last-name "Saoroda"
   :person/its 20341112
   :person/mobile 9923109056
   :person/email "abc@abc.com"
   :hub-commitment/term :hub-commitment.term/monthly
   :person/address {:db/id 17592186045489}})

(create-me person)

(def persons-not-receiving-thaali (comp
                                     print-en
                                     format-entities
                                     api/get-en
                                     api/persons-not-receiving-thaali))

