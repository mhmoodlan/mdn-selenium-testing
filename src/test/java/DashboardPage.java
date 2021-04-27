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


class DashboardPage extends PageBase {

    private By mainCardBy = By.xpath("//main[@id='content']/div/div/h1");
    private By userAvatarButtonBy = By.id("user-avatar-menu");
    private By userProfileBy = By.xpath("//div[@id='root']/div/header/div/div/div/ul/li/a");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }    
    
    public String getMainCardTitle(){
        return this.waitAndReturnElement(mainCardBy).getText();
    }

    public UserAccountPage openUserAccount() {
        this.waitAndReturnElement(userAvatarButtonBy).click();
        this.waitAndReturnElement(userProfileBy).click();
        return new UserAccountPage(this.driver);
    }
}