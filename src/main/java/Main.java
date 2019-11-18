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

public class Main {
    public static void main(String[] args) {
        String url = "https://weburg.net/movies/info/";
//        String url = "https://www.kinopoisk.ru/film/";

        for (int i =1;i<400;i++) {
            String sURL = url+ i;
            Map<String,String> info = parser(sURL);
            System.out.println(info.get("Название"));
            System.out.println(info.get("Режиссер"));
            System.out.println(info.get("Актеры"));
            System.out.println(info.get("Описание"));
            System.out.println("------------------------------------------------------------------------------------");
        }

//        url+=1000;
//        HashMap<String,String> info = parser(url);
//
//
//
//        for (String s :info.keySet()) {
//            System.out.println(s + ":" + info.get(s));
//        }


    }


    public static HashMap<String, String> parser(String url) {
        Map<String, String> description = new HashMap<>();

        Document page = null;
        try {
            page = Jsoup.connect(url)
                    .userAgent("Chrome/74.0.3729.169 Safari/537.36")
                    .referrer("http://www.google.com")
                    .get();
        } catch (NullPointerException e) {
            e.getMessage();
        } catch (HttpStatusException e) {
            System.out.println(url + " " +e.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }



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

}
