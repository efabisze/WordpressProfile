package support;

import org.openqa.selenium.WebDriver;

public class Manager {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static void cleanThreadDriverSession(){
        webDriver.remove();
    }

}
