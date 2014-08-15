package com.mayalogy.mayu.text;

import com.mayalogy.mayu.core.LookupSet;
import com.mayalogy.mayu.io.JarReader;

public class PrepositionSet extends LookupSet {

    private static PrepositionSet instance = null;

    private PrepositionSet() {
    	final String file = "/data/nlp/preps.txt";
        try {
        	load(JarReader.getResourceAsReader(file));
        } catch(Exception e) { 
        	throw new MayuTextException("Error reading file: " + file, e);
        }
    }

    public static PrepositionSet getInstance() {
        if(instance==null)
            instance=new PrepositionSet();
        return instance;
    }
}