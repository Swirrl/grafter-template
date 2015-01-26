(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: this part is for you!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [grafter "0.3.0"]
                 [org.slf4j/slf4j-jdk14 "1.7.5"]]

  :repl-options {:init (set! *print-length* 200)
                 :init-ns {{name}}.pipeline }

  :plugins [[lein-grafter "0.3.0"]])
