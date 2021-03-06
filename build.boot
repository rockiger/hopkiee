#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.5.1"

(set-env!
  :project      'hopkiee
  :version      "0.1.0-SNAPSHOT"
  :dependencies '[[tailrecursion/boot.task   "2.2.4"]
                  [tailrecursion/hoplon      "5.10.24"]
                  [com.cemerick/clojurescript.test "0.3.3"]]
  :out-path     "resources/public"
  :src-paths    #{"src"})

;; Static resources (css, images, etc.):
(add-sync! (get-env :out-path) #{"assets"})

(require
  '[tailrecursion.hoplon.boot    :refer :all]
  '[tailrecursion.boot.task.ring :refer [dev-server]])

(deftask development
  "Build hopkiee for development."
  []
  (comp (watch) (hoplon {:pretty-print true :prerender false}) (dev-server)))

(deftask dev-debug
  "Build hopkiee for development with source maps."
  []
  (comp (watch) (hoplon {:pretty-print true
                         :prerender false
                         :source-map true}) (dev-server)))

(deftask production
  "Build hopkiee for production."
  []
  (hoplon {:optimizations :advanced}))
