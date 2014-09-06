(ns user
  (:require [faiz.data.datomic :as dt]
            [faiz.config :refer [config]]
            [clojure.tools.namespace.repl :refer [refresh]]
            [faiz.server :as server]
            [cemerick.austin.repls]
            [cemerick.austin]))

;; the browser js tries to connect to the conn stored in repl-env atom
(def repl-env (reset! cemerick.austin.repls/browser-repl-env
                      (cemerick.austin/repl-env)))

;; starts the cljs repl
(defn cljs-repl []
  (cemerick.austin.repls/cljs-repl repl-env))

(def conf (config))

(defn start []
  (->> (reduce #(merge
                 (dt/start %2 %1)
                 %1)
               (conf :db)
               [dt/dev-tasks dt/ops-tasks])
       (reset! dt/api))
  (server/restart)
  nil)

(defn restart []
  (refresh :after 'user/start))


;(dt/start (merge dt/ops-tasks dt/dev-tasks) (conf :db))

;(dt/start (merge dt/ops-tasks dt/dev-tasks) (conf :db))
;; @dt/api
