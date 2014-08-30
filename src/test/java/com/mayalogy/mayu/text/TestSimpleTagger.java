package com.mayalogy.mayu.text;

import java.util.List;

import com.mayalogy.mayu.text.Ontology;
import com.mayalogy.mayu.text.BasicTagger;
import com.mayalogy.mayu.text.FoundEntity;

import junit.framework.TestCase;

public class TestSimpleTagger extends TestCase {

    public void testTag() {
        
        Ontology.getInstance().addEntitiesFromFile(new String[]{
                "/data/ontology/ot-4.onto"
        });
        BasicTagger.getInstance().setOntology(Ontology.getInstance());
                
        assertContains(BasicTagger.getInstance().tag("i'm feeling lovely today"), "i'm feeling");
        
        Ontology.getInstance().addEntitiesFromFile(new String[]{
                "/data/ontology/ot-1.onto"
        });
        
        assertContains(BasicTagger.getInstance().tag("i do feel great in the summer."), "i do feel great");

    }
    
    private void assertContains(List<FoundEntity> es, String target) {
        boolean contains = false;
        for(FoundEntity fE:es) {
            if(fE.getName().equalsIgnoreCase(target))
                contains=true;
        }
        assertTrue(contains);
    }
}
