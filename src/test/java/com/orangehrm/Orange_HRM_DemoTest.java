package com.orangehrm;

import commons.BaseTest;
import commons.PageGeneratorManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pageobjects.DashboardPageObject;
import pageobjects.LoginPageObject;

@Log4j2
public class Orange_HRM_DemoTest extends BaseTest {
    private final String username = "admin";
    private final String password = "Minky089@";
    private WebDriver driver;
    private DashboardPageObject dashboardPage;
    private LoginPageObject loginPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(@Optional("CHROME") String browserName) {
        driver = getDriverBrowser(browserName);
        loginPage = PageGeneratorManager.getPage(LoginPageObject.class, driver);
        dashboardPage = loginPage.loginToSystem(username, password);
    }

    @Test
    public void Employee_01_NewEmployee() throws InterruptedException {
    }

    @Test
    public void Employee_02_UploadAvatar() {
    }

    @Test
    public void Employee_03_PersonalDetails() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
