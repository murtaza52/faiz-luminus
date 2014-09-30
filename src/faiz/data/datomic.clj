(ns faiz.data.datomic
  (:require [datomic.api :refer [db q] :as d]
            [plumbing.core :refer [fnk defnk]]
            [plumbing.graph :as g]
            [schema.core :as s]
            [faiz.utils :as utils]
            [dire.core :refer [with-handler!]]
            [slingshot.slingshot :refer [throw+]]))

(defn connect [uri]
  "Returns the connection to the server. Helpful in debuging."
  (d/connect uri))

(defn vec-or-list-of-vec? [v]
  (and (vector? v) (vector? (first v))))

(defn or-vec [v]
  (if (vec-or-list-of-vec? v) v [v]))

(defn transact [conn v]
  "Transacts a list of items (v). Derefs the promise before returning this forces the realization of any errors."
  @(d/transact conn (or-vec v)))

(defn transact-each
  "Populates data in the db"
  [conn coll]
  (map (partial transact conn) coll))

(defn reset-db!
  [uri]
  (d/delete-database uri)
  (d/create-database uri))

(defnk process [uri seed-data schema]
  (reset-db! uri)
  (let [conn (d/connect uri)]
    {:conn conn
     :schema (transact-each conn (utils/read-clj schema))
     :seed-data (transact conn (utils/read-clj seed-data))}))

(with-handler! #'transact
  java.util.concurrent.ExecutionException
  (fn [e _ v] (throw+ {:error-while-transacting v})))


;;;;;;;;;;;;;;;;;; Query fen's ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn qu
  "Function for querying"
  ([conn clause]
   (d/q clause
        (d/db conn)))
  ([conn clause & p]
   (apply d/q clause
          (d/db conn)
          p)))

(defn id->entity-2
  [conn ids]
  (doall
   (map #(d/entity (d/db conn) (first %))
        ids)))

(defn realize-en
  [en-maps]
  (doall
   (map #(d/touch %) en-maps)))

;;;;;;;;;;;;;;;;;;;; Creating Entities ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn add-if
  [m k v]
  (if (m k)
    m
    (assoc m k v)))

(def get-temp-id #(d/tempid :db.part/user))

(defn upsert-en
  [conn m]
  (let [m-with-id (add-if m :db/id (get-temp-id))
        {:keys [db-after tempids]} @(d/transact conn [m-with-id])
        id (d/resolve-tempid db-after tempids (m-with-id :db/id))]
    (d/entity db-after id)))

;;;;;;;;;;;;;;;;;;;;;;; Graph ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn start [graph conf]
  (let [compiled-graph (g/eager-compile graph)]
    (compiled-graph conf)))

;; (def dev-tasks
;;   (g/graph  {:reset-db! reset-db!
;;              :conn (fnk [uri] (d/connect uri))
;;              :alter-schema! alter-schema!
;;              :seed-data! seed-data!}))

(def ops-tasks
  (g/graph  {:qu (fnk [conn] (partial qu conn))
             :id->entity (fnk [conn] (partial id->entity-2 conn))
             :get-en (fnk [id->entity] (comp realize-en id->entity))
             :upsert-en (fnk [conn] (partial upsert-en conn))}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def api (atom {}))

(defn conn []
  (@api :conn))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;(def reset (start dev-tasks))

;; compose the dev fns till seed with

;; keep the conn state in  local var in the api sectioPn

;; check if the errors percolate

;; check for the dev mode
