package commons;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageGeneratorManager {
    public static <T extends BasePage> T getPage(Class<T> pageClass, WebDriver driver) {
        try {
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            return  constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Can not init Page Object class: " + pageClass.getSimpleName(), e);
        }
    }
}
