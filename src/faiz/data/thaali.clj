(ns faiz.data.thaali
  (:require [faiz.data.datomic :as dt]
            [datomic.api :refer [db q] :as d]
            [faiz.data.api :as api]))

(defn connect [] (db (dt/conn)))

(defn all-thaalis [] (api/find-all :thaali/num))

(defmulti thaali-action (fn [action _] action))

(defmethod thaali-action :stop [_ thaali-num]
  (api/upsert  [{:db/id [:thaali/num thaali-num]
                 :thaali/active? false}]))

(defmethod thaali-action :start [_ thaali-num]
  (api/upsert [{:db/id [:thaali/num thaali-num]
                :thaali/active? true}]))

(defmethod thaali-action :to-full [_ thaali-num]
  (api/upsert [{:db/id [:thaali/num thaali-num]
                :thaali/size :thaali.size/full}]))

(defmethod thaali-action :to-half [_ thaali-num]
  (api/upsert [{:db/id [:thaali/num thaali-num]
                :thaali/size :thaali.size/half}]))

(defmethod thaali-action :to-transport [_ thaali-num]
  (api/upsert [{:db/id [:thaali/num thaali-num]
                :delivery/mode :delivery.mode/transport}]))

(defmethod thaali-action :to-pickup [_ thaali-num]
  (api/upsert [{:db/id [:thaali/num thaali-num]
                :delivery/mode :delivery.mode/pickup}]))

(defmethod thaali-action :new [_ thaali-map]
  (api/upsert-en (dt/conn) thaali-maap))

(defmethod thaali-action :change-address [_ thaali-map]
  (api/upsert-en (dt/conn) thaali-map))

(comment
  (thaali-action :start 21)

  (-> (api/find-en :thaali/num 21)
      ffirst
      api/get-entity))
