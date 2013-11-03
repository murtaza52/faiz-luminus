(ns faiz.data.init
  (:require [faiz.data.datomic :as dt]
            [faiz.data.api :as api]
            [faiz.config :refer [config]]
            [faiz.utils :as utils]
            [faiz.config :as config]))

(defn adddata
  "Populates data in the db"
  [conn file]
  (trans (utils/read-clj file) conn))

()

