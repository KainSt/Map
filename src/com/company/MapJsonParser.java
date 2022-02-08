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
        List<String> stationList = new LinkedList<>();
        Map<String, String> linesNameList = new LinkedHashMap<>();

        try{
            Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
            Elements metroTable = doc.select("#metrodata");
            Elements lines = metroTable.select("span[data-line]");
            Elements station = metroTable.select("a[data-metrost]");


            lines.forEach(element -> linesNameList.put(element.attr("data-line") , element.text()));
            station.forEach(element -> stationList.add(element.text()));
            System.out.println();
        }
        catch (IOException exception){
            exception.getStackTrace();
        }
        System.out.println();

    }
}