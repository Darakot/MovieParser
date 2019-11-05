package main.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Document page = null;
        try {
            page = Jsoup.connect("https://weburg.net/movies/info/36962")
                    .userAgent("Chrome/74.0.3729.169 Safari/537.36")
                    .referrer("http://www.google.com")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        page.outputSettings().charset("UTF-8");
//        page.outputSettings().charset("windows-1251");

        Elements element = page.select("a.wb-link");
        System.out.println(element);
    }
}
