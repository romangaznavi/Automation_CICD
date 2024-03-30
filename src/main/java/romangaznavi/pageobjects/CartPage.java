package romangaznavi.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import romangaznavi.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> productTitles;
	

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = productTitles.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		return match;
	}
	
	public CheckoutPage goToCheckOut() {
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
	

}
