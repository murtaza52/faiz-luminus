(ns faiz.data.api
  (:require [faiz.data.datomic :as dt]
            [datomic.api :as d]))


(def search-clause '[:find ?e
                     :in $ ?v
                     :where])

(defn find-en
  [attribute]
  (fn [param]
    ((dt/api :find-en) (merge search-clause (vector '?e attribute '?v))
                       param)))

(defn get-entities
  [attribute]
  (fn [param]
    ((dt/api :find-en) (merge search-clause (vector '?e attribute '?v))
                       param)))

(defn realize-entities
  [k]
  (fn [s]
    (->>
     s
     (map first)
     (map #((dt/api :entity) [k %]))
     (map d/touch))))

(def realize-person (realize-entities :person/its))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; Search API ;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def find-person-by-its (find-en :person/its))

(def find-person-by-mobile (find-en :person/mobile))

(def find-thaali-by-num (find-en :thaali/num))

;; retrieve address of a person, return all thaalis, pending hubs.
(def given-person-get-thaalis)

;; retrieve all active thaalis for the address of a particular person
(defn thaalis-for-its-address
  [its]
  ((dt/api :qu) '[:find ?thaali-num ?size ?thaali
                :in $ ?its
                :where
                [?person :person/its ?its]
                [?person :person/address ?add]
                [?thaali :thaali/address ?add]
                [?thaali :thaali/num ?thaali-num]
                [?thaali :thaali/size ?thaali-size]
                [?thaali-size :db/ident ?size]]
   its))

;; find all persons in Pune
(defn all-persons-in-pune
  []
  ((dt/api :qu) '[:find ?its
                :where
                [?person :person/its ?its]
                [?person :person/in-poona? true]]))

;; find all persons in Pune and receiving thaali barakat
(defn persons-receiving-thaali
  []
  ((dt/api :qu) '[:find ?its
                :where
                [?person :person/its ?its]
                [?person :person/in-poona? true]
                [?person :person/receives-thaali? true]]))

;; find all persons in Pune and not receiving thaali barakat
(defn persons-not-receiving-thaali
  []
  ((dt/api :qu) '[:find ?its
                :where
                [?person :person/its ?its]
                [?person :person/in-poona? true]
                [?person :person/receives-thaali? false]]))

(defn attr-missing?
  [db eid attr]
(attr (d/entity db eid)))

(defn pending-hubs-for-its-address
  [its]
  ((dt/api :qu) '[:find ?thaali-num ?size
                :in $ ?its
                :where
                [?p :person/its ?its]
                [?p :person/address ?add]
                [?all-p :person/address ?add]
                []]
   its))

;; a hub object is created when a new thaali is started



;; retrieve all pending hubs for a particular
()


;; retrieve thaalis that were active during a month



;; retrieve currently active thaalis

;; retrieve the duration during which a thaali was active

;; retrieve the start stop and duration activity of a thaali during a time span.

  (find-person-by-its 20341280)

;; create incharges for each thaali.

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; Create API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn upsert-en
  [m]
  ((dt/api :upsert-en) m))


;; (def val-address
;;   (val/validation-set
;;    (val/presence-of :address/area)
;;    (val/presence-of :address/sub-area)
;;    (val/presence-of :address/landmark)
;;    (val/presence-of :address/street)
;;    (val/presence-of :address/society)
;;    (val/presence-of :address/building)
;;    (val/presence-of :address/floor)
;;    (val/presence-of :address/flat-num)
;;    (val/presence-of :db/id)))

;; (def val-person
;;   (val/validation-set
;;    (val/presence-of :person/first-name)
;;    (val/presence-of :person/middle-name)
;;    (val/presence-of :person/last-name)
;;    (val/presence-of :person/its-jamaat)
;;    (val/presence-of :person/its)
;;    (val/presence-of :person/watan)
;;    (val/presence-of :db/id)))

;; (def val-address
;;   (val/validation-set
;;    (val/presence-of :address/area)
;;    (val/presence-of :db/id)))
;; (val/valid? val-address {:address/city "A" :address/area "c"})

