
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
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
        JSONObject mainJSON = new JSONObject();
        JSONObject stationsStructureJSON = new JSONObject();
        JSONObject linesNameListJSON = new JSONObject();
        for (Map.Entry<String, String> entry : linesNameList.entrySet()) {
            linesNameListJSON.put(entry.getKey(),entry.getValue());
        }

        for (Map.Entry<String, String[]> entry : stationsList.entrySet()) {
            JSONArray arrayStation = new JSONArray();
            for( String s:entry.getValue()){
                arrayStation.add(s);
            }

            stationsStructureJSON.put(entry.getKey(), arrayStation);
        }
        mainJSON.put("linesName",linesNameListJSON);
        mainJSON.put("stationsStructure",stationsStructureJSON);

        System.out.println("Json object done");
        try {
            Files.write(Paths.get("data/lines.json"), mainJSON.toString().getBytes());
        }catch (IOException ex){
            ex.getStackTrace();
        }
    }
}