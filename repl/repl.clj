(ns repl
  (:require [clojure.tools.namespace.repl :refer [refresh]]
            [clojure.repl :refer [doc source dir]]
            [clojure.pprint :refer [pprint]]
            [faiz.data.api :as api]))



(comment

  ;(refresh)
(api/reset-db!)

(api/add-data! api/seed-data)

(api/init!)

@api/db-settings

)

(def sample {:transporter :kaizar-bhai, :watan "Sagwara", :sub-area "Kedari PP", :name "Shabbir M fakhruddin Bawsi", :area "Wanowrie", :size :full, :num 6, :zilqaad 0, :shawaal 0, :mode :transport, :address "flat no.7 bldg no.B/4, pleasent park fatimanagar", :mobile 8605451052})



(api/init-db!)

(api/add-data! api/seed-data)

;;(api/en :thaali)

;; api/search-clause

(def thaali-clause (merge search-clause '[?e :thaali/num ?v]))

(doc slurp)

;; create a new person

(def new-ebaali-clause 21)

(api/query thaali-clause 21)

;; use query set to directly query

;;


(def new-reg
  []
  ;; create new person
  ;; create new thaali
  ;; add transporter detail
  ;; create hub detail
  )

;; ui should pas down only semantic details

(api/cr-en new-e)

(def sample-thaali {:type :thaali :mode :pickup :num 27 :})

(api/cr sample-thaali)

;;


;; create a new address

;; create thaali




;; create a hub record
