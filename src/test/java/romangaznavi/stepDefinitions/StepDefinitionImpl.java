package romangaznavi.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import romangaznavi.TestComponents.BaseTest;
import romangaznavi.pageobjects.CartPage;
import romangaznavi.pageobjects.CheckoutPage;
import romangaznavi.pageobjects.ConfirmationPage;
import romangaznavi.pageobjects.LandingPage;
import romangaznavi.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public CartPage cartpage;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce webpage")
	public void I_landed_on_Ecommerce_webpage() throws IOException {
		landingpage = launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and passowrd (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productcatalogue = landingpage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void CHeckout_and_submit_the_order(String productName) {
		cartpage = productcatalogue.goToCartPage();

		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.selectCountry("india");
		confirmationpage = checkoutpage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void message_is_displayed_on_confirmationPage(String string) {
		String confirmMsg = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string) {
		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.close();
	}
	
}
