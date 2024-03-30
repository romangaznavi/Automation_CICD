package romangaznavi.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import romangaznavi.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="hero-primary")
	WebElement confirmMsg;
	
	
	public String getConfirmationMessage() {
		return confirmMsg.getText();
	}
}
