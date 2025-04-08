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
		
		String task = showMessageAndList(msg);
		
		String jsonStr = changeDescription(jsonList, task);
		
		writeJson(jsonStr);
	}
	
	private String changeDescription(List<Map<String, Object>> jsonList, String task) {
		Scanner sc = new Scanner(System.in);
		boolean taskFound = false;
		for (Iterator<Map<String, Object>> iterator = jsonList.iterator(); iterator.hasNext(); ) {
			
		    Map<String, Object> json = iterator.next();
		    
		    if (json.get("description").equals(task)) {
		    	System.out.println("\nHow to rename? \n");
		        String rename = sc.nextLine();
		        json.put("description", rename);
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