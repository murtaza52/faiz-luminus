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

(defmethod thaali-action :to-transport [_ {:keys [thaali-num transporter]}]
    (api/upsert [{:db/id [:thaali/num thaali-num]
                :delivery/mode :delivery.mode/transport
                :delivery/transporter transporter}]))

(defmethod thaali-action :to-pickup [_ thaali-num]
  (let [transporter (api/find-attr-for-en :thaali/num thaali-num :delivery/transporter)]
    (api/upsert [{:db/id [:thaali/num thaali-num]
                  :delivery/mode :delivery.mode/pickup}
                 [:db/retract [:thaali/num thaali-num] :delivery/transporter transporter]])))


(comment

  (thaali-action :to-transport {:thaali-num 22 :transporter :delivery.transporter/hunaid-bhai})

  (thaali-action :stop 22)

  (-> (api/find-en :thaali/num 22)
      ffirst
      api/get-entity)

  (defmethod thaali-action :new [_ thaali-map]
  (api/upsert-en (dt/conn) thaali-maap))
)