;; (defn upsert-en
;;   [val-set]
;;   (fn [entity-map]
;;     (if (val/valid? val-set)
;;         ((dt/api :upsert-en) entity-map))))

(comment
  (find-person-by-its 20341280)

  (upsert-en person1)

  )
(def person1 {
   :person/first-name "Murtaza"
   :person/middle-name "Shabbir"
   :person/last-name "Rampurawala"
   :person/its 20341287
   :person/mobile 9923109052
   :person/email "abc@abc.com"}
)

(upsert-en person1)

;(def thaali-mappings
;  [:thaali/num
;  :common/hijri-year
;  :month :common/hijri-month
;  :mode :delivery/mode
;  :pickup :delivery.mode/pickup
;  :transport :delivery.mode/transport
;  :start-date :thaali/started-on
;  :stop-date :thaali/stopped-on
;  :size :thaali/size
;  :half :thaali.size/half
;  :full :thaali.size/full
;  ])
;
;(def hub-keys
;  [:hub/pledged
;   :hub/amount-received
;   :hub/received-on
;   :hub/received-by
;   :hub/due-date
;   :common/hijri-year
;   :common/hijri-month])
;
;(def person-keys
;  [:person/first-name
;   :person/middle-name
;   :person/last-name
;   :person/its
;   :person/mobile
;   :person/email
;   :person/watan])
;
;(def address-keys
;  [:address/address-string :address/area :address/sub-area])

(def sample
  {:thaali/size :thaali.size/full
   :thaali/num 6
   :address/sub-area "Kedari PP"
   :person/mobile 8605451052
   :delivery/mode :delivery.transporter/qaizar-bhai
   :person/first-name "Shabbir"
   :person/middle-name "M"
   :address/area "Wanowrie"
   :zilqaad 0
   :shawaal 0
   :person/watan "Sagwara"
   :person/last-name "fakhruddin Bawsi"
   :address/address-string "flat no.7 bldg no.B/4, pleasent park fatimanagar"})



;; init - by creating db, running the schema and connecting to it.

;; connect - connect to a an existing db

;; seed - create, connectm run schema and seed data

;; reset - delete db and seed

;;(dt/populate-data schema)
;(dt/populate-data data-path)

;; QUERIES

;; active thaalis

;; active thaalis for transporter

;; active thaalis transported by

;; active thaalis transported by and full / half

;; active thaalis transported by and full / half - route

;; a job to rollover thaalis every month or should they subscribe

;; total hub due for a residence for a month.

;; total pending hub for a residence

;; (def thaali-mappings
;;   {:num :thaali/num
;;   :year :common/hijri-year
;;   :month :common/hijri-month
;;   :mode :delivery/mode
;;   :pickup :delivery.mode/pickup
;;   :transport :delivery.mode/transport
;;   :start-date :thaali/started-on
;;   :stop-date :thaali/stopped-on
;;   :size :thaali/size
;;   :half :thaali.size/half
;;   :full :thaali.size/full
;;   })

;; (def hub-mappings
;;   {
;;   :pledged :hub/pledged
;;   :amt :hub/amount-received
;;   :rcvd-on :hub/received-on
;;   :rcvd-by :hub/received-by
;;   :due-date :hub/due-date
;;   :year :common/hijri-year
;;   :month :common/hijri-month})

;; (def person-mappings
;;   {
;;    :first-name :person/first-name
;;    :middle-name :person/middle-name
;;    :last-name :person/last-name
;;    :its :person/its
;;    :mobile :person/mobile
;;    :email :person/email
;;    :watan :person/watan})

;; (def address-mappings
;;   {:address-string :address/address-string
;;    :})


;; (def mappings [thaali-mappings hub-mappings address-mappings person-mappings])

;; (def f-set (comp set vals))

;; (defn get-val
;;   [m k]
;;   (or (m k) k))

;; (defn make-map
;;   [dm]
;;   (fn [m]
;;     (->>
;;        m
;;        (map (fn[[k v]] {(get-val dm k) (get-val dm v)}))
;;        (apply merge))))