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
		
		String task = showMessageAndList(msg);
		
		String jsonStr = changeMark(jsonList, task);
		
		writeJson(jsonStr);
		
		System.out.println("The status has been changed.\n");
	}
	
	private String changeMark(List<Map<String, Object>> jsonList, String task) {
		boolean taskFound = false;
		for (Iterator<Map<String, Object>> iterator = jsonList.iterator(); iterator.hasNext(); ) {
		    Map<String, Object> json = iterator.next();
		    
		    if (json.get("description").equals(task)) {
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
            System.out.println("Task not found: " + task);
        }
		
		String jsonStr = ParseJson.jsonString(jsonList);
		
		return jsonStr;
	}
}