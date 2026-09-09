package brightlyTestQa.utils;

import org.apache.commons.io.FileUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> getJsonDatgaToMap() throws IOException {
        String jsonContent =FileUtils.readFileToString(new File("src/main/resources/dataSet/toFromAddress.json"), "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> dataMap = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {

        });

        return dataMap;

    }
}
