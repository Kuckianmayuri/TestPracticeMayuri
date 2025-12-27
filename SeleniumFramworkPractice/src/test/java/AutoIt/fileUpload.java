package AutoIt;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

public class fileUpload {

	public static void main(String[] args) throws InterruptedException, IOException {

// TODO Auto-generated method stub

		String downloadPath = System.getProperty("user.dir"); //will have path where the project is saved, seleniumFramworkPractice is saved in C:\Users\asus\git\repository\SeleniumFramworkPractice
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://altoconvertpdftojpg.com/");
		driver.findElement(By.cssSelector("[class*='btn--choose']")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\rahul\\Documents\\check\\fileupload.exe"); // fileupload is compliled file
																							// of autoIt script
																							// (currently I dont
																							// have complied file, since
																							// autoIt is not correctly
																							// installed in my system)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*='medium']")));
		driver.findElement(By.cssSelector("button[class*='medium']")).click(); // connvert button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Download Now")));
		driver.findElement(By.linkText("Download Now")).click();
		Thread.sleep(5000);
		// file to check
		File f = new File(downloadPath + "/converted.zip"); //C:\Users\asus\git\repository\SeleniumFramworkPractice\converted.zip
		if (f.exists()) {
			Assert.assertTrue(f.exists());
			if (f.delete())
				System.out.println("file deleted");
		}
	}

}
