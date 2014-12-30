(ns {{name}}.pipeline
    (:require
     [grafter.tabular :refer [defpipeline column-names columns rows all-columns derive-column
                              mapc swap drop-rows open-dataset open-datasets make-dataset
                              move-first-row-to-header _ graph-fn]]
     [grafter.rdf :refer [s]]
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

(defpipeline pipeline
  "An example pipeline that converts person data."
  [spreadsheet]
  (-> (open-dataset spreadsheet)
      (drop-rows 1)
      (make-dataset [:name :sex :age])
      (derive-column :person-uri [:name] base-id)
      (mapc {:age ->integer
             :sex {"f" (s "female")
                   "m" (s "male")}})
      make-graph))
