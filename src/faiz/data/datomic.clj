(ns faiz.data.datomic
  (:require [datomic.api :refer [db q] :as d]
            [faiz.config :refer [config]]
            [plumbing.core :refer [fnk defnk]]
            [plumbing.graph :as graph]
            [schema.core :as s]
            [faiz.utils :as utils]))

(def db-conf (atom (-> (config) :db)))

(def dev? true)

(defnk add-conn
  [uri]
  (swap! db-conf merge {:conn (d/connect uri)}))

(defn add-data
  "Populates data in the db"
  [conn file]
  (d/transact conn (utils/read-clj file)))

(defnk reset-db!
  [uri]
  (d/delete-database uri)
  (d/create-database uri))

(defnk alter-schema!
  [conn schema]
  (doall
   (map #(add-data conn %) schema)))

(defnk seed-data!
  [conn seed-data]
  (doall
   (map #(add-data conn %) seed-data)))

(if dev?
    (do
      (reset-db! @db-conf)
      (add-conn @db-conf)
      (alter-schema! @db-conf)
      (seed-data! @db-conf))
    (add-conn @db-conf))

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
        {:keys [db-after tempids]} @(d/transact conn [m-with-id])
        id (d/resolve-tempid db-after tempids (m-with-id :db/id))]
    (d/entity db-after id)))

;;;;;;;;;;;;;;;;;;;;;;; Graph ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def dt
  (graph/lazy-compile
   (graph/graph
    :qu (fnk [conn] (partial qu conn))
    :id->entity (fnk [conn] (partial id->entity-2 conn))
    :get-en (fnk [id->entity] (comp realize-en id->entity))
    :upsert-en (fnk [conn] (partial upsert-en conn)))))

(defonce api (dt @db-conf))
