package helper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {
//Goal

    // Create object for XSSFWorkbook class
    // Get Access to Sheet
    // Get Access to all rows of sheet
    // Get Access to specific row from all rows
    // Get Access to all cells
    // Access the data from excel to Arrays

    public ArrayList<String> getData(String testCaseName) throws IOException {
        ArrayList<String> a = new ArrayList<String>();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/RSAGoogleMaps.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);

        int sheetCount = xssfWorkbook.getNumberOfSheets();
        for (int i = 0; i < sheetCount; i++) {
            if (xssfWorkbook.getSheetName(i).equalsIgnoreCase("test data")) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
                // Identify Testcases column by scanning the entire 1st row

                Iterator<Row> rows = xssfSheet.iterator();      // Sheet is collection of rows
                Row firstRow = rows.next();

                Iterator<Cell> cells = firstRow.cellIterator(); // row is collection of cells
                int k = 0;
                int column = 0;
                while (cells.hasNext()) {
                    Cell value = cells.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        //desired column
                        column = k;
                    }
                    k++;
                }
                System.out.println(column); // TestCases column identified now to find purchase (specific row) from all rows
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {
                                a.add(c.getStringCellValue());
                            } else {
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {


    }
}