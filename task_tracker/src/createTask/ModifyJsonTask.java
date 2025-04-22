package createTask;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import bodyProgram.ShowList;

public class ModifyJsonTask extends CommonFunctions{
	public ModifyJsonTask() {
		changeTask();
	}
	
	private void changeTask() {
		Scanner sc = new Scanner(System.in);
		
		List<Map<String, Object>> jsonList = ShowList.returnAllTask();
		
		String msg = "\nWhich task do you want to change";
		
		String jsonStr = changeDescription(jsonList, msg);
		
		writeJson(jsonStr);
	}
	
	private String changeDescription(List<Map<String, Object>> jsonList, String msg) {
		Scanner sc = new Scanner(System.in);

		String task = showMessageAndList(msg);
	    boolean taskFound = false;
	    
	    for (Iterator<Map<String, Object>> iterator = jsonList.iterator(); iterator.hasNext(); ) {
	        Map<String, Object> json = iterator.next();

	        boolean matchByDescription = task.equals(json.get("description"));
	        boolean matchById = false;

	        if (task.matches("\\d+")) {
	                int taskId = Integer.parseInt(task);
	                Object idObj = json.get("id");

	                if (idObj instanceof Integer && taskId == (Integer) idObj) {
	                    matchById = true;
	                }
	        }
	        if (matchByDescription || matchById) {
	        	System.out.println("How to rename?");
	        	String rename = sc.nextLine();
	        	while(taskFilter(rename)) {
	    			System.out.println("How to rename?");
	    			rename = sc.nextLine();
	    		}
		        json.put("description", rename);
		        taskFound = true;
		        break;
	        }
	    }
	    if (!taskFound) {
	        System.out.println("The task has not been found");
	    }
	    
		String jsonStr = ParseJson.jsonString(jsonList);
		
		return jsonStr;
	}
}