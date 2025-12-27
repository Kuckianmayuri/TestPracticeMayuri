package seleniumLiveProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.abstactComponent;

public class confirmationPage extends abstactComponent{
        WebDriver driver;
        public confirmationPage(WebDriver driver) {
    		super(driver);
    		this.driver = driver;
    		PageFactory.initElements(driver, this);
    	}

        @FindBy(css = ".hero-primary")
        WebElement confirmationMessage;
        
        public String getConfirmationMessage() {
        	return confirmationMessage.getText();
        }
}
