(ns faiz.gorilla-api
  (:require [faiz.data.api :as api]
            [faiz.utils :as utils]
            [clojure.pprint :as p]))

(def attrs-to-display #{:person/its :person/first-name :person/middle-name :person/last-name :person/mobile})

(def filter-person (utils/filter-map attrs-to-display))

(def mumineen-not-receiving-thaali
  #(->>
    (api/persons-not-receiving-thaali)
    api/realize-person
    (map filter-person)
    (map utils/normalize-keys)
    p/print-table))

