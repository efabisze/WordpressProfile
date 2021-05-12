import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class MainBeforeAfter {

    public static WebDriver driver;
    public static  String testUrl = "https://wordpress.com/me";
    String user = "efabisze@gmail.com";
    String password = "E&kgC2^fBvAi94g";

    public static WebDriver getInstance()
    {
        if (System.getProperty("webdriver.chrome.driver") == null)
        {
            String path = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", path+"\\src\\test\\resources\\chromedriver.exe");
        }
        if (System.getProperty("test.url") != null)
        {
            testUrl = System.getProperty("test.url");
        }
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        return driver = new ChromeDriver(options);

    }
    @BeforeMethod
    public void beforeMethod() throws InterruptedException
    {
        driver = MainBeforeAfter.getInstance();
        driver.get(testUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
         enterLogin();
    }

    protected void enterLogin()
    {
        Reporter.log("Open 'Login' page: " + testUrl, true);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("usernameOrEmail"))));

        driver.findElement(By.id("usernameOrEmail")).sendKeys(user);
        driver.findElement(By.cssSelector(".button.form-button.is-primary")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.name("password"))));

        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.form-button.is-primary")).click();
    }

    protected WebDriver getDriver()
    {
        return driver;
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException
    {
        driver.close();

        try
        {
            driver.quit();
        }
        catch (Exception e)
        {
            System.out.println("Unable to close browser after login header exception caught: " + e);
        }

    }

}