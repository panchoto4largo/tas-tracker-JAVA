package createJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import bodyProgram.FilePath;

public class JsonEmpty extends FilePath {
	public boolean JsonIsEmpty(){
		boolean x = false;
		try {
            File file = new File(filePath);

            String content = new String(Files.readAllBytes(file.toPath())).trim();

            if ("[]".equals(content)) {
                x = true;
            } else {
            	x = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return x;
	}
}