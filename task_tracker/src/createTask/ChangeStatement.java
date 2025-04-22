package createTask;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import bodyProgram.ShowList;

public class ChangeStatement extends CommonFunctions{
	private int toChange;
	private static final String inProgress = "in-progress";
	private static final String done = "done";
	
	public ChangeStatement(int toChange){
		this.toChange = toChange;
		whichTask();
	}
	
	private void whichTask() {
		List<Map<String, Object>> jsonList = ShowList.returnAllTask();
		
		String msg = "\nWhich task do you want to mark? \n";
		
		String jsonStr = changeMark(jsonList, msg);
		
		writeJson(jsonStr);
		
		System.out.println("The status has been changed.\n");
	}
	
	private String changeMark(List<Map<String, Object>> jsonList, String msg) {
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
	        	if(this.toChange == 4) {
		        	json.put("status", inProgress);
		        }else {
		        	json.put("status", done);
		        }
		        String updatedAt = getCurrentTime();
		        json.put("updatedAt", updatedAt);
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