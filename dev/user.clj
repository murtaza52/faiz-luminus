(ns user
  (:require [faiz.data.datomic :as dt]
            [faiz.config :refer [config]]
            [clojure.tools.namespace.repl :refer [refresh]]))

(def conf (config))

(defn start []
  (->> (reduce #(merge
                 (dt/start %2 %1)
                 %1)
               (conf :db)
               [dt/dev-tasks dt/ops-tasks])
       (reset! dt/api))
  nil)

(defn restart []
  (refresh :after 'user/start))


;(dt/start (merge dt/ops-tasks dt/dev-tasks) (conf :db))

;(dt/start (merge dt/ops-tasks dt/dev-tasks) (conf :db))
;; @dt/api
