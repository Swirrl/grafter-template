(ns {{name}}.core
  (:require [grafter.tabular :refer :all]
            [grafter.rdf :refer :all]
            [grafter.rdf.io :as io]
            [{{name}}.graph :refer [make-graph]]
            [{{name}}.pipeline :refer [pipeline]])
  (:gen-class))

(defn import-data
  [quads-seq destination]
  (let [quads (->> quads-seq
                   ;;(filter remove-invalid-triples)
                   )]

    (add (io/rdf-serializer destination) quads)))


(defn apply-pipeline [path]
  (-> (open-all-datasets path)
      first
      pipeline))

(defpipeline apply-complete-transformation [path]
  (-> (apply-pipeline path)
      make-graph))

(defn -main [& [path output]]
  (when-not (and path output)
    (println "Usage: lein run <input-file.csv> <output-file.(nt|rdf|n3|ttl)>")
    (System/exit 0))

  (-> (apply-complete-transformation path)
      (import-data output))

  (println path "=>" output))
