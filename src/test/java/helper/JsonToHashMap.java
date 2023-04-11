package helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

//    {
//        "name" :"abc",
//            "email id " : ["abc@gmail.com", "def@gmail.com", "ghi@gmail.com"]
//    }
public class JsonToHashMap {

public static void main (String[] args){

    HashMap<String, Object> retHM = new HashMap<>();

    ArrayList<String> emailIds = new ArrayList<>();
    emailIds.add("abc@gmail.com");
    emailIds.add("def@gmail.com");
    emailIds.add("ghi@gmail.com");

    retHM.put("name","abc");
    retHM.put("email id",emailIds);

    System.out.println(retHM);

}

//    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
//        Map<String, Object> retMap = new HashMap<String, Object>();
//
//        if(json != JSONObject.NULL) {
//            retMap = toMap(json);
//        }
//        return retMap;
//    }
//
//    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        Iterator<String> keysItr = object.keys();
//        while(keysItr.hasNext()) {
//            String key = keysItr.next();
//            Object value = object.get(key);
//
//            if(value instanceof JSONArray) {
//                value = toList((JSONArray) value);
//            }
//
//            else if(value instanceof JSONObject) {
//                value = toMap((JSONObject) value);
//            }
//            map.put(key, value);
//        }
//        return map;
//    }
//
//    public static List<Object> toList(JSONArray array) throws JSONException {
//        List<Object> list = new ArrayList<Object>();
//        for(int i = 0; i < array.length(); i++) {
//            Object value = array.get(i);
//            if(value instanceof JSONArray) {
//                value = toList((JSONArray) value);
//            }
//
//            else if(value instanceof JSONObject) {
//                value = toMap((JSONObject) value);
//            }
//            list.add(value);
//        }
//        return list;
//    }
//    public static void main (String[] args) throws JsonProcessingException {
//        String jsonStr = "{\\r\\n\\\"name\\\" : \\\"abc\\\" ,\\r\\n\\\"email id \\\" : [\\\"abc@gmail.com\\\",\\\"def@gmail.com\\\",\\\"ghi@gmail.com\\\"]\\r\\n}";
//        Map<String, Object> mapping = new ObjectMapper().readValue(jsonStr, HashMap.class);
//
//        mapping.entrySet().forEach(entry -> {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        });
//
//
//    }
}
