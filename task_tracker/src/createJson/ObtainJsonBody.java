package createJson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import bodyProgram.FilePath;

public class ObtainJsonBody extends FilePath{
	public String JsonBody(String str) {
		String content = "";
		try {
			StringBuilder sb = new StringBuilder();
			
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			
			content = sb.toString();
			if (content.startsWith("[") && content.endsWith("]")) {
	            content = content.substring(1, content.length() - 1);
	        }
			
			if (str != null && !str.isBlank()) {
			    content = content.isEmpty() ? str : content + "," + str;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}