package createTask;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParseJson {
	
	
	public static String jsonString(List<Map<String, Object>> jsonList) {
		StringBuilder jsonStr = new StringBuilder();
		
		jsonStr.append("[");
		
		for(int i = 0; i < jsonList.size(); i++) {
			Map<String, Object> map = jsonList.get(i); 
			jsonStr.append("{");
			
			for(Map.Entry<String, Object> entry: map.entrySet()) {
				jsonStr.append("\"").append(entry.getKey()).append("\":")
                .append("\"").append(entry.getValue()).append("\"");
				
				if (i != map.size() - 1) {
	                jsonStr.append(", ");
	            }
				jsonStr.append("}");
			}
			
			if (i != jsonList.size() - 1) {
                jsonStr.append(", ");
            }
		}
		jsonStr.append("]");
		
		return jsonStr.toString();
	}
	
	public List<Map<String, Object>> parseJsonArray(String jsonString){
		List<Map<String, Object>> list = new ArrayList<>();
		
		String[] jsonObjects = jsonString.split("(?<=\\}),");
		
		
		for(String jsonObject : jsonObjects) {
			jsonObject = jsonObject.trim();
			
			Map<String, Object> map = parseEachJson(jsonObject);
			
			list.add(map);
		}
		return list;
	}
	
	private static Map<String, Object> parseEachJson(String jsonObject){
		Map<String, Object> map = new LinkedHashMap<>();
		
		jsonObject = jsonObject.trim().substring(1, jsonObject.length() - 1);
		
		String[] objects = jsonObject.split("},\\{");
        for (String obj : objects) {
            obj = obj.replace("{", "").replace("}", "");


            String[] keyValuePairs = obj.split(",");

            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");

                String key = keyValue[0].replace("\"", "").trim();
                String value = keyValue[1].replace("\"", "").trim();

                map.put(key, value);
            }
        }
        return map;
	}
}
