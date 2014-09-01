(ns {{name}}.core
  (:require [grafter.tabular :refer :all]
            [grafter.rdf.protocols :as pr]
            [grafter.rdf.sesame :as ses]
            [grafter.rdf.validation :refer [has-blank? validate-triples]]
            [{{name}}.filter :refer [filter-triples]]
            [{{name}}.make-graph :refer [glasgow-life-facilities-template]]
            [{{name}}.pipeline :refer [pipeline]]))

(defonce my-repo (-> "./tmp/grafter-sesame-store-2" ses/native-store ses/repo))

(defn import-life-facilities
  [quads-seq destination]
  (let [now (java.util.Date.)
        quads (->> quads-seq
                   filter-triples
                   (validate-triples (complement has-blank?)))]

    (pr/add (ses/rdf-serializer destination) quads)))

;; To adapt this template to your tabular file, the first step would
;; be to create, in the prefixers.clj file, the basic transformations
;; and basic URIs you'll need. Then you can adapt the Pipeline
;; function in the pipeline.clj file and the template in the
;; make-graph.clj. You can then adapt the filter in filter.clj if
;; needed, and the import function above.
;; Finally, you just have to call your new functions in the -main
;; function just here and run on your terminal:
;; $ lein run path-of-your-file name-of-your-RDF-graph


(defn -main [& [path output]]
  (when-not (and path output)
    (println "Usage: lein run <input-file.csv> <output-file.(nt|rdf|n3|ttl)>")
    (System/exit 0))
  (-> (open-all-datasets path)
      first
      pipeline
      glasgow-life-facilities-template
      (import-life-facilities output))
  (println path "has been grafted using Grafter 0.2.0!"))
