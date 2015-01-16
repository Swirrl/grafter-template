(ns {{name}}.pipeline
    (:require
     [grafter.tabular :refer [defpipe defgraft column-names columns rows
                              all-columns derive-column mapc swap drop-rows
                              read-dataset read-datasets make-dataset
                              move-first-row-to-header _ graph-fn
                              test-dataset]]
     [grafter.rdf :refer [s]]
     [grafter.rdf.protocols :refer [->Quad]]
     [grafter.rdf.templater :refer [graph]]
     [grafter.rdf.ontologies.rdf :refer :all]
     [grafter.rdf.ontologies.foaf :refer :all]
     [{{name}}.prefix :refer [base-id base-graph base-vocab base-data]]
     [{{name}}.transform :refer [->integer]]))

;; Declare our graph template which will destructure each row and
;; convert it into an RDF graph.  This will be the final step in our
;; pipeline definition.
;;
;; Tutorial
;; http://grafter.org/tutorials/907_graph.html

(def make-graph
  (graph-fn [{:keys [name sex age person-uri gender]}]
            (graph (base-graph "example")
                   [person-uri
                    [rdf:a foaf:Person]
                    [foaf:gender sex]
                    [foaf:age age]
                    [foaf:name (s name)]])))

;; Pipeline modifies, for each row of the tabular file we are working
;; on, the columns, so we can access or add the exact data we need
;; for our templates.
;;
;; Tutorial
;; http://grafter.org/tutorials/906_pipeline.html

(defpipe convert-persons-data
  "Pipeline to convert tabular persons data into a different tabular format."
  [data-file]
  (-> (read-dataset data-file)
      (drop-rows 1)
      (make-dataset [:name :sex :age])
      (derive-column :person-uri [:name] base-id)
      (mapc {:age ->integer
             :sex {"f" (s "female")
                   "m" (s "male")}})))

(defgraft convert-persons-data-to-graph
  "Pipeline to convert the tabular persons data sheet into graph data."
  convert-persons-data make-graph)
