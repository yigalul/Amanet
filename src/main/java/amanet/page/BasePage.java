package amanet.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class BasePage {
    protected static final int TIMEOUT_IN_SECONDS = 30;
    protected Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void mouseOver(WebElement element){
        Actions action = new Actions(driver);
        waitForElementToBeVisible(element);
        action.moveToElement(element).perform();
    }



    protected void click(WebElement el) {
        waitForElementToBeVisible(el);
        el.click();
    }

    protected boolean verifyElement(WebElement el) {
        waitForElementToBeVisible(el);
        return el.isDisplayed();
    }

    protected List<String> getText(WebElement el) {
        waitForElementToBeVisible(el);
        String text = el.getText();
        if (text != null)
            return Arrays.asList(text.split("\n"));
        else{
            logger.error("Text is null");
        }
        return null;
    }

    protected void waitForElementToBeVisible(WebElement el) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOf(el));
    }
}
