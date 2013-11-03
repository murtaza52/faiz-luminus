;; (ns faiz.handler
;;   (:use compojure.core)
;;   (:use ring.util.json-response)
;;   (:use [faiz.utils :only [wrap-logger wrap-exception]])
;;   (:require [compojure.handler :as handler]
;;             [compojure.route :as route]
;;             [ring.util.response :as resp]
;;             [me.shenfeng.mustache :as mustache]
;;             [cemerick.friend :as friend]
;;             (cemerick.friend [workflows :as workflows]
;;                              [credentials :as creds]
;;                              [openid :as openid])))

;; ;;(mustache/deftemplate index (slurp "public/index-async.html"))

;; ;;(def index-data {:title "Invoize." :brand "Faiz" :links [{:url "#/students" :text "Students"} {:url "#/thaalis" :text "Thaalis"}]})

;; (def file-options {:root "yeoman/app"})

;; (defroutes app-routes
;;   (GET "/" [] (resp/redirect "/landing"))
;;   (GET "/landing" request (if (friend/identity request)
;;                             (resp/redirect "/home")
;;                             (resp/file-response "views/landing.html" file-options)))
;;   (GET "/home" [] (friend/authenticated (resp/file-response "views/home.html" file-options)))
;;   (friend/logout (ANY "/logout" request (resp/redirect "/")))
;;   (GET "/authmap" [] (json-response {:account-manager {:WD [1234, 1423], :Tyco [3211, 2345]}}))
;;   (route/files "/" file-options)
;;   (route/not-found "Not Found"))

;; (defn wrap-print-session [handler]
;;   (fn [req]
;;     (println req)
;;     (handler req)))

;; (def app-routes-with-auth
;;   (-> app-routes
;;       wrap-print-session
;;       (friend/authenticate
;;        {:workflows [(openid/workflow :openid-uri "/openid" :realm "http://localhost:3000" :credential-fn identity)]})))

;; (def app
;;   (-> app-routes-with-auth (wrap-logger true) wrap-exception handler/site))
