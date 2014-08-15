package com.mayalogy.mayu.text;

import java.util.ArrayList;
import java.util.List;

import com.mayalogy.mayu.core.WordGramExtractor;

public class BasicTagger {

	private static BasicTagger instance = null;
	private CoreOntology ontology = null;

	public static BasicTagger getInstance() {
		if(instance==null) {
			instance = new BasicTagger();
		}
		return instance;
	}

	private BasicTagger() {
		
		/**
		 * Set default Ontology.
		 */
		setOntology(Ontology.getInstance());
	}
	
	public void setOntology(CoreOntology coreOntology) {
		this.ontology=coreOntology;
	}

	public void tag(TaggedText s) {
		if(ontology==null) throw new MayuTextException("No CoreOntology has been added.");

		s.setEntities(tag(s.getText()));
	}

	public List<FoundEntity> tag(String text) {
		if(ontology==null) throw new MayuTextException("No CoreOntology has been added.");

		return tag(text, 6);
	}

	private List<FoundEntity> tag(String text, int maxWordTokens) {        
		//Order matters, longer phrase matches take precedence.
		List<FoundEntity> entities = new ArrayList<FoundEntity>();
		if(text!=null) {
			/**
			 * Ensure stop words are retained for single word grams so "I" is present.
			 */
			for(int i=maxWordTokens; i>1; i--) {
				for(String orig:WordGramExtractor.getGrams(i, text)) {
					addFoundEntities(orig, entities);
				}
			}
			/**
			 * Ensure stop words are retained for single grams so "I" is present.
			 */
			for(String orig:WordGramExtractor.get1Gram(text, true)) {
				addFoundEntities(orig, entities);
			}
		}
		return entities;
	}

	private void addFoundEntities(String orig, List<FoundEntity> entities) {
		if(orig.length()>1 || orig.equalsIgnoreCase("i")) { //allow personal pronoun through for tagging
			FoundEntity foundEntity = ontology.search(orig);
			if(foundEntity!=null)
				entities.add(foundEntity);
		}
	}
}
