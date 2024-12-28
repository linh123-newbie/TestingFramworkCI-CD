package testingwebdriver.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.jar.asm.TypeReference;


public class DataReader {
	public List<HashMap<String, String>> getJsonDataMap() throws IOException {
		//Read json to string
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//TestingWebDriver//data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);
		
		//convert to hashmap jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new com.fasterxml.jackson.core.type.TypeReference<List<HashMap<String, String>>>(){
			
		});
		return data;
	}
}
