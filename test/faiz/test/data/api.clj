(ns faiz.test.data.api
  (:use [faiz.data.api]
        [midje.sweet]))

;; (facts "Calculates the total commitment"
;;        (fact "for everyone in a given financial year"
;;              (total-commitment :1435-1436) => 73000)
;;        (fact "for a given person in a fiancial year"
;;              (total-commitment :1435-1436 20341280) => 52000))

;; (facts "Returns the revenue distribution for the given financial year"
;;        (fact "for all individuals"
;;              (commitment-schedule :1435-1436) => #{[]})
;;        (fact "for a given individual"
;;              (commitment-schedule :1435-1436 20341280) => #{[]}))
