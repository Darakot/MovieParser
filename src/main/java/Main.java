package main.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        String urlW = "https://weburg.net/movies/info/";
        String urlK = "https://www.kinopoisk.ru/film/";
        Parser parser = new Parser();

//        for (int i =1;i<400;i++) {
//            String sURL = urlK+ i;
//            Map<String,String> info = parser.parserKinopoisk(sURL);
//            Thread.sleep(500);
//            System.out.println(info.get("Название"));
//            System.out.println(info.get("Режиссер"));
//            System.out.println(info.get("Актеры"));
//            System.out.println(info.get("Описание"));
//            System.out.println("------------------------------------------------------------------------------------");
//        }

//        urlW+=1000;
//        HashMap<String,String> info = Parser.parserWeburg(urlW);
        urlK+=822708;
        HashMap<String,String> info = parser.parserKinopoisk(urlK);

//        for (String s :info.keySet()) {
//            System.out.println(s + ":" + info.get(s));
//        }


    }




}
