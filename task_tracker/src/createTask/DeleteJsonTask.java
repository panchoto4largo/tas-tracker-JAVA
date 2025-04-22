package createTask;

import java.util.*;

import bodyProgram.ShowList;

public class DeleteJsonTask extends CommonFunctions{
	public DeleteJsonTask(){

		List<Map<String, Object>> jsonList = ShowList.returnAllTask();
		
		String jsonStr = taskToDelete(jsonList);
		
		writeJson(jsonStr);
		
		System.out.println("The task has been eliminated.\n");
	}
	
	private String taskToDelete(List<Map<String, Object>> jsonList) {
 		String msg = "\nWhich task do you want to delete? \n";
 		
 		jsonList = selectTheTask(msg, jsonList);
 		
 		String jsonStr = ParseJson.jsonString(jsonList);
 		return jsonStr;
 	}
	
	private List<Map<String, Object>> selectTheTask(String msg, List<Map<String, Object>> jsonList) {
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
	            iterator.remove();
	            taskFound = true;
	            break;
	        }
	    }
	    if (!taskFound) {
	        System.out.println("The task has not been found");
	    }

	    return jsonList;
	}
}