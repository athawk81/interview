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

    private Element HTML_Element_ContainingArticleLink;

    public ArticleSummery(Element HTML_Element_ContainingSingleArticleLink)
    {
        this.HTML_Element_ContainingArticleLink = HTML_Element_ContainingSingleArticleLink;
    }

    public ArticleSummery() {}

    public void setArticleSummery(Element HTML_Element_ContainingSingleArticleLink)
    {
        this.HTML_Element_ContainingArticleLink = HTML_Element_ContainingSingleArticleLink;
        setTitle();
        setUrl();
        setCompany();
        setCompanyUrl();
    }

    public String getMembersInCSVString()
    {
        return company + "," + companyUrl + "," + title + "," + url;
    }


    public void setTitle()
    {
        title = HTML_Element_ContainingArticleLink.text();
    }

    public void setUrl()
    {
        Element HTML_Element_ContainingArticleUrl = HTML_Element_ContainingArticleLink.child(0);
        url = HTML_Element_ContainingArticleUrl.attr(CSSPatterns.URL_ATTRIBUTE);
    }

    public void setCompany()
    {
        try {
            Document articleHTML = Jsoup.connect(this.url).get();
            Elements HTML_Elements_ContainingCompanyName = articleHTML.select(CSSPatterns.COMPANY_NAME);
            if (HTML_Elements_ContainingCompanyName.isEmpty())
                company = "n/a";
            else
                company = HTML_Elements_ContainingCompanyName.get(0).text();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    public void setCompanyUrl()
    {
        try {
            Document articleHTML = Jsoup.connect(this.url).get();
            Elements HTML_Elements_ContainingCompanyUrl = articleHTML.select(CSSPatterns.COMPANY_URL);
            if (HTML_Elements_ContainingCompanyUrl.isEmpty())
                companyUrl = "n/a";
            else
                companyUrl = HTML_Elements_ContainingCompanyUrl.get(0).attr(CSSPatterns.URL_ATTRIBUTE);

        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
