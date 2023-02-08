package com.example.travel_guide_app_1181390_1182126;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class DestinationJasonParser {
    public static List<Destination> getObjectFromJason(String jason) {
        List<Destination> destination;
        try {
            JSONArray jsonArray = new JSONArray(jason);
            destination = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Destination dest = new Destination();

                dest.setCity(jsonObject.getString("city"));
                dest.setCountry(jsonObject.getString("country"));
                dest.setContinent(jsonObject.getString("continent"));
                dest.setLongitude(jsonObject.getDouble("longitude"));
                dest.setLatitude(jsonObject.getDouble("latitude"));
                dest.setCost(jsonObject.getInt("cost"));
                dest.setImg(jsonObject.getString("img"));
                dest.setDescription(jsonObject.getString("description"));

                destination.add(dest);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return destination;
    }
}
