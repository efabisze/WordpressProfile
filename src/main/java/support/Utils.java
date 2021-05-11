package support;

import java.util.Random;
import org.openqa.selenium.WebDriver;

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
