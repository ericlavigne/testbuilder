(ns testbuilder.core
  (:import (net.java.textilej.parser.builder HtmlDocumentBuilder))
  (:import (net.java.textilej.parser MarkupParser))
  (:import (net.java.textilej.parser.markup.textile TextileDialect))
  (:use compojure.core, ring.adapter.jetty)
  (:require [compojure.route :as route]))

; http://www.textism.com/tools/textile/index.php
(defn textile-to-html-fragment [textile]
  (let [out (java.io.StringWriter.)
	builder (HtmlDocumentBuilder. out)
	parser (doto (MarkupParser.)
		 (.setDialect (TextileDialect.))
		 (.setBuilder builder)
		 (.parse textile false))]
    (.toString out)))

(defroutes main-routes
  (GET "/" [] "<h1>Test Builder</h1>")
  (route/not-found "<h1>Page not found</h1>"))

; lein run testbuilder.core run-server
(defn run-server []
  (run-jetty main-routes {:port 8080}))
