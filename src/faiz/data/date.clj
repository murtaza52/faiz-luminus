(ns faiz.date
  (:require [clj-time.core :as t]
            [clj-time.coerce :as c]))

(defn date-instance
  [& p]
  (-> (apply t/date-time p) c/to-date))

(date-instance 2014 5 1)


;;(def current-instance

;;(-> t/now c/to-long)

