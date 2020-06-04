package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.util.List;

public class LandingPage {

    protected static WebDriver driver;

    private By byDesiredAmount = By.name("desiredAmount");
    private By byLoanPurpose = By.cssSelector("[data-auto='dropLoanPurpose']");
    private By bySubmit = By.cssSelector("[data-auto='CheckYourRate']");

    public LandingPage (WebDriver driver) {
        this.driver = driver;
    }

    public InfoPage submit (Integer amount, String purpose) {
        driver.findElement(byDesiredAmount).sendKeys(amount.toString());
        Select select = new Select(driver.findElement(byLoanPurpose));
        select.selectByVisibleText(purpose);
        driver.findElement(bySubmit).click();
        return new InfoPage(driver);
    }

    public InfoPage submitWithRandomPurpose (Integer amount) {
        driver.findElement(byDesiredAmount).sendKeys(amount.toString());
        Select select = new Select(driver.findElement(byLoanPurpose));
        List<WebElement> options = select.getOptions();
        select.selectByIndex(Utils.randomInt(options.size() - 1));
        driver.findElement(bySubmit).click();
        return new InfoPage(driver);
    }
}
