package ExcelDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;


public class dataDriven {
	public ArrayList<String> getData(String testcase) throws IOException {
		ArrayList<String> a = new ArrayList<String>();

		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("C:\\Users\\asus\\OneDrive\\Desktop\\demodata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row firstrow = rows.next(); // got access to first row
				Iterator<Cell> ce = firstrow.cellIterator(); // row is collection of cells
				int k = 0;
				int column = 0;
				while (ce.hasNext())// checks next cell present or not
				{
					Cell value = ce.next(); // moves to next cell
					if (value.getStringCellValue().equalsIgnoreCase(testcase)) {
						// Desired column
						column = k;
					}
					k++;
				}
				System.out.println("column = "+column);

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcase)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if(c.getCellType() == CellType.STRING) {
							a.add(cv.next().getStringCellValue());
							}
							else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
				}

			}
		}
		return a;
	}

	// Identify testcase column by scanning entire row
	// Once column is identified then scan entore column to identify purchase
	// testcase row
	// after you grab purchase testcase row = pull data of the row
	public static void main(String[] args) throws IOException {
         dataDriven d = new dataDriven();
         ArrayList<String> data = d.getData("Purchase");
         for(String s: data) {
        	 System.out.println(s);
         }
	}

}
