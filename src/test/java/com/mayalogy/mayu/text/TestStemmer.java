package com.mayalogy.mayu.text;

import java.io.File;

import junit.framework.TestCase;

import com.mayalogy.mayu.core.LookupSet;
import com.mayalogy.mayu.text.CachingStemmer;

public class TestStemmer extends TestCase {

	public TestStemmer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void testOneLiner() throws Exception {
		System.out.println(CachingStemmer.getInstance().stem("'m"));
	}

	public void testStem() throws Exception {
		File f = new File("src/test/resources/data/stress_strings.txt");

		for(String s:LookupSet.getFromFile(f.getAbsolutePath(), false)) {
			String stemmed = CachingStemmer.getInstance().stem((s));
			assertTrue(stemmed!=null);
		}

		assertTrue(CachingStemmer.getInstance().stem("Pig farms on the lake's").equalsIgnoreCase("Pig farms on the lake"));
	}

}
