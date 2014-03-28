(ns faiz.init
  (:gen-class)
  (:require [faiz.data.api :as api]
            [clojure.tools.nrepl.server :as nrepl]
            [faiz.repl :as s]))

(defn init
  []
  (nrepl/start-server :port 7053)
  (s/start-server))

;; (defn -main
;;   "Application entry point"
;;   [& args]
;;   (init))
