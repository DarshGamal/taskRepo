package utiles.datareaders;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

public class DataProviderUtils {

    public static Iterator<Object[]> getData(String filePath) throws IOException {
        String lowerPath = filePath.toLowerCase(Locale.ROOT);
        if (lowerPath.endsWith(".csv")) {
            return CSVReaderUtil.readCSV(filePath);
        } else if (lowerPath.endsWith(".xlsx")) {
            return ExcelReaderUtil.readExcel(filePath);
        } else if (lowerPath.endsWith(".json")) {
            return JSONReaderUtil.readJson(filePath);
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }
}
