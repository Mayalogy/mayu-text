package com.mayalogy.mayu.text;

import com.mayalogy.mayu.text.Ontology;
import com.mayalogy.mayu.text.SentencePhraseSubstitutor;
import com.mayalogy.mayu.text.FoundEntity;

import junit.framework.TestCase;

public class TestSentencePhraseSubstitutor extends TestCase {

	private void prepOnto() {
		FoundEntity te = new FoundEntity("purple worms");
		te.addPath("popsicles");
		Ontology.getInstance().addEntity(te);
	}
	
	public void testSubstituteRestore() {
		prepOnto();
		String t = "purple worms";
		assertTrue(t.equals(SentencePhraseSubstitutor.restoreSubstitute(SentencePhraseSubstitutor.generateSubstitute(t))));
	}
	
	public void testSubstitute() {
		prepOnto();
		assertMod("I happily eat grapes", "I eat grapes");
		assertMod("I take labradors.", null);
		assertMod("\"I eat purple worms on Wednesdays\"", "I eat " + SentencePhraseSubstitutor.SUBSTITUTION_PREFIX+"purple" 
		+ SentencePhraseSubstitutor.SUBSTITUTION_WHITESPACE 
				+ "worms on Wednesdays");
	}

	public void testRestoreSubstitute() {
		String p = SentencePhraseSubstitutor.restoreSubstituteWordByWord("'m");
		assertTrue(p.equalsIgnoreCase("'m"));
	}
	
	public void testRestoreSubstituteWordByWord() {
		String sp = SentencePhraseSubstitutor.generateSubstitute("Jimbo")+" eats "+SentencePhraseSubstitutor.generateSubstitute("grapes");
		System.out.println(sp);
		String p = SentencePhraseSubstitutor.restoreSubstituteWordByWord(sp);
		System.out.println(p);
		assertTrue(p.equalsIgnoreCase("Jimbo eats grapes"));
	}
	
	private void assertMod(String original, String match) {
		String[] substitutionTypeWhitelist = { "popsicles" };
		String mod = SentencePhraseSubstitutor.substitute(original, Ontology.getInstance(), substitutionTypeWhitelist);
		if(match!=null)
			assertTrue(mod.trim().equalsIgnoreCase(match.trim()));
	}
}
