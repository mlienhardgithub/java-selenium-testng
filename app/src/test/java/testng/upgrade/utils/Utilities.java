package testng.upgrade.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilities {
	
	/**
	 * deserialize the JSON file to an object
	 * @param filePath
	 * @param returnType
	 * @return
	 * @throws Exception
	 */
	public <T> T deserializeTheJsonFile(String filePath, Class<T> returnType) throws Exception {
		T deserializedObject = null;
		File file = new File(filePath);
		if (file.exists()) {
			InputStream inputStream = Files.newInputStream(Paths.get(file.toURI()));
			JsonFactory jsonFactory = new JsonFactory();
			ObjectMapper mapper = new ObjectMapper();
			jsonFactory.setCodec(mapper);
			JsonParser jsonParser = jsonFactory.createParser(inputStream);
			try {
				//jsonParser.readValueAs(returnType);
				deserializedObject = mapper.readValue(jsonParser, returnType);
			} catch (com.fasterxml.jackson.core.JsonParseException e) {
				try {
					//retry
					deserializedObject = mapper.readValue(jsonParser, returnType);
				} catch (com.fasterxml.jackson.core.JsonParseException e2) {
					throw new RuntimeException(e2);
				}
			}
		} else {
			throw new Exception("the file does not exist: " + filePath);
		}
		return (T) deserializedObject;
	}
	
	public static Properties loadConfigProperties() throws IOException {
        Properties configProps = new Properties();
        InputStream inputStream = Utilities.class
        		.getClassLoader().getResourceAsStream("config.properties");
        configProps.load(inputStream);
        inputStream.close();
        return configProps;
    }
}
