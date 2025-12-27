package ChromeDevToolCDP;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v140.emulation.Emulation;

public class MobileEmulatorTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeDriver driver = new ChromeDriver(); // we should use chromedriver when want to work with devtool
		DevTools devTools = driver.getDevTools();

		devTools.createSession();

		// send commands to CDP -> CDP methods will invoke to get access to chrome dev
		// tools
		// https://chromedevtools.github.io/devtools-protocol/tot/Emulation/ already
		// build In methods for API call
		devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty())); //Emulation.setDeviceMetricsOverrid got from CDP git hub  https://chromedevtools.github.io/devtools-protocol/tot/Emulation/ already
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector(".navbar-toggler")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Library")).click();	
		//Network.getRequestPostData
		
	}

}
