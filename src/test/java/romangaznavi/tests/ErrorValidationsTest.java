package romangaznavi.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import romangaznavi.TestComponents.BaseTest;
import romangaznavi.TestComponents.Retry;
import romangaznavi.pageobjects.CartPage;
import romangaznavi.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {
		landingpage.loginApplication("roman1@gmail.com", "Roman11123");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingpage.loginApplication("aajj123@gmail.com", "Password@123");
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay("ZARA COAT 3");
		Assert.assertFalse(match);
	}
}
