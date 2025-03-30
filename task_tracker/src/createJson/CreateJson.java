package createJson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import bodyProgram.FilePath;

public class CreateJson extends FilePath{
	
	public CreateJson(){
		boolCreate(filePath);
	}
	
	private static void boolCreate(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			createVoidJson(filePath);
		}
	}
	
	private static void createVoidJson(String filePath) {
		try {
			File file = new File(filePath);
			FileWriter writer = new FileWriter(file);
			writer.write("[]");
			writer.close();
		}
		catch(IOException e){
			System.out.println();
		}
	}
}