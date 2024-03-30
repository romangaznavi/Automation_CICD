package romangaznavi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import romangaznavi.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	@FindBy(css = "[routerlink='/dashboard/cart']")
	WebElement addToCartClick;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	// This is By locator, because it is pointing to a specific object in the page.
	By productsBy = By.cssSelector(".mb-3");
	By productNam = By.cssSelector("b");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		addToCartClick.click();
	}

	public List<WebElement> getCartProducts() {
		return cartProducts;
	}

	public boolean verifyProduct(String productName) {
		Boolean match = cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		return match;
	}
	
	
}
