(ns leiningen.new.grafter-template
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "grafter-template"))

(defn grafter-template
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh project with Grafter's template.

You can go to http://www.grafter.org to find some informations about Grafter, some documentation, the API and some tutorials.

Grafter is still very young so any feedback is useful!

As a first step you can try our example: RDFization of the glasgow-life-facilities.csv you'll find in the data directory by running:
\"$ lein run \"./data/glasgow-life-facilities.csv\" \"glasgow-life-facilities.ttl\"\"...

Then you can adapt any of the steps: pipeline, make-graph, prefixers, filter and core, for your own tabular files")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/filter.clj" (render "filter.clj" data)]
             ["src/{{sanitized}}/make_graph.clj" (render "make_graph.clj" data)]
             ["src/{{sanitized}}/pipeline.clj" (render "pipeline.clj" data)]
             ["src/{{sanitized}}/prefixers.clj" (render "prefixers.clj" data)]
             ["data/glasgow-life-facilities.csv" (render "data/glasgow-life-facilities.csv")])))
