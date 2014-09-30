(defproject faiz "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [lib-noir "0.7.1"]
                 ;; [ring-server "0.3.0"]
                 ;;[selmer "0.4.3"]
                 [com.taoensso/timbre "2.6.2"]
                 [com.postspectacular/rotor "0.1.0"]
                 [com.taoensso/tower "1.7.1"]
                 [markdown-clj "0.9.33"]
                 ;;
                 [com.datomic/datomic-pro "0.9.4572"]
                 ;;
                 [prismatic/plumbing "0.1.1"]
                 [prismatic/schema "0.2.1"]
                 ;;
                 [org.clojure/tools.namespace "0.2.4"]
                 ;;
                 [org.clojure/core.async    "0.1.278.0-76b25b-alpha"]
                 ;;
                 [http-kit                  "2.1.18"]
                 ;;
                 [compojure                 "1.1.6"]  ; Or routing lib of your choice
                 [ring                      "1.2.1"]
                 [org.clojure/core.match    "0.2.1"]  ; Optional but quite handly
                 [com.taoensso.forks/ring-anti-forgery "0.3.1"]
                 ;;
                 [clj-time "0.6.0"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [local/camel-snake-kebab "1.1"]
                 ;;
                 [com.taoensso/sente "0.13.0"]
                 [slingshot "0.10.3"]
                 [dire "0.5.2"]]
  :resource-paths ["resources"]
  :repositories {"project" "file:repo"}
  :profiles {:production {:ring {:open-browser? false
                                 :stacktraces?  false
                                 :auto-reload?  false}}
             :dev {:dependencies [[ring-mock "0.1.5"]
                                  [http-kit "2.1.18"]
                                  [clj-http "0.9.1"]
                                  [midje "1.6.3"]]
                   :source-paths ["dev"]}}
  :source-paths ["src"]
  :min-lein-version "2.0.0"
  :jvm-opts ^:replace ["-Xmx1g" "-server"])
