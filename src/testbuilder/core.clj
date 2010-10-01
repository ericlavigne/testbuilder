(ns testbuilder.core
  (:import (net.java.textilej.parser.builder HtmlDocumentBuilder))
  (:import (net.java.textilej.parser MarkupParser))
  (:import (net.java.textilej.parser.markup.textile TextileDialect))
  (:use compojure.core, ring.adapter.jetty, hiccup.core)
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

(defn index-view [code] 
    (html [:form {:action "/" :method "POST" } 
        [:textarea {:name "code" :rows "20" :cols "70"} code] 
	[:br] 
	[:input {:type "Submit" :value "Convert to HTML" }]
	[:div (textile-to-html-fragment code)]]))


(defroutes main-routes
  (GET "/" [] (index-view ""))
  (POST "/" {params :params} (index-view (params "code")))
  (route/not-found "<h1>Page not found</h1>"))

; lein run testbuilder.core run-server
(defn run-server []
  (run-jetty main-routes {:port 8080}))
