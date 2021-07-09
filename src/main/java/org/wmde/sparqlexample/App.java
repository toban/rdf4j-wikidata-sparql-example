package org.wmde.sparqlexample;

import org.eclipse.rdf4j.query.resultio.sparqljson.SPARQLResultsJSONWriter;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import java.util.Collections;

/**
 * Wikidata RDF4J SPARQL example
 */
public class App 
{
    public static void main( String[] args )
    {
        String sparqlEndpoint = "https://query.wikidata.org/sparql";
        SPARQLRepository repo = new SPARQLRepository(sparqlEndpoint);

        String userAgent = "Wikidata RDF4J Java Example/0.1 (https://query.wikidata.org/)";
        repo.setAdditionalHttpHeaders( Collections.singletonMap("User-Agent", userAgent ) );

        String querySelect = "SELECT ?item ?itemLabel WHERE {\n" +
                "  ?item wdt:P31 wd:Q146.\n" +
                "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\". }\n" +
                "}";

        try{
            repo.getConnection().prepareTupleQuery(querySelect).evaluate(new SPARQLResultsJSONWriter(System.out));
        } catch ( Exception exception ) {
            exception.printStackTrace();
        }

    }
}
