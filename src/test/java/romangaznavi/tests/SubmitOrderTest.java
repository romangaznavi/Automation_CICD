package romangaznavi.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import romangaznavi.TestComponents.BaseTest;
import romangaznavi.pageobjects.CartPage;
import romangaznavi.pageobjects.CheckoutPage;
import romangaznavi.pageobjects.ConfirmationPage;
import romangaznavi.pageobjects.OrderPage;
import romangaznavi.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups= {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage = productcatalogue.goToCartPage();

		Boolean match = cartpage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		confirmationpage.getConfirmationMessage();
		String confirmMsg = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productcatalogue = landingpage.loginApplication("roman1@gmail.com", "Roman@123");
		OrderPage orderPage = productcatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "roman1@gmail.com");
//		map.put("password", "Roman@123");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "aajj123@gmail.com");
//		map1.put("password", "Password@123");
//		map1.put("product", "ADIDA ORIGINAL");
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//romangaznavi//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
