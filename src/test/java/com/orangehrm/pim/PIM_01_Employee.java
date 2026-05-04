package com.orangehrm.pim;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Log4j2
public class PIM_01_Employee {
private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @Test
    public void User_01_Register() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
