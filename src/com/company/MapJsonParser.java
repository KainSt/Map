package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class MapJsonParser {
    public static void main(String[] args){
        List<String> nameList = new LinkedList<>();
        try{
            Document doc = Jsoup.parse(new URL("https://www.moscowmap.ru/metro.html#lines"),3000);
            Elements metroTable = doc.select("#metrodata");
            Elements lines = metroTable.select("div[class=js-metro-stations t-metrostation-list-table]");
            Elements station = lines.select("span[class=name]");
            station.forEach(element -> nameList.add(element.text()));
            System.out.println();
        }
        catch (IOException exception){
            exception.getStackTrace();
        }
        System.out.println();
    }
}