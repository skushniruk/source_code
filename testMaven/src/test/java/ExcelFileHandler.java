import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by forteadmin on 12/4/15.
 */
public class ExcelFileHandler {

    public List<List<String>> readFromFile(String filename) {
        List<List<String>> table = new ArrayList<List<String>>();

        FileInputStream file = null;
        try {
            file = new FileInputStream(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext())
        {
            List<String> rowData = new ArrayList<String>();

            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();

                switch(cell.getCellType()){
                    case Cell.CELL_TYPE_BOOLEAN : rowData.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    case Cell.CELL_TYPE_NUMERIC : rowData.add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case Cell.CELL_TYPE_STRING : rowData.add(String.valueOf(cell.getStringCellValue()));
                        break;

                }
            }
            table.add(rowData);
        }

        return table;
    }

    public void writeToFile(String fileName, List<String> results)
    {
        try {
            FileInputStream file = new FileInputStream(new File(fileName));

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);

            Cell cell = null;
            for (int i = 0; i < results.size(); i++)
            {
                cell = sheet.getRow(i).getCell(2);
                cell.setCellValue(results.get(i));

            }
            file.close();

            FileOutputStream updateFile = new FileOutputStream(new File("Update" + fileName));
            workbook.write(updateFile);
            updateFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
