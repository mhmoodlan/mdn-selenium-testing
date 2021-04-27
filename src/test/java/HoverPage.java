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

import java.time.Duration;


class HoverPage extends PageBase {
    private By signInBy = By.className("signin-link");
    private Actions actions;

    public HoverPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://developer.mozilla.org/en-US/docs/Web/CSS/:hover");
        this.actions = new Actions(this.driver);
    }    
    
    public String hoverAndReturnCssValue(String cssValue) {
        WebElement hoverEle = this.waitAndReturnElement(signInBy);
        this.actions.moveToElement(hoverEle, (53 / 2), 17 / 2).build().perform();
        return hoverEle.getCssValue(cssValue);
    } 
}