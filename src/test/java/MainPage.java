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

import java.util.HashMap;

class MainPage extends PageBase {

    private By loginMenuButtonBy = By.className("signin-link");
    // private By loginViaGoogleButtonBy = By.className("auth-buttons");
    private By loginViaGoogleButtonBy = By.xpath("//a[@href='/users/google/login/?next=%2Fen-US%2F&yarisignup=1']");

    private HashMap<String, String> staticPages;

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://developer.mozilla.org/en-US/");
        this.staticPages = new HashMap<String, String>();
        this.staticPages.put("/en-US/docs/Web", "Web technology for developers");
        this.staticPages.put("/en-US/docs/MDN/About", "About MDN Web Docs");
        this.staticPages.put("/en-US/docs/Learn", "Learn web development");
        this.staticPages.put("/en-US/docs/MDN/Feedback", "Send feedback about MDN Web Docs");
    }    

    public HashMap getStaticPages() {
        return this.staticPages;
    }
    
    public LoginPage openLogin() {
        this.waitAndReturnElement(loginMenuButtonBy).click();
        this.waitAndReturnElement(loginViaGoogleButtonBy).click();
        return new LoginPage(this.driver);
    }
}