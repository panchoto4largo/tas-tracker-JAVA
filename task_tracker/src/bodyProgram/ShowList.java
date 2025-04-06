package bodyProgram;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import createJson.ObtainJsonBody;
import createTask.ParseJson;

public class ShowList {
    
    public ShowList(int list) {
    	switch (list) {
    		case 1:
    			List<Map<String, Object>> jsonList = returnAllTask();
    			showTask(jsonList);
    			break;
    		case 2:
    			showDoneTask();
    			break;
    		case 3:
    			showInProgressTask();
    			break;
    		case 4:
    			showUndoneTask();
    			break;
    	}		
    }
    
    public static List<Map<String, Object>> returnAllTask(){
		ObtainJsonBody body = new ObtainJsonBody();
		String jsonString = body.JsonBody(null); 
		
		ParseJson parseJson = new ParseJson();
		List<Map<String, Object>> jsonList = parseJson.parseJsonArray(jsonString);
		
		return jsonList;
	}
    
    private static void showTask(List<Map<String, Object>> jsonList) {
    	if(jsonList.size() != 0) {
	    	for(Map<String, Object> json : jsonList) {
	            System.out.println(json.get("description"));
			}
    	}else {
    		System.out.println("The list is empty");
    	}
    }
    
    private static List<Map<String, Object>> returnTaskList(String statement){
    	List<Map<String, Object>> jsonlist = returnAllTask();
    	
    	for (Iterator<Map<String, Object>> iterator = jsonlist.iterator(); iterator.hasNext(); ) {
		    Map<String, Object> json = iterator.next();
		    
		    if (!json.get("description").equals(statement)) {
		        iterator.remove();
		    }
		}
    	return jsonlist;
    }
    
    private void showDoneTask() {
    	List<Map<String, Object>> jsonDoneList = returnTaskList("done");
    	showTask(jsonDoneList);
    }
    private void showInProgressTask() {
    	List<Map<String, Object>> jsonInProgressList = returnTaskList("in-progress");
    	showTask(jsonInProgressList);
    }
    private void showUndoneTask() {
    	List<Map<String, Object>> jsonUndoneList = returnTaskList("undone");
    	showTask(jsonUndoneList);
    }
}