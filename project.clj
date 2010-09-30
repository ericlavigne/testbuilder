(defproject testbuilder "0.0.1"
  :description "Authoring tool for college examinations"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
		 [net.java/textile-j "2.2"]
		 [compojure "0.5.1"]
		 [ring/ring-servlet "0.3.0"]
		 [ring/ring-jetty-adapter "0.3.0"]
		 [ring/ring-devel "0.3.0"]
		 [hiccup "0.3.0"]]
  :dev-dependencies [[lein-run "1.0.0"]]
  :repositories {"java.net" "http://download.java.net/maven/2"})
