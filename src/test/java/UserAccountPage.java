import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class UserAccountPage extends PageBase {

    private By selectLanguageBy = By.name("locale");
    private By updateLanguageButtonBy = By.xpath("//form[@class='settings-form']/button");
    private By successMsgBy = By.className("success");
    private By logoutMsgBy = By.xpath("//main[@id='content']/h2");
    private By userAvatarButtonBy = By.id("user-avatar-menu");
    private By logoutBy = By.xpath("//div[@id='root']/div/header/div/div/div/ul/li/form/button");

    public UserAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMsg() {
        return this.getElementText(successMsgBy);
    }

    public String getLogoutMsg() {
        return this.getElementText(logoutMsgBy);
    }

    public void changeLanguage(String newLanguage) {
        Select select = new Select(this.waitAndReturnElement(selectLanguageBy));
        select.selectByValue(newLanguage);
        this.waitAndReturnElement(updateLanguageButtonBy).click();
    }

    public void logout() {
        this.waitAndReturnElement(userAvatarButtonBy).click();
        this.waitAndReturnElement(logoutBy).click();
    }
}