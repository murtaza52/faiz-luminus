(ns faiz.main
  (:require-macros
   [cljs.core.match.macros :refer (match)]
   [cljs.core.async.macros :as asyncm :refer (go go-loop)])
  (:require
   [clojure.string  :as str]
   [cljs.core.match]
   [cljs.core.async :as async  :refer (<! >! put! chan)]
   [taoensso.encore :as encore :refer (logf)]
   [taoensso.sente  :as sente  :refer (cb-success?)]
   [clojure.browser.repl]
   [reagent.core :as reagent]
   [faiz.admin :refer [main-app]]))


(defn ^:export run []
  (reagent/render-component [main-app] (.-body js/document)))

;; clojure.browser.repl is needed for the austin repl to work

(let [{:keys [chsk ch-recv send-fn state]}
      (sente/make-channel-socket! "/chsk" ; Note the same URL as before
        {:type (if (>= (rand) 0.5) :ajax :auto)})]
  (def chsk       chsk)
  (def ch-chsk    ch-recv) ; ChannelSocket's receive channel
  (def chsk-send! send-fn) ; ChannelSocket's send API fn
  (def chsk-state state) ; Watchable, read-only atom
  )

(logf "ClojureScript appears to have loaded correctly.")

(defn- event-handler [[id data :as ev] _]
  (logf "Event: %s" ev)
  (match [id data]
    ;; TODO Match your events here <...>
    [:chsk/state {:first-open? true}]
    (logf "Channel socket successfully established!")
    [:chsk/state new-state] (logf "Chsk state change: %s" new-state)
    [:chsk/recv  payload]   (logf "Push event from server: %s" payload)
    :else (logf "Unmatched event: %s" ev)))

(defonce chsk-router
  (sente/start-chsk-router-loop! event-handler ch-chsk))

(.addEventListener (.getElementById js/document "btn1") "click"
  (fn [ev]
    (logf "Button 1 was clicked (won't receive any reply from server)")
    (chsk-send! [:faiz/thaali-action [:start 22]])))

(.addEventListener (.getElementById js/document "btn2") "click"
  (fn [ev]
    (logf "Button 2 was clicked (will receive reply from server)")
    (chsk-send! [:example/button2 {:had-a-callback? "indeed"}] 5000
      (fn [cb-reply] (logf "Callback reply: %s" cb-reply)))))

(.addEventListener (.getElementById js/document "btn-login") "click"
  (fn [ev]
    (let [user-id (.-value (.getElementById js/document "input-login"))]
      (if (str/blank? user-id)
        (js/alert "Please enter a user-id first")
        (do
          (logf "Logging in with user-id %s" user-id)

          ;;; Use any login procedure you'd like. Here we'll trigger an Ajax POST
          ;;; request that resets our server-side session. Then we ask our channel
          ;;; socket to reconnect, thereby picking up the new session.

          (encore/ajax-lite "/login" {:method :post
                                      :params
                                      {:user-id (str user-id)
                                       :csrf-token (:csrf-token @chsk-state)}}
            (fn [ajax-resp]
              (logf "Ajax login response: %s" ajax-resp)))

          (sente/chsk-reconnect! chsk))))))
