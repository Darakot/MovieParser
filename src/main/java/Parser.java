package main.java;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Парсер предназначен для 2 сайтов.
 * На кинопоиске стоит защита от парсинга.Пока не понятно как обойти.
 */


public class Parser {

    public Parser() {
    }

    public static Document getPage(String url) throws IOException {

        Document page = null;
        try {
            page = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36")
                    .referrer("http://www.google.com")
                    .get();
        } catch (NullPointerException e) {
            e.getMessage();
        } catch (HttpStatusException e) {
            System.out.println(url + " " +e.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return page;
    }

    public static HashMap<String, String> parserWeburg(String url) throws IOException {
        Map<String, String> description = new HashMap<>();

        Document page = getPage(url);

        if (page != null) {
            Element movieTitle = page.select("h3.wb-promo-box-heading").first();
            Element filmDescription = page.select("table.wb-layout-about").first();
            Elements movieInfo = page.select("div.wb-tags-series");
            Element movieRatings = page.select("ul.external-ratings__list").first();
            String sAnnotationFilm = null;
            String sNameFilm = null;
            System.out.println(movieRatings);
            try {
                sNameFilm = new String(movieTitle.text().getBytes("UTF-8"));
                sAnnotationFilm = new String(filmDescription.select("p").text().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            description.put("Название", sNameFilm);
            description.put("Описание", sAnnotationFilm);
            for (Element eMovieInfo : movieInfo) {
                String sMovieInfo = null;
                try {
                    sMovieInfo = new String(eMovieInfo.text().getBytes("UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
                String[] sPr = sMovieInfo.split(":");
                description.put(sPr[0], sPr[1]);
            }

        }
        return (HashMap<String, String>) description;
    }

    public static HashMap<String,String> parserKinopoisk(String url) throws IOException {
        Map<String, String> description = new HashMap<>();

        Document page = getPage(url);
        System.out.println(page);

        if(page!=null){
            Elements movieInfo = page.select("table.info");

//            System.out.println(movieInfo);

            for (Element eMovieInfo : movieInfo) {
                String sMovieInfo = null;
                try {
                    sMovieInfo = new String(eMovieInfo.text().getBytes("UTF-8"));
                    System.out.println(sMovieInfo);
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
//                String[] sPr = sMovieInfo.split(":");
//                description.put(sPr[0], sPr[1]);
            }
        }

        return (HashMap<String, String>) description;
    }
}
