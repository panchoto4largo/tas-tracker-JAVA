package createTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import bodyProgram.FilePath;
import bodyProgram.ShowList;

public class ChangeStatement extends FilePath{
	private int toChange;
	private static final String inProgress = "in-progress";
	private static final String done = "done";
	
	public ChangeStatement(int toChange){
		this.toChange = toChange;
		whichTask();
	}
	

	private void whichTask() {
		Scanner sc = new Scanner(System.in);
		File file = new File(filePath);
		
		List<Map<String, Object>> jsonList = ShowList.returnAllTask();
		
		System.out.println("\nWhich task do you want to mark? \n");
		ShowList show = new ShowList(1);
		String task = sc.nextLine();
		
		String jsonStr = changeMark(jsonList, task);
		
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(jsonStr);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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