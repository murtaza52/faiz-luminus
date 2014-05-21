(ns faiz.main
  (:require-macros
   [cljs.core.match.macros :refer (match)]
   [cljs.core.async.macros :as asyncm :refer (go go-loop)])
  (:require
   [clojure.string  :as str]
   [cljs.core.match]
   [cljs.core.async :as async  :refer (<! >! put! chan)]
   [taoensso.encore :as encore :refer (logf)]
   [taoensso.sente  :as sente  :refer (cb-success?)]))

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
