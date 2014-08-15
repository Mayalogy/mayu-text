package com.mayalogy.mayu.text;

import java.util.List;

import com.mayalogy.mayu.text.SentenceSplitter;
import com.mayalogy.mayu.text.TaggedText;

import junit.framework.TestCase;

public class TestSentenceSplitter extends TestCase {

    public void testSplit() {
        SentenceSplitter splitter = new SentenceSplitter();
        List<TaggedText> sents = splitter.split("I like dogs. They are my friend, except on Wednesdays. Yeah.");
        for(TaggedText s:sents) {
            System.out.println(s.getText());
        }
        assertTrue(sents.size()==3);
    }
}
