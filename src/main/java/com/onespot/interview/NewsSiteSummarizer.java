package com.onespot.interview;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;


public class NewsSiteSummarizer {

    private  String newsSite;
    private  Elements htmlElementsContainingArticleLinks;
    private  ArticleSummery article;

    public NewsSiteSummarizer(String newsSite)
    {
        article = new ArticleSummery();

        try {
               Document newsSiteHTML = Jsoup.connect(newsSite).get();
               htmlElementsContainingArticleLinks = newsSiteHTML.select(CSSPatterns.ARTICLE_LINKS_ON_HOME_PAGE);
        }
        catch (IOException e) {
               System.err.println(e);
        }
    }

    public int writeArticleSummariesToCSV(String fileName)
    {
        int numWrites=0;
        try {
               File file = new File(fileName);
               BufferedWriter output = new BufferedWriter(new FileWriter(file));

               for (Element htmlElementContainingArticleLink : htmlElementsContainingArticleLinks)  {
                   article.setArticleSummery(htmlElementContainingArticleLink);
                   String articleSummeryCSV = article.getSummeryCSV();
                   output.write(articleSummeryCSV + "\n");
                   numWrites++;
               }
               output.close();
            }
            catch (IOException e) {
                   System.err.println(e);
        }
        return numWrites;
    }
}



