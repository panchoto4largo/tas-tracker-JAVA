package createTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import bodyProgram.FilePath;
import bodyProgram.ShowList;

public class DeleteJsonTask extends FilePath{
	public DeleteJsonTask(){
		File file = new File(filePath);

		List<Map<String, Object>> jsonList = ShowList.returnAllTask();
		
		String jsonStr = taskToDelete(jsonList);
		
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(jsonStr);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("The task has been eliminated.\n");
	}
	
	private String taskToDelete(List<Map<String, Object>> jsonList) {
 		Scanner sc = new Scanner(System.in);
 		boolean taskFound = false;
 		
 		System.out.println("\nWhich task do you want to delete? \n");
 		ShowList show = new ShowList(1);
 		String task = sc.nextLine();
 
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