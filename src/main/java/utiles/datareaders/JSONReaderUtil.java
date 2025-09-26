package utiles.datareaders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONReaderUtil {

    public static Iterator<Object[]> readJson(String filePath) throws IOException {
        List<Object[]> data = new ArrayList<>();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Map<String, String>>>() {}.getType();
        List<Map<String, String>> jsonData = gson.fromJson(new FileReader(filePath), listType);

        for (Map<String, String> entry : jsonData) {
            data.add(entry.values().toArray(new Object[0]));
        }
        return data.iterator();
    }
}
