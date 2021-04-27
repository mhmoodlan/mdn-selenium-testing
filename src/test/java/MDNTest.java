
import java.util.logging.Level;
import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;
import java.util.HashMap;

public class MDNTest {
    public WebDriver driver;
    
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        LoggingPreferences logPrefs = new LoggingPreferences();
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(caps);
        driver.manage().window().maximize();
    }
    
    @Test
    public void testLogin() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.openLogin();
        DashboardPage dashboardPage = loginPage.login("mdn.selenium@gmail.com","mdn-selenium-2021");
        
        System.out.println(dashboardPage.getMainCardTitle());
        Assert.assertTrue(dashboardPage.getMainCardTitle().contains("Resources for developers, by developers."));
        
        UserAccountPage userPage = dashboardPage.openUserAccount();
        userPage.changeLanguage("ar");
        Assert.assertTrue(userPage.getSuccessMsg().contains("Yay! Updated settings successfully saved."));

        userPage.logout();
        Assert.assertTrue(userPage.getLogoutMsg().contains("You have not signed in"));
    }

    @Test
    public void testMultipleStaticPages() {
        MainPage mainPage = new MainPage(this.driver);
        AbstractMap<String, String> staticPages = mainPage.getStaticPages();
        for(AbstractMap.Entry<String, String> entry : staticPages.entrySet()) {
            Assert.assertTrue(mainPage.getPageHeaderText(entry.getKey()).contains(entry.getValue()));
        }
    }

    @Test
    public void testBackButton() {
        MainPage mainPage = new MainPage(this.driver);
        AbstractMap<String, String> staticPages = mainPage.getStaticPages();
        String mainPageTitle = this.driver.getTitle();
        for(AbstractMap.Entry<String, String> entry : staticPages.entrySet()) {
            mainPage.goToPage(entry.getKey());
            mainPage.getPageHeaderText(entry.getKey());
            Assert.assertTrue(mainPage.getPageHeaderText(entry.getKey()).contains(entry.getValue()));
            mainPage.goBack();
            Assert.assertTrue(this.driver.getTitle().contains(mainPageTitle));
            break;
        }
        
    }
    
    @Test
    public void testDragAndDrop() {
        HoverPage page = new HoverPage(this.driver);
        Assert.assertTrue(page.hoverAndReturnCssValue("text-decoration").equals("underline solid rgb(33, 33, 33)"));
    }

    @Test
    public void testRegExPage() {
        RegExPage page = new RegExPage(this.driver);
        String number = page.getValidRandomPhoneNumber(43);
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("window.scrollBy(0,10000)");
        boolean result = page.checkPhoneNumberDemo(number);
        
        Assert.assertTrue(result);
    }

    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
