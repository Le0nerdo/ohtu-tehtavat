package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        // Alkuperäinen testi
        /*
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        */

        // bullets 2 ja 3
        /*
        Random r = new Random();
        String user = "asfsad"+r.nextInt(12323);
        WebElement e = driver.findElement(By.linkText("register new user"));
        e.click();

        sleep(2);

        e = driver.findElement(By.name("username"));
        e.sendKeys(user);
        e = driver.findElement(By.name("password"));
        e.sendKeys(user);
        e = driver.findElement(By.name("passwordConfirmation"));
        e.sendKeys(user);

        e = driver.findElement(By.name("signup"));
        e.submit();

        sleep(2);

        e = driver.findElement(By.linkText("continue to application mainpage"));
        e.click();
        
        sleep(2);
        e = driver.findElement(By.linkText("logout"));
        e.click();

        sleep(2);

        e = driver.findElement(By.linkText("login"));
        */

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("asd");
        element = driver.findElement(By.name("password"));
        element.sendKeys("das");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(2);
        element = driver.findElement(By.name("username"));

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
