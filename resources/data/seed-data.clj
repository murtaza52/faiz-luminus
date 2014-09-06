[

;; address

 {:db/id #db/id[:db.part/user -1000005]
  :address/area "Salunke Vihaar"
  :address/street "NIBM"
  :address/landmark "ABC Farms"
  :address/society "GV Gardens"
  :address/building "A5"
  :address/floor "4"
  :address/flat-num "206"}

  {:db/id #db/id[:db.part/user -1000511]
  :address/area "Salunke Vihaar"
  :address/street "NIBM"
  :address/landmark "ABC Farms"
  :address/society "GV Gardens"
  :address/building "A5"
  :address/floor "4"
  :address/flat-num "206"}

;; person

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
   :hub-commitment/term-type :hub-commitment.term-type/yearly}

  {:db/id #db/id[:db.part/user -1000010]
   :person/first-name "Batul"
   :person/middle-name "M Murtaza"
   :person/last-name "Rampurawala"
   :person/its 20359401
   :person/mobile 9923589052
   :person/email "abc@abc.com"
   :person/in-poona? true
   :person/receives-thaali? true}

  {:db/id #db/id[:db.part/user -1000011]
   :person/first-name "Tasneem"
   :person/middle-name "M Murtaza"
   :person/last-name "Kandivali"
   :person/its 20359402
   :person/mobile 9923589055
   :person/email "abc@abc.com"
   :person/in-poona? true
   :person/receives-thaali? true
   :hub-commitment/term-type :hub-commitment.term-type/monthly}

 ;; Thaalis

 {:db/id #db/id[:db.part/user -1000002]
  :thaali/size :thaali.size/half
  :thaali/num 21
  :thaali/active? true
  :delivery/mode :delivery.mode/pickup
  :thaali/persons [#db/id[:db.part/user -1000009]]
  :thaali/address #db/id[:db.part/user -1000005]}

 {:db/id #db/id[:db.part/user -1000003]
  :thaali/size :thaali.size/full
  :thaali/num 22
  :thaali/active? true
  :delivery/mode :delivery.mode/transport
  :delivery/transporter :delivery.transporter/hunaid-bhai
  :thaali/persons [#db/id[:db.part/user -1000011]]
  :thaali/address #db/id[:db.part/user -1000511]}

 ;; Thaali Events

 {:db/id #db/id[:db.part/user -1000061]
  :thaali-event/not-picked-on #inst "2014-02-13T00:00:00.000-00:00"
  :thaali-event/thaali-num 22}

 ;; Define a group

 {:db/id #db/id[:db.part/user -1000020]
  :group/name "Saifee"
  :group/addresses [#db/id[:db.part/user -1000005]]
  :group/musaid #db/id[:db.part/user -1000009]
  :group/coordinator #db/id[:db.part/user -1000010]}

;; Hub commitment

 {:db/id #db/id[:db.part/user -1000040]
  :hub-commitment/term :1435-1436
  :hub-commitment/person #db/id[:db.part/user -1000009]
  :hub-commitment/amount 52000}

  ;; Hub commitment for a monthly term
 {:db/id #db/id[:db.part/user -1000071]
  :hub-commitment/term :1435-shawaal
  :hub-commitment/person #db/id[:db.part/user -1000011]
  :hub-commitment/amount 3300}

 ;; Hub schedules for a person
 {:db/id #db/id[:db.part/user -1000050]
  :hub-schedule/due-date #inst "2014-03-01T00:00:00.000-00:00"
  :hub-schedule/amount 2000
  :hub-schedule/commitment #db/id[:db.part/user -1000040]}

 {:db/id #db/id[:db.part/user -1000051]
  :hub-schedule/due-date #inst "2014-04-01T00:00:00.000-00:00"
  :hub-schedule/amount 27000
  :hub-schedule/commitment #db/id[:db.part/user -1000040]}

 {:db/id #db/id[:db.part/user -1000052]
  :hub-schedule/due-date #inst "2014-05-01T00:00:00.000-00:00"
  :hub-schedule/amount 20000
  :hub-schedule/commitment #db/id[:db.part/user -1000040]}

 ;;;;;;;;; Hub Received ;;;;;;;;;;;;;;;

 ;; Monthly term
 {:db/id #db/id[:db.part/user -1000035]
  :hub-received/amount 1650
  :hub-received/commitment #db/id[:db.part/user -1000071]}

 ;; Yearly term
  {:db/id #db/id[:db.part/user -1000036]
  :hub-received/amount 7000
  :hub-received/commitment #db/id[:db.part/user -1000040]}

  {:db/id #db/id[:db.part/user -1000037]
  :hub-received/amount 7000
  :hub-received/commitment #db/id[:db.part/user -1000040]}


  ;; try making it unique by giving a unique id by timestamp
]
