package utiles.datareaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtil {

    public static Iterator<Object[]> readCSV(String filePath) throws IOException {
        List<Object[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // Skip header
                String[] values = line.split(",");
                data.add(values);
            }
        }
        return data.iterator();
    }
}
