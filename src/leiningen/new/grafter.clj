(ns leiningen.new.grafter
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "grafter"))

(defn grafter
  ""
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info
"            ___           __ _
           / __|_ _ __ _ / _| |_ ___ _ _
          | (_ | '_/ _` |  _|  _/ -_) '_|
           \\___|_| \\__,_|_|  \\__\\___|_|

      MACHINE TOOLS FOR LINKED DATA MANUFACTURE
                   grafter.org

Before you start building your grafter assembly line,
check your installation by running:

$ cd " name "

$ lein run ./data/example-data.csv example-output.ttl
")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/graph.clj" (render "graph.clj" data)]
             ["src/{{sanitized}}/pipeline.clj" (render "pipeline.clj" data)]
             ["src/{{sanitized}}/transform.clj" (render "transform.clj" data)]
             ["data/example-data.csv" (render "example-data.csv" data)]
             ["src/{{sanitized}}/prefix.clj" (render "prefix.clj" data)])))
