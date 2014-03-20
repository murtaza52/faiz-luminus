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
                 [com.datomic/datomic-pro "0.9.4572"]
                 [prismatic/plumbing "0.1.1"]
                 [prismatic/schema "0.2.1"]
                 [org.clojure/tools.namespace "0.2.4"]
                 [org.clojure/clojurescript "0.0-2030"]
                 [prismatic/dommy "0.1.2"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [clj-time "0.6.0"]]
  :plugins [[lein-ring "0.8.7"]
            [lein-cljsbuild "1.0.0"]]
  ;;:hooks [leiningen.cljsbuild]
  :ring {:handler faiz.handler/war-handler
         :init    faiz.handler/init
         :destroy faiz.handler/destroy}
  :resource-paths ["resources"]
  :profiles {:production {:ring {:open-browser? false
                                 :stacktraces?  false
                                 :auto-reload?  false}}
             :dev {:dependencies [[ring-mock "0.1.5"]
                                  [http-kit "2.1.18"]
                                  [clj-http "0.9.1"]]}}
  :cljsbuild
  {:builds [{:source-paths ["src-cljs"]
             :compiler {:output-to "resources/public/js/site.js"
                        :optimizations :whitespace
                        :pretty-print true
                        :source-map "site.js.map"}}]}
  :min-lein-version "2.0.0")
