import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UploadDownload {

	public static void main(String[] args) throws IOException, InterruptedException {

		String fruitName = "Apple";
		String fileName = "C:\\Users\\asus\\Downloads\\download.xlsx";
		String updatedValue = "808";
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		driver.manage().window().maximize();

		// download
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement downloadBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("downloadButton")));
		downloadBtn.click();
		
		//Take latest file
		Thread.sleep(3000);  // small wait for download start

		String downloadFolder = "C:\\Users\\asus\\Downloads";
		java.io.File dir = new java.io.File(downloadFolder);

		java.io.File latestFile = null;
		long lastMod = Long.MIN_VALUE;

		for (java.io.File file : dir.listFiles()) {
		    if (file.getName().startsWith("download") && file.getName().endsWith(".xlsx")) {
		        if (file.lastModified() > lastMod) {
		            latestFile = file;
		            lastMod = file.lastModified();
		        }
		    }
		}

		String fileName1 = latestFile.getAbsolutePath();
		System.out.println("Using file: " + fileName1);
		
		// Edit excel
		int col = getColumnNumber(fileName1, "Price");
		int row = getRowNumber(fileName1, fruitName);
		Assert.assertTrue(updateCell(fileName1, row, col, updatedValue));

		// upload
		WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys(fileName1); // upload button, path will be send fom backend
																		// when you send
		// path where it will be downloaded

		// wait for success message to show up and wait for disapper
		By toastLocator = By.cssSelector(".Toastify__toast-body div:nth-child(2)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
		String toastText = driver.findElement(toastLocator).getText();
		Assert.assertEquals("Updated Excel Data Successfully.", toastText);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
		// Verify updated excel data showing in the web table
		String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']")).getText();
		System.out.println(actualPrice);
		Assert.assertEquals(updatedValue, actualPrice);
	}

	private static boolean updateCell(String fileName, int row, int col, String updatedValue) throws IOException {
		ArrayList<String> a = new ArrayList<String>();

		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row rowField = sheet.getRow(row - 1); // getrow start with 0, hence we use row-1
		Cell cellField = rowField.getCell(col);
		cellField.setCellValue(updatedValue);
		// write to the file & save the excel
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fis.close();
		return true;
	}

	private static int getRowNumber(String fileName, String text) throws IOException {
		ArrayList<String> a = new ArrayList<String>();
		int k = 1;
		int rowIndex = -1;

		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();

			while (cells.hasNext()) {
				Cell cell = cells.next();
				if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(text)) {
					rowIndex = k;
				}
			}
			k++;
		}

		return rowIndex;
	}

	private static int getColumnNumber(String fileName, String colName) throws IOException {
		ArrayList<String> a = new ArrayList<String>();

		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		Row firstrow = rows.next(); // got access to first row
		Iterator<Cell> cells = firstrow.cellIterator(); // row is collection of cells
		int k = 0;
		int column = 0;
		while (cells.hasNext())// checks next cell present or not
		{
			Cell value = cells.next(); // moves to next cell
			if (value.getStringCellValue().equalsIgnoreCase(colName)) {
				// Desired column
				column = k;
			}
			k++;
		}
		System.out.println("column = " + column);
		return column;
	}
}
