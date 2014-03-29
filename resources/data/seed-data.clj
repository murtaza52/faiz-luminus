[

 ;;thaali

 ;; treat thaalis as a continum.
 ;; retrieve a thaalis history to see its start and stop dates.
 ;; the history is retrieved since its assignment to its last address.
 ;; keep address relation in thaali, to see the assignment history ? Or retrieve thaali history
 ;; from the date of the
 ;; thaalis at a particular time can be retrieved by querying against the DB at that point of time.



;;  {:db/id #db/id[:db.part/user -1000001]
;;   :thaali/size :thaali.size/half
;;   :thaali/num 21
;;   :common/hijri-year 1434
;;   :common/hijri-month "Shawaal"
;;   :thaali/started-on "12-04-12"
;;   :thaali/stopped-on "12-05-12"
;;   :delivery/mode :delivery.mode/transport
;;   :common/entity-type :common.entity-type/thaali}

;; ;;transporter
;;   {:db/id #db/id[:db.part/user -1000004]
;;   :common/entity-type :common.entity-type/person
;;   :person/first-name "Juzer"
;;   :common/roles :common.roles/transporter}

;;    {:db/id #db/id[:db.part/user -1000005]
;;   :common/entity-type :common.entity-type/person
;;   :person/first-name "Murtaza"
;;   :person/last-name "Gadiwala"
;;   :common/roles :common.roles/transporter}

;;    {:db/id #db/id[:db.part/user -1000006]
;;   :common/entity-type :common.entity-type/person
;;   :person/first-name "Qaizar"
;;   :common/roles :common.roles/transporter}


 ;; create a hub object for every person every month, every person done hub niyat of how much he will contribute.


;;;;person
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

 {:db/id #db/id[:db.part/user -1000009]
   :common/entity-type :common.entity-type/person
   :person/first-name "Murtaza"
   :person/middle-name "Shabbir"
   :person/last-name "Rampurawala"
   :person/its 20341280
   :person/mobile 9923109052
   :person/email "abc@abc.com"
   :person/in-poona? true
   :person/receives-thaali? true
   :person/address #db/id[:db.part/user -1000005]}

  {:db/id #db/id[:db.part/user -1000010]
   :person/first-name "Batul"
   :person/middle-name "M Murtaza"
   :person/last-name "Rampurawala"
   :person/its 20359401
   :person/mobile 9923589052
   :person/email "abc@abc.com"
   :person/in-poona? true
   :person/receives-thaali? false
   :person/address #db/id[:db.part/user -1000005]}

 {:db/id #db/id[:db.part/user -1000011]
   :person/first-name "Batul"
   :person/middle-name "M Murtaza"
   :person/last-name "Rampurawala"
   :person/its 20359402
   :person/mobile 9923589052
   :person/email "abc@abc.com"
   :person/in-poona? true
   :person/receives-thaali? false
   :person/address #db/id[:db.part/user -1000005]}

 {:db/id #db/id[:db.part/user -1000005]
  :address/area "Salunke Vihaar"
  :address/street "NIBM"
  :address/landmark "ABC Farms"
  :address/society "GV Gardens"
  :address/building "A5"
  :address/floor "4"
  :address/flat-num "206"}

 ;; Define a group
 {:db/id #db/id[:db.part/user -1000020]
  :group/name "Saifee"
  :group/addresses [#db/id[:db.part/user -1000005]]
  :group/musaid #db/id[:db.part/user -1000009]
  :group/coordinator #db/id[:db.part/user -1000010]}


;;  {:db/id #db/id[:db.part/user -1000030]
;;   :financial-year/hijri-year :financial-year.year/1435-1436
;;   :financial-year/start-date #inst "2014-02-01T00:00:00.000-00:00"
;;   :financial-year/end-date #inst "2015-02-01T00:00:00.000-00:00"}

 ;; Hub schedules for a person
 {:db/id #db/id[:db.part/user -1000050]
  :hub-schedule/due-date #inst "2014-03-01T00:00:00.000-00:00"
  :hub-schedule/amount 2000
  :hub-commitment/id #db/id[:db.part/user -1000040]}

 {:db/id #db/id[:db.part/user -1000051]
  :hub-schedule/due-date #inst "2014-04-01T00:00:00.000-00:00"
  :hub-schedule/amount 27000
  :hub-commitment/id #db/id[:db.part/user -1000040]}

 {:db/id #db/id[:db.part/user -1000052]
  :hub-schedule/due-date #inst "2014-05-01T00:00:00.000-00:00"
  :hub-schedule/amount 20000
  :hub-commitment/id #db/id[:db.part/user -1000040]}

  {:db/id #db/id[:db.part/user -1000053]
  :hub-schedule/due-date #inst "2014-04-01T00:00:00.000-00:00"
  :hub-schedule/amount 10000
   :hub-commitment/id #db/id[:db.part/user -1000041]}

 {:db/id #db/id[:db.part/user -1000054]
  :hub-schedule/due-date #inst "2014-05-01T00:00:00.000-00:00"
  :hub-schedule/amount 11000
  :hub-commitment/id #db/id[:db.part/user -1000041]}

 ;; Hub commitment including the schedules.
 {:db/id #db/id[:db.part/user -1000040]
  :hub-commitment/financial-year :1435-1436
  :hub-commitment/person #db/id[:db.part/user -1000009]
  :hub-commitment/amount 52000}

  {:db/id #db/id[:db.part/user -1000041]
  :hub-commitment/financial-year :1435-1436
  :hub-commitment/person #db/id[:db.part/user -1000011]
  :hub-commitment/amount 21000}

;;  {:db/id #db/id[:db.part/user -1000002]
;;   :thaali/size :thaali.size/half
;;   :thaali/num 21
;;   :common/hijri-year 1434
;;   :common/hijri-month "Zilqaad"
;;   :delivery/mode :delivery.mode/pickup
;;   :thaali/started-on "01-04-13"
;;   :thaali/stopped-on "01-05-13"
;;   :thaali/address #db/id[:db.part/user -1000005]}

;;  {:db/id #db/id[:db.part/user -1000003]
;;   :thaali/size :thaali.size/full
;;   :thaali/num 22
;;   :common/hijri-year 1434
;;   :common/hijri-month "Zilqaad"
;;   :delivery/mode :delivery.mode/transport
;;   :thaali/started-on "01-04-13"
;;   :thaali/stopped-on "10-04-13"
;;   :thaali/address #db/id[:db.part/user -1000005]}

   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;   {:db/id #db/id[:db.part/user -1000031]
;;   :hub/person #db/id[:db.part/user -1000009]
;;   :hub/pledged 1650
;;   :common/hijri-year 1434
;;   :common/hijri-month "Shawaal"
;;   :hub/due-date "21/1/2013"}

;;   {:db/id #db/id[:db.part/user -1000032]
;;   :hub/person #db/id[:db.part/user -1000010]
;;   :hub/pledged 1650
;;   :common/hijri-year 1434
;;   :common/hijri-month "Shawaal"
;;   :hub/due-date "21/1/2013"}

;;   {:db/id #db/id[:db.part/user -1000033]
;;   :hub/person #db/id[:db.part/user -1000009]
;;   :hub/pledged 1650
;;   :common/hijri-year 1434
;;   :common/hijri-month "Zilqaad"
;;   :hub/due-date "21/1/2013"}

;;   {:db/id #db/id[:db.part/user -1000034]
;;   :hub/person #db/id[:db.part/user -1000010]
;;   :hub/pledged 1650
;;   :common/hijri-year 1434
;;   :common/hijri-month "Zilqaad"
;;   :hub/due-date "21/1/2013"}

;;  {:db/id #db/id[:db.part/user -1000035]
;;   :hub/amount-received 1650
;;   :hub/received-on "12/1/2013"
;;   :hub/received-by "Sadiq Bhai"
;;   :hub/person #db/id[:db.part/user -1000009]
;;   :hub/for #db/id[:db.part/user -1000031]}

;;   {:db/id #db/id[:db.part/user -1000036]
;;   :hub/amount-received 1650
;;   :hub/received-on "12/1/2013"
;;   :hub/received-by "Sadiq Bhai"
;;   :hub/person #db/id[:db.part/user -1000010]
;;   :hub/for #db/id[:db.part/user -1000032]}


;;  :address/thaali-details [#db/id[:db.part/user -1000001]
;;                            #db/id[:db.part/user -1000002]
;;                            #db/id[:db.part/user -1000003]]
;;   :delivery/transporter [:db.part/user -1000004]
;;   :delivery/route :delivery.route/salunke-vihar
;;   :hub/hub-details [#db/id[:db.part/user -1000006]
;;                     #db/id[:db.part/user -1000007]]
;;   :address/primary-incharge #db/id[:db.part/user -1000009]



])

