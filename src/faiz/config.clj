(ns faiz.config
  (:require [faiz.utils :as utils]))

(def conf-file "config/config.edn")

(def config #(utils/read-edn conf-file))
