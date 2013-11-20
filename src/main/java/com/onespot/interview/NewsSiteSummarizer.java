package com.onespot.interview;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;


public class NewsSiteSummarizer {

    private  String newsSite;
    private  Elements HTML_Elements_ContainingArticleLinks;
    private  ArticleSummery article;

    public NewsSiteSummarizer(String newsSite)
    {
        article = new ArticleSummery();

        try {
        Document newsSiteHTML = Jsoup.connect(newsSite).get();
        HTML_Elements_ContainingArticleLinks = newsSiteHTML.select(CSSPatterns.ARTICLE_LINKS);
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    public void writeArticleSummariesToCSV(String fileName)
    {
        try {
            File file = new File(fileName);
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            for (Element HTML_Element_ContainingSingleArticleLink : HTML_Elements_ContainingArticleLinks)  {
                article.setArticleSummery(HTML_Element_ContainingSingleArticleLink);
                //System.out.println(article.getMembersInCSVString());
                output.write(article.getMembersInCSVString() + "\n");
             }
            output.close();
            }
            catch (IOException e) {
                System.err.println(e);
        }
    }
}


