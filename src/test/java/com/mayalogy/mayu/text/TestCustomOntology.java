package com.mayalogy.mayu.text;

import java.io.IOException;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mayalogy.mayu.text.Ontology;
import com.mayalogy.mayu.text.FoundEntity;

public class TestCustomOntology extends TestCase {
		
    private static Logger logger = LoggerFactory.getLogger(TestCustomOntology.class);
    
    public void testGetAvailableOntos() throws IOException {
    	for(String s:Ontology.getInstance().getOntologyFiles("data/ontology")) {
    		logger.info(s);
    	}
    	assertTrue(Ontology.getInstance().getOntologyFiles("data/ontology").length>3);
    }
    
    public void testSingleTypeVariant() {
        Ontology.getInstance().addEntitiesFromFile(new String[]{
                "ot-9.onto"
        });
        logger.info(Ontology.getInstance().search("formula 1").toReadableString());
    }
    
    public void testStemSearches() {
        Ontology.getInstance().clearEntities();
        Ontology.getInstance().addEntitiesFromFile(new String[]{
                "ot-3.onto"
        });
        FoundEntity te = Ontology.getInstance().search("viable difference");
        assertNotNull(te);
        
        Ontology.getInstance().clearEntities();
        Ontology.getInstance().setStemWordsOnSearch(false);
        Ontology.getInstance().addEntitiesFromFile(new String[]{
                "ot-3.onto"
        });
        te = Ontology.getInstance().search("viable difference");
        assertTrue(te==null);
        Ontology.getInstance().clearEntities();
    }
    
    public void testCaseInsensitivity() {
        Ontology.getInstance().clearEntities();
        Ontology.getInstance().addEntitiesFromFile(new String[]{
                "ot-5.onto"
        });
        FoundEntity te = Ontology.getInstance().search("CAPITAL_LETTERS");
        assertNotNull(te);
        te = Ontology.getInstance().search("capital_letters");
        assertNotNull(te);
        Ontology.getInstance().clearEntities();
    }
    
    public void testAddOntologies() {
        Ontology.getInstance().addEntitiesFromFile(new String[]{
                "ot-1.onto"
        });
        for(FoundEntity te:Ontology.getInstance().getAllEntities()) {
            logger.info(te.toReadableString());
        }
        FoundEntity te = Ontology.getInstance().search("I FEEL great");
        logger.info(te.toReadableString());
        assertTrue(te.getName().equals("i feel great"));
    }
}