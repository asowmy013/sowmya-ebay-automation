import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.MediaEntityBuilder;
import pages.ItemPage;
import pages.ScreenshotUtil;
import pages.eBayPages;


public class TestEbay {
    WebDriver driver;
    eBayPages homePage;
    ItemPage itemPage;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            homePage = new eBayPages(driver);
            itemPage = new ItemPage(driver);

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            test = extent.createTest("Ebay Add to Cart Test");
        } catch (Exception e) {
            System.out.println("Error setting up WebDriver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testAddItemToCart() {
        try {
            driver.get("https://www.ebay.com");
            test.info("Navigated to eBay");

            homePage.searchForItem("book");
            test.info("Searched for 'book'");

            homePage.selectFirstItem();
            test.info("Selected the first item");

            itemPage.addItemToCart();
            test.info("Added item to cart");

            String cartItemCount = itemPage.getCartItemCount();
            test.info("Cart Item Count: " + cartItemCount, MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "CartItemCount")).build());

            Assert.assertTrue(cartItemCount.contains("1"), "Item count should be 1");
            test.pass("Item successfully added to cart and cart count updated");
        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "Error")).build());
            System.out.println("Error during test execution: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
            extent.flush();
        } catch (Exception e) {
            System.out.println("Error during teardown: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

