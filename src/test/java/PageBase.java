import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    protected By bodyBy = By.tagName("body");
    
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected void waitUntilTitleIs(String title){
        this.wait.until(ExpectedConditions.	titleContains(title));
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    } 
    
    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(bodyBy);
        return bodyElement.getText();
    }
   
    public String getElementText(By locator) {
        WebElement element = this.waitAndReturnElement(locator);
        return element.getText();
    }

    public String getPageHeaderText(String href) {
        By pageLocator = By.xpath("//ul[@class='link-list-mdn']/li/a[@href='"+ href +"']");
        this.waitUntilTitleIs(this.driver.getTitle());
        this.waitAndReturnElement(pageLocator).click();
        return this.getElementText(By.xpath("//article[@class='main-page-content']/h1"));
    }

    public void goToPage(String url) {
        this.driver.navigate().to("https://developer.mozilla.org/"+url);
    }

    public void goBack() {
        this.driver.navigate().back();
    }
}