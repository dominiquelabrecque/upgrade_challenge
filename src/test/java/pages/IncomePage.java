package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IncomePage {

    protected static WebDriver driver;

    private By byIndividualIncome = By.name("borrowerIncome");
    private By byAdditionalIncome = By.name("borrowerAdditionalIncome");
    private By byContinue = By.cssSelector("[type='submit']");


    public IncomePage(WebDriver driver) {
        this.driver = driver;
    }

    public CredentialPage submit(Integer individualIncome, Integer additionalIncome) {
        driver.findElement(byIndividualIncome).sendKeys(individualIncome.toString());
        driver.findElement(byAdditionalIncome).sendKeys(additionalIncome.toString());
        driver.findElement(byContinue).click();
        if (driver.getCurrentUrl().endsWith("step=income")) {
            driver.findElement(byContinue).click();
        }
        return new CredentialPage(driver);
    }
}
