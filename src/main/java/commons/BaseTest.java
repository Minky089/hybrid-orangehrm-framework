package commons;

import commons.constant.Browsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Random;

public class BaseTest {

    protected WebDriver getDriverBrowser(String browserName) {
        Browsers browser = Browsers.valueOf(browserName.toUpperCase());
        WebDriver driver;

        switch (browser) {
            case CHROME -> driver = new ChromeDriver();
            case FIREFOX -> driver = new FirefoxDriver();
            case EDGE -> driver = new EdgeDriver();
            default -> throw new RuntimeException("Browser name is not valid.");
        }

        driver.get("http://127.0.0.1:8082/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }

    protected int generateRandomNumber() {
        return new Random().nextInt(99999);
    }
}
