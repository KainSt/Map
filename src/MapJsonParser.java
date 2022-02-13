import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapJsonParser {
    public static void main(String[] args){
        Map<String, String[]> stationsList = new LinkedHashMap<>();
        Map<String, String> linesNameList = new LinkedHashMap<>();

        try{
            Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
            Elements lines = doc.select("span[data-line]");
            Elements stations = doc.select("div[data-line]");


            lines.forEach(element -> linesNameList.put(element.attr("data-line") , element.text()));
            stations.forEach(element -> {
                String [] listSt = element.text().split("\\s"+"[0-9]{1,2}"+"\\."+"\\s");
                listSt[0]=listSt[0].substring(3);
                System.out.println();
                stationsList.put(element.attr("data-line"),listSt);


            });
            System.out.println();
        }
        catch (IOException exception){
            exception.getStackTrace();
        }
        System.out.println("Json parsing");
        


    }
}