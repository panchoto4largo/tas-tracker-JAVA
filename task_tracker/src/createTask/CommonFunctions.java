package createTask;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        return sdf.format(new Date());
	    }
}
