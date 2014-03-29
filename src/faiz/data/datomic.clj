(ns faiz.data.datomic
  (:require [datomic.api :refer [db q] :as d]
            [faiz.config :refer [config]]
            [plumbing.core :refer [fnk]]
            [plumbing.graph :as graph]
            [schema.core :as s]
            [faiz.utils :as utils]))

(defn reset-db!
  [uri]
  (d/delete-database uri)
  (d/create-database uri))

(defn trans
  "Utility function for transacting data"
  [tx-data conn]
  @(d/transact conn tx-data))

(defn add-data
  "Populates data in the db"
  [conn file]
  (trans (utils/read-clj file) conn))

;;;;;;;;;;;;;;;;;; Query fns ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

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
        {:keys [db-after tempids]} (trans [m-with-id] conn)
        id (d/resolve-tempid db-after tempids (m-with-id :db/id))]
    (d/entity db-after id)))

;;;;;;;;;;;;;;;;;;;;;;; Graph ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def dt
  (graph/lazy-compile
   (graph/graph
    :reset (fnk [uri] (reset-db! uri))
    :conn (fnk [uri] (d/connect uri))
    :add-data (fnk [conn] (partial add-data conn))
    :schema-res (fnk [schema add-data] (doall (map add-data schema)))
    :seed-data-res (fnk [seed-data add-data] (doall (map add-data seed-data)))
    :qu (fnk [conn] (partial qu conn))
    :id->entity (fnk [conn] (partial id->entity-2 conn))
    :get-en (fnk [id->entity] (comp realize-en id->entity))
    :upsert-en (fnk [conn] (partial upsert-en conn)))))

(defonce api (-> (config) :db dt))
