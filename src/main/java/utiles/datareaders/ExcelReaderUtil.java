package utiles.datareaders;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtil {
    public static Iterator<Object[]> readExcel(String filePath) throws IOException {
        List<Object[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            rows.next(); // Skip header

            while (rows.hasNext()) {
                Row row = rows.next();
                int colCount = row.getLastCellNum();
                String[] values = new String[colCount];
                for (int i = 0; i < colCount; i++) {
                    Cell cell = row.getCell(i);
                    values[i] = (cell == null) ? "" : cell.toString().trim();
                    // Remove ".0" if it's a whole number
                    if (values[i].matches("\\d+\\.0")) {
                        values[i] = values[i].replace(".0", "");
                    }
                }
                data.add(values);
            }
        }
        return data.iterator();
    }
}
