package createTask;

import java.util.*;
import createJson.JsonEmpty;
import createJson.ObtainJsonBody;
import bodyProgram.ShowList;

public class CreateJsonTask extends CommonFunctions{
	private int id;
	private String description;
	private static String status = "undone";
	private String createdAt;
	private String updatedAt;
	
	private String task;
	
	public CreateJsonTask() {
		this.createdAt = getCurrentTime();
		this.updatedAt = this.createdAt;
		createTask();
	}
	 
	private void createTask() {
		Scanner sc = new Scanner(System.in);
		Map<String, Object> jsonMap = new LinkedHashMap<>();
		
		System.out.println("What task you have to do?");
		task = sc.nextLine();
		
		while(taskFilter(task)) {
			System.out.println("What task you have to do?");
			task = sc.nextLine();
		}
		
		id = createId();
		
		jsonMap.put("id", id);
		jsonMap.put("description", task);
		jsonMap.put("status", status);
		jsonMap.put("createdAt", createdAt);
		jsonMap.put("updatedAt", createdAt);
		
				
		StringBuilder jsonTask = new StringBuilder();
		jsonTask.append("{");
		
		int count = 0;
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            jsonTask.append("\"").append(entry.getKey()).append("\":");
            if (entry.getValue() instanceof String) {
                jsonTask.append("\"").append(entry.getValue()).append("\"");
            } else {
                jsonTask.append(entry.getValue());
            }

            if (++count < jsonMap.size()) {
                jsonTask.append(",");
            }
        }

        jsonTask.append("}");
        implementsTask(jsonTask.toString());
    }
	
	private void implementsTask(String str) {		
		boolean jsonBoolean = JsonEmpty.JsonIsEmpty();
		 
		if(jsonBoolean){
			str = "[" + str + "]";
			writeJson(str);
		}
		else {
			ObtainJsonBody body = new ObtainJsonBody();
			String content = body.JsonBody(str); 
			writeJson("[" + content + "]");
		}
	}
	
	private int createId() {
	    List<Map<String, Object>> jsonList = ShowList.returnAllTask();
	    boolean jsonBoolean = JsonEmpty.JsonIsEmpty();

	    if (jsonBoolean) {
	        return 1;
	    } else {
	        int maxId = 0;
	        for (Map<String, Object> json : jsonList) {
	            Object idObject = json.get("id");
	            if (idObject != null && idObject instanceof Integer) {
	                int idTask = (Integer) idObject;
	                if (idTask > maxId) {
	                    maxId = idTask;
	                }
	            }
	        }
	        return maxId + 1;
	    }
	}
}