package support;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import support.Manager.*;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Utils {
    WebDriver driver;

    public static String genRandom(int length){
        String alphabet =
                new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
        int n = alphabet.length(); //10

        String result = new String();
        Random r = new Random(); //11

        for (int i = 0; i < length; i++) //12
            result = result + alphabet.charAt(r.nextInt(n)); //13

        return result;
    }
}
