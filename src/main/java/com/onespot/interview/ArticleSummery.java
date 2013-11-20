package com.onespot.interview;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ArticleSummery
{
    public String title;
    public String url;
    public String company;
    public String companyUrl;

    private Element htmlElementContainingArticleLink;

    public ArticleSummery() {}

    public ArticleSummery(Element htmlElementContainingSingleArticleLink)
    {
        /* this constructor exists to make unit tests easier */
        this.htmlElementContainingArticleLink = htmlElementContainingSingleArticleLink;
    }


    public void setArticleSummery(Element htmlElementContainingArticleLink)
    {
        this.htmlElementContainingArticleLink = htmlElementContainingArticleLink;
        setTitle();
        setUrl();
        setCompany();
        setCompanyUrl();
    }

    public String getSummeryCSV()
    {
        return title + "," + url + "," + company + "," + companyUrl;
    }


    public void setTitle()
    {
        title = htmlElementContainingArticleLink.text();
    }

    public void setUrl()
    {
        Element htmlElementContainingArticleUrl = htmlElementContainingArticleLink.child(0);
        url = htmlElementContainingArticleUrl.attr(CSSPatterns.URL_ATTRIBUTE);
    }

    public void setCompany()
    {
        try {
              Document articleHTML = Jsoup.connect(this.url).get();
              Elements htmlElementsContainingCompanyName = articleHTML.select(CSSPatterns.COMPANY_NAME);
              if (htmlElementsContainingCompanyName.isEmpty())
                  company = "n/a";
              else
                  company = htmlElementsContainingCompanyName.get(0).text();
        }
        catch (IOException e) {
              System.err.println(e);
        }
    }

    public void setCompanyUrl()
    {
        try {
              Document articleHTML = Jsoup.connect(this.url).get();
              Elements htmlElementsContainingCompanyUrl = articleHTML.select(CSSPatterns.COMPANY_URL);
              if (htmlElementsContainingCompanyUrl.isEmpty())
                  companyUrl = "n/a";
              else
                  companyUrl = htmlElementsContainingCompanyUrl.get(0).attr(CSSPatterns.URL_ATTRIBUTE);

        }
        catch (IOException e) {
              System.err.println(e);
        }
    }
}
