package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Set;

public class ItemPage {
    public WebDriver driver;
    public static By addToCartButton = By.xpath("//span[contains(text() ,'Add to cart')]");
    public static By cartIcon = By.xpath("//span[@class='gh-cart__icon']//span");

    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addItemToCart() {
        Set<String> windowHandles = driver.getWindowHandles();
        ArrayList<String> handlesList = new ArrayList<>(windowHandles);
        // Switch to the last window
        String lastWindowHandle = handlesList.get(handlesList.size() - 1);
        driver.switchTo().window(lastWindowHandle);
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        driver.findElement(addToCartButton).click();
    }

    public String getCartItemCount() {
        WebElement cartElement = driver.findElement(cartIcon);
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
        return cartElement.getText();
    }
}