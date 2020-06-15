package me.sam.uitest.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * desc : https://gist.github.com/shortstuffsushi/37ecd21df9b6dca18a3e
 * date : 2020/6/5 5:13 PM
 *
 * @author : dongSen
 */
public class JsonTest {

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("sam");
        list.add("jay");

//        JSONArray jsonArray = toJson(list);
        List<String> strings = null;
        try {
            strings = fromJson(new JSONArray("[\"sam\",\"jay\"]"));
            System.out.println(strings.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("names", list);
//            System.out.println(jsonObject.toString());


    }

    public static JSONArray toJson(List<String> list) {
        JSONArray jsonArray = new JSONArray();
        for (String obj : list) {
            jsonArray.put(obj);
        }
        return jsonArray;
    }

    public static List<String> fromJson(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                String obj = (String) jsonArray.get(i);
                list.add(obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
