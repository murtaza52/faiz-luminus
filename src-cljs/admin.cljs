(ns faiz.admin
   (:require [reagent.core :as reagent :refer [atom]]))

(defn main-app
  []
  [:div [:p "Hello World"]])

;; (ns faiz.admin
;;   (:require [om.core :as om :include-macros true]
;;             [om.dom :as dom :include-macros true]))

;; (defn widget [data owner] (dom/h1 "Faiz ul Mawaid il Burhaniyah"))

;; (defn init! []
;;   (om/root widget {}
;;            {:target (. js/document (getElementById "main-app"))}))
