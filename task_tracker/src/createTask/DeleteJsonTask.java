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
 		boolean taskFound = false;
 		
 		String msg = "\nWhich task do you want to delete? \n";
 		
 		String task = showMessageAndList(msg);
 		
 		
 		for (Iterator<Map<String, Object>> iterator = jsonList.iterator(); iterator.hasNext(); ) {
 		    Map<String, Object> json = iterator.next();
 		    
 		    if (json.get("description").equals(task)) {
 		        iterator.remove();
 		        taskFound = true;
 		        break;
 		    }
 		}
 		if (!taskFound) {
             System.out.println("Task not found: " + task);
         }
 		
 		String jsonStr = ParseJson.jsonString(jsonList);
 		return jsonStr;
 	}
}