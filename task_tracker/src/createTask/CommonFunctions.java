package createTask;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bodyProgram.FilePath;
import bodyProgram.ShowList;

public class CommonFunctions extends FilePath{
	
		public void writeJson(String jsonStr){
			try {
				FileWriter writer = new FileWriter(filePath);
				writer.write(jsonStr);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public String showMessageAndList(String msg) {
			Scanner sc = new Scanner(System.in);
			System.out.println(msg);
	 		ShowList show = new ShowList(1);
	 		String task = sc.nextLine();
			return task;
		}
		
		public String getCurrentTime() {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	        return sdf.format(new Date());
	    }
		
		public Boolean taskFilter(String task) {
			boolean taskFound = false;
			List<Map<String, Object>> jsonList = ShowList.returnAllTask();
			for (Iterator<Map<String, Object>> iterator = jsonList.iterator(); iterator.hasNext(); ) {
	 		    Map<String, Object> json = iterator.next();
	 		    
	 		    if (json.get("description").equals(task)) {
	 		        taskFound = true;
	 		        break;
	 		    }
	 		}
	 		if (taskFound) {
	 			System.out.println("\nYou have another task with that description");
	 			return true;
	        }
	 		if(task.length() < 3) {
	 			System.out.println("\nThe task cant be shorter than 3 words");
	 			return true;
	 		}
	 		return false;
		}
}