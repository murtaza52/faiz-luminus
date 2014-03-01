(ns faiz.routes.home
  (:use compojure.core)
  (:require [faiz.views.layout :as layout]
            [faiz.util :as util]
            [faiz.routes.controllers :as c]))

(defn home-page []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/persons/all" [] (c/persons-taking-thaalis)))


(c/persons-taking-thaalis)
