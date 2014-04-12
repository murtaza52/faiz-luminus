(ns user
  (:require [faiz.data.datomic :as dt]
            [faiz.config :refer [config]]
            [clojure.tools.namespace.repl :refer [refresh]]))

(def conf (config))

(defn start []
  (->> conf
       :db
       dt/start
       (swap! dt/api)))

(defn restart []
  (refresh :after 'user/start))
