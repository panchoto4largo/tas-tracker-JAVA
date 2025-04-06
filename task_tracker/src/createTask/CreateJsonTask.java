package createTask;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;


import createJson.JsonEmpty;
import createJson.ObtainJsonBody;
import bodyProgram.FilePath;

public class CreateJsonTask extends FilePath{
	private static int id = 1;
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
        id++;
    }
	
	private void implementsTask(String str) {
		File file = new File(filePath);
		
		JsonEmpty jsonEmptiness = new JsonEmpty();
		boolean jsonBoolean = jsonEmptiness.JsonIsEmpty();
		 
		if(jsonBoolean){
			try {
				FileWriter writer = new FileWriter(file);
				writer.write("[" + str + "]");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			ObtainJsonBody body = new ObtainJsonBody();
			String content = body.JsonBody(str); 
			try (FileWriter writer = new FileWriter(file)) {
                writer.write("[" + content + "]");
            }catch (FileNotFoundException e) {
			e.printStackTrace();
            } catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	
    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}