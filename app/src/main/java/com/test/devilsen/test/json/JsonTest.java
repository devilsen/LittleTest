package com.test.devilsen.test.json;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 * date: 2020/07/06 0006
 *
 * @author: dongsen
 */
public class JsonTest {

    public static void main(String[] args) {
        JsonTest test = new JsonTest();

        Gson gson = new Gson();
        String json = test.mapToJson(gson);

        HashMap hashMap = gson.fromJson(json, HashMap.class);
        System.out.println(hashMap.toString());
    }

    private String mapToJson(Gson gson) {
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("name", "google");
        inputMap.put("site", "http://www.google.com");
        // convert map to JSON String
        String jsonStr = gson.toJson(inputMap);
        System.out.println(jsonStr);
        return jsonStr;
    }

}
