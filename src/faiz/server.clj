(ns faiz.server
  (:require
   [clojure.string     :as str]
   [compojure.core     :as comp :refer (defroutes routes GET POST)]
   [compojure.route    :as route]
   [compojure.handler  :as comp-handler]
   [hiccup.core        :as hiccup]
   [org.httpkit.server :as http-kit-server]
   [clojure.core.match :as match :refer (match)]
   [clojure.core.async :as async :refer (<! <!! >! >!! put! chan go go-loop)]
   [taoensso.timbre    :as timbre]
   [taoensso.sente     :as sente]
   [cemerick.austin.repls :as austin]
   [ring.middleware.anti-forgery :as ring-anti-forgery]
   [faiz.data.thaali :as thaali]))

(defn- logf [fmt & xs] (println (apply format fmt xs)))

(let [{:keys [ch-recv send-fn ajax-post-fn ajax-get-or-ws-handshake-fn
              connected-uids]}
      (sente/make-channel-socket! {})]
  (def ring-ajax-post                ajax-post-fn)
  (def ring-ajax-get-or-ws-handshake ajax-get-or-ws-handshake-fn)
  (def ch-chsk                       ch-recv) ; ChannelSocket's receive channel
  (def chsk-send!                    send-fn) ; ChannelSocket's send API fn
  (def connected-uids                connected-uids) ; Watchable, read-only atom
 )

(defn login! [ring-request]
  (let [{:keys [session params]} ring-request
        {:keys [user-id]} params]
    (logf "Login request: %s" params)
    {:status 200 :session (assoc session :uid user-id)}))

;; (def old-dom
;;   [:h1 "Sente reference example"]
;;     [:p "An Ajax/WebSocket connection has been configured (random)."]
;;     [:hr]
;;     [:p [:strong "Step 1: "] "Open browser's JavaScript console."]
;;     [:p [:strong "Step 2: "] "Try: "
;;      [:button#btn1 {:type "button"} "chsk-send! (w/o reply)"]
;;      [:button#btn2 {:type "button"} "chsk-send! (with reply)"]]
;;     ;;
;;     [:p [:strong "Step 3: "] "See browser's console + nREPL's std-out." ]
;;     ;;
;;     [:hr]
;;     [:h2 "Login with a user-id"]
;;     [:p  "The server can use this id to send events to *you* specifically."]
;;     [:p [:input#input-login {:type :text :placeholder "User-id"}]
;;         [:button#btn-login {:type "button"} "Secure login!"]]
;;     [:script {:src (austin/browser-connected-repl-js)}]
;;     [:script {:src "main.js"}] ; Include our cljs target
;;     [:script {:src "react-0.10.min.js"}])

(def landing-pg
  (hiccup/html
   [:div {:id "main-app"}]
    [:script {:src (austin/browser-connected-repl-js)}]
        [:script {:src "out/goog/base.js"}]
    [:script {:src "main.js"}]
    [:script "goog.require('main.core')"]
    [:script "faiz.main.run()"]))

(defroutes my-ring-handler
    (->
     (routes
      (GET  "/"      req landing-pg)
      ;;
      (GET  "/chsk"  req (ring-ajax-get-or-ws-handshake req))
      (POST "/chsk"  req (ring-ajax-post                req))
      (POST "/login" req (login! req))
      ;;
      (route/resources "/") ; Static files, notably public/main.js (our cljs target)
      (route/not-found "<h1>Page not found</h1>"))

;;; Middleware

     ;; Sente adds a :csrf-token param to Ajax requests:
     (ring-anti-forgery/wrap-anti-forgery
      {:read-token (fn [req] (-> req :params :csrf-token))})

     compojure.handler/site))

  (defn run-http-server []
    (let [s   (http-kit-server/run-server (var my-ring-handler) {:port 0})
          uri (format "http://localhost:%s/" (:local-port (meta s)))]
      (logf "Http-kit server is running at `%s`" uri)
      (.browse (java.awt.Desktop/getDesktop)
               (java.net.URI. uri))))

  (defn- event-msg-handler
    [{:as ev-msg :keys [ring-req event ?reply-fn]} _]
    (let [session (:session ring-req)
          uid     (:uid session)
          [id data :as ev] event]
      (logf "Event: %s" ev)
      (match [id data]
             [:faiz/thaali-action [action d]] (thaali/thaali-action action data)
             ;; TODO: Match your events here, reply when appropriate <...>
             :else
             (do (logf "Unmatched event: %s" ev)
                 (when-not (:dummy-reply-fn? (meta ?reply-fn))
                   (?reply-fn {:umatched-event-as-echoed-from-from-server ev}))))))

(defonce server (atom nil))

(defonce chsk-router (atom nil))

(defn stop[]
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    ;; run-server retuens a fn which can be called for shutdown
    (@server)
    (reset! server nil)))

(defn restart []
  (stop)
  (reset! server (run-http-server))
  (reset! chsk-router (sente/start-chsk-router-loop! event-msg-handler ch-chsk)))
