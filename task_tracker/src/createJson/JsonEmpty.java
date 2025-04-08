package createJson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import bodyProgram.FilePath;

public class JsonEmpty extends FilePath {
	public static boolean JsonIsEmpty() {
	    File file = new File(filePath);
	    if (!file.exists() || file.length() == 0) return true;

	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (!line.trim().equals("[]") && !line.trim().isEmpty()) {
	                return false;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return true;
	}
}