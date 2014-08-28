(ns {{name}}.pipeline
  (:require [grafter.tabular :refer [column-names derive-column mapc swap drop-rows _]]
            [grafter.tabular.common :refer [open-all-datasets make-dataset move-first-row-to-header]]
            [grafter.parse :refer [date-time]]
            [{{name}}.prefixers :refer :all]))

;; Pipeline modifies, for each row of the tabular file we are working
;; on, the columns, so we can access or add the exact data we need
;; for our templates.

;; Tutorial
;; http://grafter.org/tutorials/906_pipeline.html


(defn pipeline [dataset]
  (-> dataset
      (make-dataset ["facility-description" "facility-name" "monthly-attendance" "month" "year" "address" "town" "postcode" "website"])
      (drop-rows 1)
      (derive-column "facility-type" ["facility-description"] clean-type)
      (derive-column "name-slug" ["facility-name"] slugify)
      (mapc {"facility-description" uriify-facility
             "monthly-attendance" parse-attendance
             "month" convert-month
             "year" parse-year
             "address" address-line
             "town" city
             "postcode" post-code
             "website" url})
      (derive-column "ref-facility-uri" ["facility-type" "name-slug"] uriify-ref-facility)
      (derive-column "postcode-uri" ["postcode"] uriify-pcode)
      (swap "month" "year")
      (derive-column "date" ["year" "month"] date-time)
      (derive-column "prefix-date" ["date"] prefix-monthly-attendance)
      (derive-column "type-name" ["facility-type" "name-slug"] slug-combine)
      (derive-column "observation-uri" ["prefix-date" "type-name"] str)))
