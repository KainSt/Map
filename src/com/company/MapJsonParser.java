package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapJsonParser {
    public static void main(String[] args){
        Map<String, String> stationsList = new LinkedHashMap<>();
        Map<String, String> linesNameList = new LinkedHashMap<>();

        try{
            Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
            Elements lines = doc.select("span[data-line]");
            Elements stations = doc.select("div[data-line]");


            lines.forEach(element -> linesNameList.put(element.attr("data-line") , element.text()));
            stations.forEach(element -> {
                String allInString = element.text();
                String atrr = element.attr("data-line");
                String [] listSt = allInString.split("\\s"+"[0-9]{1,2}"+"\\."+"\\s");
                listSt[0]=listSt[0].substring(3);
                System.out.println();
                for (String station: listSt){
                    stationsList.put(station, atrr);
                }

            });
            System.out.println();
        }
        catch (IOException exception){
            exception.getStackTrace();
        }
        System.out.println("Json parsing");


    }
}