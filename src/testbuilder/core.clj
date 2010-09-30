(ns testbuilder.core
  (:import (net.java.textilej.parser.builder HtmlDocumentBuilder))
  (:import (net.java.textilej.parser MarkupParser))
  (:import (net.java.textilej.parser.markup.textile TextileDialect)))

; http://www.textism.com/tools/textile/index.php
(defn textile-to-html-fragment [textile]
  (let [out (java.io.StringWriter.)
	builder (HtmlDocumentBuilder. out)
	parser (doto (MarkupParser.)
		 (.setDialect (TextileDialect.))
		 (.setBuilder builder)
		 (.parse textile false))]
    (.toString out)))

