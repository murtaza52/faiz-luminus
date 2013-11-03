(defproject faiz "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [lib-noir "0.7.1"]
                 [compojure "1.1.5"]
                 [ring-server "0.3.0"]
                 [selmer "0.4.3"]
                 [com.taoensso/timbre "2.6.2"]
                 [com.postspectacular/rotor "0.1.0"]
                 [com.taoensso/tower "1.7.1"]
                 [markdown-clj "0.9.33"]
                 [com.datomic/datomic-free "0.8.4143"]
                 [im.chit/ribol "0.3.2"]
                 [liberator "0.9.0"]
                 [core.typed "0.1.8"]
                 [prismatic/plumbing "0.1.1"]
                 [com.novemberain/validateur "1.2.0"]
                 [org.clojure/tools.namespace "0.2.4"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler sample.handler/war-handler
         :init    sample.handler/init
         :destroy sample.handler/destroy}
  :resource-paths ["resources"]
  :profiles {:production {:ring {:open-browser? false
                                 :stacktraces?  false
                                 :auto-reload?  false}}
             :dev {:dependencies [[ring-mock "0.1.5"]
                                  [ring/ring-devel "1.2.0"]]}}
  :min-lein-version "2.0.0")
