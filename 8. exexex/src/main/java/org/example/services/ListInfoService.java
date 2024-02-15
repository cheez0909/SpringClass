package org.example.services;


import org.example.models.ListDto;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ListInfoService {

    public List<Map<String, Object>> getList(){
        List<Map<String, Object>> list = new ArrayList<>();
        String result = "";
        try{
            URL url = new URL("http://www.wamis.go.kr:8080/wamis/openapi/wkw/rf_dubrfobs");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            try (BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"))) {
                result = bf.readLine();
            }

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONArray jsonArray = (JSONArray) jsonObject.get("list");

            for(int i=0; i<jsonArray.size(); i++){
                JSONObject object = (JSONObject)jsonArray.get(i);

                Map<String, Object> map = new HashMap<>();
                for (Object key : object.keySet()) {
                    String keyStr = (String) key;
                    Object keyVal = object.get(keyStr);
                    map.put(keyStr, keyVal);
                }

                list.add(map);
            }

            return list;
        }catch (Exception e){
            e.printStackTrace();
            return list;
        }
    }
}
