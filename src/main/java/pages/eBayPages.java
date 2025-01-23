package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class eBayPages {
    WebDriver driver;
    public static By searchBox = By.id("gh-ac");
    public static By searchButton = By.id("gh-search-btn");
    public static By firstItemLink = By.xpath("//li[2]//div[@class = 's-item__info clearfix']/a");

    public eBayPages(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForItem(String item) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        driver.findElement(searchBox).sendKeys(item);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        driver.findElement(searchButton).click();
    }

    public void selectFirstItem() {
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstItemLink));
        driver.findElement(firstItemLink).click();
    }
}