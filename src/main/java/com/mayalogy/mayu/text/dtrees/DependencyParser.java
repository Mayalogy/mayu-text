package com.mayalogy.mayu.text.dtrees;

import java.util.List;

import edu.stanford.nlp.trees.TypedDependency;

public interface DependencyParser {

	public List<TypedDependency> parseSentence(String sentence);

}
