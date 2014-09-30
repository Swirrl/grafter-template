# grafter-template

A Leiningen template for [Grafter](http://www.grafter.org) pipelines.

This template installs a simple project structure suitable for
creating Grafter RDF transformations.

After building the template a minimal pipeline will be installed that
converts this source data:

    name,sex,age
    Alice,f,34
    Bob,m,63

Into this RDF:

    <http://my-domain.com/id/Alice> a <http://xmlns.com/foaf/0.1/Person> ;
        <http://xmlns.com/foaf/0.1/gender> "female" ;
        <http://xmlns.com/foaf/0.1/age> "34"^^<http://www.w3.org/2001/XMLSchema#int> ;
        <http://xmlns.com/foaf/0.1/name> "Alice" .

    <http://my-domain.com/id/Bob> a <http://xmlns.com/foaf/0.1/Person> ;
        <http://xmlns.com/foaf/0.1/gender> "male" ;
        <http://xmlns.com/foaf/0.1/age> "63"^^<http://www.w3.org/2001/XMLSchema#int> ;
        <http://xmlns.com/foaf/0.1/name> "Bob" .

## Usage

You will need to have leiningen
[installed](http://leiningen.org/#install) as a prerequisite.

Then simply run:

    $ lein new grafter-template my-grafter-project

            ___           __ _
           / __|_ _ __ _ / _| |_ ___ _ _
          | (_ | '_/ _` |  _|  _/ -_) '_|
           \___|_| \__,_|_|  \__\___|_|

      MACHINE TOOLS FOR LINKED DATA MANUFACTURE
                   grafter.org

     Before you start building your grafter assembly line,
     check your installation by running:

     $ cd my-grafter-project

     $ lein run ./data/example-data.csv example-output.ttl
     ./data/example-data.csv => output.ttl

Grafter supports almost every RDF serialization and will infer the
serialisation format from the output files file extension.

You can then adapt the template to your own project needs.

## License

Copyright © 2014 Swirrl IT Ltd.

Distributed under the Eclipse Public License version 1.0, the same as Clojure.
