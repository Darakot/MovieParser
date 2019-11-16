package main.java;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "https://weburg.net/movies/info/";

        for (int i =1;i<150;i++) {
            String s = url+ i;
            System.out.println(s);

        parser(s);
        }
    }

    public static void parser(String url){

        Document page = null;
        try {
            page = Jsoup.connect(url)
                    .userAgent("Chrome/74.0.3729.169 Safari/537.36")
                    .referrer("http://www.google.com")
                    .get();
        }catch (NullPointerException e) {
            e.getMessage();}
        catch (HttpStatusException e) {
            System.out.println(e.getStatusCode());
        }catch (IOException e) {
            e.printStackTrace();
        }

//        Map<String,String> film = new HashMap<>();
//        Element nameFilm = page.select("h3.wb-promo-box-heading").first();
//        Element annotationFilm = page.select("table.wb-layout-about").first();
//        Element directorFilm = page.select("li.wb-tags-series-item").first();
//        Elements elements = page.select("div.wb-tags-series");
//        String sDirectorFilm = new String(directorFilm.text().getBytes("UTF-8"));
//        String sAnnotationFilm = new String(annotationFilm.select("p").text().getBytes("UTF-8"));
//        String sNameFilm = new String(nameFilm.text().getBytes("UTF-8"));
//        film.put("Название фильма",sNameFilm);
//        film.put("Режиссер",sDirectorFilm);
//        film.put("Описание",sAnnotationFilm);


        if (page!=null) {
            Elements elements = page.select("div.wb-tags-series");
//        for (String s :film.keySet()) {
//            System.out.println(s + ":" + film.get(s));
//        }

            for (Element e:elements) {
                String s = null;
                try {
                    s = new String(e.text().getBytes("UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
                System.out.println(s);
            }
        }

    }

}
