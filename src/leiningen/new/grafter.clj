(ns leiningen.new.grafter
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "grafter"))

(defn grafter
  ""
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info (str
                "            ___           __ _
           / __|_ _ __ _ / _| |_ ___ _ _
          | (_ | '_/ _` |  _|  _/ -_) '_|
           \\___|_| \\__,_|_|  \\__\\___|_|

      MACHINE TOOLS FOR LINKED DATA MANUFACTURE
                   grafter.org

You can list pipelines defined in this project by running:

$ lein grafter list

For usage information on the grafter plugin run:

$ lein help grafter

To run the example pipeline defined in this project run:

$ cd " name "

$ lein grafter run " name ".pipeline/convert-persons-data ./data/example-data.csv example-output.ttl

"))
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["src/{{sanitized}}/pipeline.clj" (render "pipeline.clj" data)]
             ["src/{{sanitized}}/transform.clj" (render "transform.clj" data)]
             ["data/example-data.csv" (render "example-data.csv" data)]
             ["src/{{sanitized}}/prefix.clj" (render "prefix.clj" data)])))
