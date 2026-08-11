package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void openPageUrl(String url) {
        driver.get(url);
    }

    protected void backToPage() {
        driver.navigate().back();
    }

    protected void forwardToPage() {
        driver.navigate().forward();
    }

    protected void refreshCurrentPage() {
        driver.navigate().refresh();
    }

    protected void clickToElement(String locator) {
        getElement(locator).click();
    }

    protected void sendkeyToElement(String locator, String value) {
        getElement(locator).sendKeys(value);
    }

    protected void selectItemInDropdown(String locator, String textItem) {
        new Select(getElement(locator)).selectByVisibleText(textItem);
    }

    protected void checkToCheckboxRadio(String locator) {
        if(!getElement(locator).isSelected()) {
            getElement(locator).click();
        }
    }

    protected void uncheckToCheckboxRadio(String locator) {
        if(getElement(locator).isSelected()) {
            getElement(locator).click();
        }
    }

    protected String getSelectedItemInDropdown(String locator) {
        return new Select(getElement(locator)).getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(String locator) {
        return new Select(getElement(locator)).isMultiple();
    }

    protected void selectItemInCustomDropdown(String locator, String childItemLocator, String expectedItem) {
        getElement(locator).click();
        sleepInSeconds(2);

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

        sleepInSeconds(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    protected String getElementText(String locator) {
        return getElement(locator).getText();
    }

    protected String getElementAttribute(String locator, String attributeName) {
        return getElement(locator).getAttribute(attributeName);
    }

    protected String getElementCSSValue(String locator, String propertyName) {
        return getElement(locator).getCssValue(propertyName);
    }

    protected String getHexColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).toString();
    }

    protected int getListElementSize(String locator) {
        return getListElement(locator).size();
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getPageUrl() {
        return driver.getCurrentUrl();
    }

    protected String getPageSource() {
        return driver.getPageSource();
    }

    protected Alert waitAlertPresence() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert() {
        waitAlertPresence().accept();
    }

    protected void cancelAlert() {
        waitAlertPresence().dismiss();
    }

    protected void sendkeyToAlert(String value) {
        waitAlertPresence().sendKeys(value);
    }

    protected String getAlertText() {
        return waitAlertPresence().getText();
    }

    protected boolean isElementDisplayed(String locator) {
        return getElement(locator).isDisplayed();
    }

    protected boolean isElementSelected(String locator) {
        return getElement(locator).isSelected();
    }

    protected boolean isElementEnabled(String locator) {
        return getElement(locator).isEnabled();
    }

    protected void switchToWindowByID(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    protected void switchToWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    protected void closeAllWindowsWithoutParent(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    protected void switchToIframe(String locator) {
        driver.switchTo().frame(getElement(locator));
    }

    protected void switchToDefaultPage() {
        driver.switchTo().defaultContent();
    }

    protected void hoverToElement( String locator) {
        new Actions(driver).moveToElement(getElement(locator)).perform();
    }

    protected void clickAndHoldToElement( String locator) {
        new Actions(driver).clickAndHold(getElement(locator)).perform();
    }

    protected void doubleClickToElement( String locator) {
        new Actions(driver).doubleClick(getElement(locator)).perform();
    }

    protected void rightClickToElement( String locator) {
        new Actions(driver).contextClick(getElement(locator)).perform();
    }

    protected void dragAndDropElement( String srcLocator, String tgtLocator) {
        new Actions(driver).dragAndDrop(getElement(srcLocator), getElement(tgtLocator)).perform();
    }

    protected void pressKeyToElement( String locator, String key) {
        new Actions(driver).sendKeys(getElement(locator), key).perform();
    }

    protected void scrollToElement(String locator) {
        new Actions(driver).scrollToElement(getElement(locator)).perform();
    }

    protected void scrollToBottomPageByJS() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void navigateToUrlByJS(String url) {
        ((JavascriptExecutor) driver).executeScript("window.location =  '" + url + "'");
        sleepInSeconds(3);
    }

    protected void highlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    protected void clickToElementByJS(String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(locator));
        sleepInSeconds(3);
    }

    protected void scrollToElementOnTopByJS(String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    protected void scrollToElementOnDownByJS(String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    protected void removeAttributeInDOM(String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    protected void sendkeyToElementByJS(String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "');", getElement(locator));
    }

    protected String getAttributeInDOMByJS(String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    protected String getElementValidationMessage(String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    protected boolean isImageLoaded(String locator) {
        Object result = ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(locator));

        return result != null && (boolean) result;
    }

    protected void waitForElementVisible(String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    protected void waitForElementPresence(String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

    protected void waitForElementInvisible(String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    protected void waitForElementClickable(String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    protected WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    protected List<WebElement> getListElement(String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }

    protected void sleepInSeconds(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
