package ChromeDevToolCDP;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class SetgeoLocation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		Map<String, Object> coordinates = new HashMap();
//		//find in google map latitude, longitude, accuracy .. below example is for spanish using https://earth.google.com/web/
//		coordinates.put("latitude", 40);
//		coordinates.put("longitude", 3);
//		coordinates.put("accuracy", 1);
		
		//for india
		coordinates.put("latitude", 17);
		coordinates.put("longitude", 78);
		coordinates.put("accuracy", 1);
		
		driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		
		driver.get("http://google.com");
		driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
	 driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
	 String title = driver.findElement(By.cssSelector(".default-ltr-iqcdef-cache-4b5q1b.eb5pmcc0")).getText();
	 System.out.println(title);
	}

}
