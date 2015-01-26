# Grafter Leiningen Template

A Leiningen project template for [Grafter](http://www.grafter.org)
pipelines.

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

For information on how to use this plugin and what benefits it brings,
please consult the
[Grafter Getting Started Guide](http://grafter.org/getting-started)

The latest version of this plugin is:

[![Clojars Project](http://clojars.org/grafter/lein-template/latest-version.svg)](http://clojars.org/grafter/lein-template)

However you'll seldom need to know that, so long as you have have
leiningen [installed](http://leiningen.org/#install) as a
prerequisite you can simply run the command:

    $ lein new grafter my-grafter-project
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

If you want to run a bleeding edge snapshot version of the plugin and
Grafter you can run the following:

    $ lein new grafter my-grafter-project --snapshot

Grafter supports almost every RDF serialization and will infer the
serialisation format from the output files file extension.

Supported formats include:

- .n3 (N3)
- .nq (NQuads)
- .nt (NTriples)
- .rdf (RDF XML)
- .trig (Trig)
- .trix (Trix)
- .ttl (Turtle)

Once installed you can adapt the template to your own project needs.

## License

Copyright © 2014 Swirrl IT Ltd.

Distributed under the Eclipse Public License version 1.0, the same as Clojure.
