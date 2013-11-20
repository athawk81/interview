package com.onespot.interview;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsSiteSummarizerTest {
    private NewsSiteSummarizer newsSummarizerTest;

    @Before
    public void setUp()
    {
            newsSummarizerTest = new NewsSiteSummarizer("http://TechCrunch.com/");
    }

    @Test
    public void newsSummarizerTest()
    {
        int numWrites = newsSummarizerTest.writeArticleSummariesToCSV("/Users/alexhawk/jvPrac/techCrunch");
        int numArticlesOnTechCrunchHomePage = 20;
        assertEquals(numWrites, numArticlesOnTechCrunchHomePage);
    }
}
