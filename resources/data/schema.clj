[
 ;;Person

 {:db/id #db/id[:db.part/db]
  :db/ident :person/first-name
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/fulltext true
  :db/doc "A person's first name"
  :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
  :db/ident :person/middle-name
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/fulltext true
  :db/doc "A person's middle name"
  :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
  :db/ident :person/last-name
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/fulltext true
  :db/doc "A person's last name"
  :db.install/_attribute :db.part/db}

 {:db/id #db/id[:db.part/db]
  :db/ident :person/its-jamaat
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "Jamaat in which the ITS ID is registered"
  :db.install/_attribute :db.part/db}

 {:db/id #db/id[:db.part/db]
  :db/ident :person/watan
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "Watan"
  :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
  :db/ident :person/its
  :db/valueType :db.type/long
  :db/cardinality :db.cardinality/one
  :db/unique :db.unique/identity
  :db/doc "ITS (Ejamaat) ID"
  :db.install/_attribute :db.part/db}

 {:db/id #db/id[:db.part/db]
  :db/ident :person/sabil
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "Sabil Number"
  :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
  :db/ident :person/openid-token
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "The id / token returned by the Open ID Provider"
  :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :person/address
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "Address of the person"
 :db.install/_attribute :db.part/db}

 {:db/id #db/id[:db.part/db]
 :db/ident :person/mobile
 :db/valueType :db.type/long
 :db/cardinality :db.cardinality/one
 :db/doc "Mobile Number"
 :db.install/_attribute :db.part/db}

 {:db/id #db/id[:db.part/db]
 :db/ident :person/email
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "Email Address"
 :db.install/_attribute :db.part/db}

;;Address

 {:db/id #db/id[:db.part/db]
 :db/ident :adress/address-string
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "Address of the person in string format (temp for seed data)"
 :db.install/_attribute :db.part/db}

 {:db/id #db/id[:db.part/db]
  :db/ident :address/city
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "City of Residence"
  :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
  :db/ident :address/area
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "Area of Residence"
  :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
  :db/ident :address/sub-area
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "Area of Residence"
  :db.install/_attribute :db.part/db}

; make area an enum

{:db/id #db/id[:db.part/db]
  :db/ident :address/society
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "Society Name"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
  :db/ident :address/building
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "Building Name / Number"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
  :db/ident :address/floor
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "Floor Number"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
  :db/ident :address/flat-num
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "Flat Number"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
  :db/ident :address/landmark
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db/doc "Landmark near the Building"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
  :db/ident :address/street
  :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
  :db/doc "Street Name"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :address/primary-incharge
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "Primary Person incharge for the thaalis in the residence."
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :address/secondary-incharge
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "Secondary Person incharge for the thaalis in the residence."
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :address/thaali-details
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/many
 :db/doc "A vector of thaali details for each month for each thaali."
 :db.install/_attribute :db.part/db}

;;education

{:db/id #db/id[:db.part/db]
 :db/ident :edu/start-year
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "First Year of Education"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :edu/end-year
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "Projected last Year of education"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :edu/field-of-study
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "Field of Study"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :edu/degree
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "Degree being Persued"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :edu/college
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "Degree being Persued"
 :db.install/_attribute :db.part/db}
;; [year, month, single, double, delivered, pick-up, delivered-by, started-on [], stoped-on [], skip - [[1 12/13/2013]]]

;;thaali-details

{:db/id #db/id[:db.part/db]
 :db/ident :thaali/size
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "Size of thaali"
 :db.install/_attribute :db.part/db}

;; thaali/size enum values
[:db/add #db/id[:db.part/user] :db/ident :thaali.size/half]
[:db/add #db/id[:db.part/user] :db/ident :thaali.size/full]

{:db/id #db/id[:db.part/db]
 :db/ident :thaali/num
 :db/valueType :db.type/long
 :db/cardinality :db.cardinality/one
 :db/unique :db.unique/identity
 :db/doc "Thaali Number"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :delivery/mode
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "The mode by which thaali is delivered."
 :db.install/_attribute :db.part/db}

;; delivery-mode enum values
[:db/add #db/id[:db.part/user] :db/ident :delivery.mode/pickup]
[:db/add #db/id[:db.part/user] :db/ident :delivery.mode/transport]

{:db/id #db/id[:db.part/db]
 :db/ident :delivery/transporter
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "Delivery service provided by (transporter)."
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :delivery/route
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "Delivery route of the transporter."
 :db.install/_attribute :db.part/db}

;; delivery-route enum values
[:db/add #db/id[:db.part/user] :db/ident :delivery.route/salunke-vihar]
[:db/add #db/id[:db.part/user] :db/ident :delivery.route/nibm]
[:db/add #db/id[:db.part/user] :db/ident :delivery.route/mithanagar]
[:db/add #db/id[:db.part/user] :db/ident :delivery.route/camp]

;; transporters
[:db/add #db/id[:db.part/user] :db/ident :delivery.transporter/hunaid-bhai]
[:db/add #db/id[:db.part/user] :db/ident :delivery.transporter/saifee-bhai]
[:db/add #db/id[:db.part/user] :db/ident :delivery.transporter/mohammed-bhai]

{:db/id #db/id[:db.part/db]
 :db/ident :thaali/remarks
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "Any relavent remarks"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :thaali/skip-dates
 :db/valueType :db.type/boolean
 :db/cardinality :db.cardinality/many
 :db/doc "Dates on which it was informed that thaali is not to be prepared."
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :thaali/not-picked-dates
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/many
 :db/doc "Dates on which thaali was not picked up."
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :thaali/started-on
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "Date thaali was started in the current month"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :thaali/stopped-on
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "Date thaali was stopped in the current month. This is a long term stop. If for a few days, then enter in skip-dates."
 :db.install/_attribute :db.part/db}

;; Common Elements

{:db/id #db/id[:db.part/db]
 :db/ident :common/tags
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/many
 :db/doc "Tags that can be attached to a an entity"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :common/roles
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/many
 :db/doc "Roles that can be assigned to a person."
 :db.install/_attribute :db.part/db}

[:db/add #db/id[:db.part/user] :db/ident :common.roles/admin]
[:db/add #db/id[:db.part/user] :db/ident :common.roles/transporter]
[:db/add #db/id[:db.part/user] :db/ident :common.roles/khidmatguzar]
[:db/add #db/id[:db.part/user] :db/ident :common.roles/caterer]

{:db/id #db/id[:db.part/db]
 :db/ident :common/hijri-year
 :db/valueType :db.type/long
 :db/cardinality :db.cardinality/one
 :db/doc "Hijri Year"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :common/hijri-month
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "Hijri Month"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :common/gregorian-year
 :db/valueType :db.type/long
 :db/cardinality :db.cardinality/one
 :db/doc "Year"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :common/gregorian-month
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "Month"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :common/entity-type
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/one
 :db/doc "Entity Type"
 :db.install/_attribute :db.part/db}

[:db/add #db/id[:db.part/user] :db/ident :common.entity-type/person]
[:db/add #db/id[:db.part/user] :db/ident :common.entity-type/address]
[:db/add #db/id[:db.part/user] :db/ident :common.entity-type/vendor]
[:db/add #db/id[:db.part/user] :db/ident :common.entity-type/thaali]
[:db/add #db/id[:db.part/user] :db/ident :common.entity-type/hub]

;; Hub

 {:db/id #db/id[:db.part/db]
 :db/ident :hub/hub-details
 :db/valueType :db.type/ref
 :db/cardinality :db.cardinality/many
 :db/doc "Array of hub details for each address."
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :hub/pledged
 :db/valueType :db.type/long
 :db/cardinality :db.cardinality/one
 :db/doc "Hub Pledged"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :hub/amount-received
 :db/valueType :db.type/long
 :db/cardinality :db.cardinality/many
 :db/doc "Hub Amount Received"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :hub/received-on
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/many
 :db/doc "Hub Amount Received On"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :hub/received-by
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/many
 :db/doc "Hub Amount Received"
 :db.install/_attribute :db.part/db}

{:db/id #db/id[:db.part/db]
 :db/ident :hub/due-date
 :db/valueType :db.type/string
 :db/cardinality :db.cardinality/one
 :db/doc "Hub Due Date"
 :db.install/_attribute :db.part/db}

]
