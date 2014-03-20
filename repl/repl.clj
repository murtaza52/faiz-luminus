(ns repl
  (:require [clojure.tools.namespace.repl :refer [refresh]]
            [clojure.repl :refer [doc source dir]]
            [clojure.pprint :refer [pprint]]
            [faiz.data.api :as api]
            [org.httpkit.client :as http]
            [faiz.routes.controllers :as c]))

(c/persons-taking-thaalis)


;;@(http/get "http://localhost:3000/persons/all")

;; (let [{:keys [status headers body error] :as resp} @(http/get "http://host.com/path")]
;;   (pprint body))




;; (def sample {:transporter :kaizar-bhai, :watan "Sagwara", :sub-area "Kedari PP", :name "Shabbir M fakhruddin Bawsi", :area "Wanowrie", :size :full, :num 6, :zilqaad 0, :shawaal 0, :mode :transport, :address "flat no.7 bldg no.B/4, pleasent park fatimanagar", :mobile 8605451052})


;; (def new-reg
;;   []
;;   ;; create new person
;;   ;; create new thaali
;;   ;; add transporter detail
;;   ;; create hub detail
;;   )

;; ;; ui should pas down only semantic details


;; ;;(api/cr sample-thaali)

;; ;;


;; ;; create a new address

;; ;; create thaali




;; ;; create a hub record
