package romangaznavi.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import romangaznavi.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;

	

	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(names->names.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		return match;
	}

}
