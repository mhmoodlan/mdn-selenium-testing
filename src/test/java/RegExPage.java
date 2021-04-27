import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.Random;
import java.lang.Math;


class RegExPage extends PageBase {
    private By inputBy = By.tagName("input");
    private By checkButtonBy = By.tagName("button");

    public RegExPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions");
    }    
    
    public String getValidRandomPhoneNumber(int seed) {
        
        Random rd = new Random(seed);
        String number = "";
        for(int i = 0; i < 10; i++) {
            number += Math.abs(rd.nextInt()%10);
            if(i==2 || i == 5) number += "-";
        }
        return number;
    } 

    public boolean checkPhoneNumberDemo(String number) {
        this.driver.switchTo().frame("frame_Using_special_characters_to_verify_input");
        boolean result = false;
        int attempts = 0;
        WebElement input = this.waitAndReturnElement(inputBy);
        input.sendKeys(number);
        while(attempts < 2) {
            try {
                
                this.waitAndReturnElement(checkButtonBy).click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        
        return result;
    }
}