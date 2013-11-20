package com.onespot.interview;

/**
 * Created with IntelliJ IDEA.
 * User: alexhawk
 * Date: 11/17/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class TechCrunchSummerizer {
    public static void main(String[] args) {
        NewsSiteSummarizer techCrunch = new NewsSiteSummarizer("http://TechCrunch.com/");
        System.out.println(args[0]);
        techCrunch.writeArticleSummariesToCSV(args[0]);
    }

}
