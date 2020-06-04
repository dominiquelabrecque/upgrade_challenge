package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CredentialPage {

    protected static WebDriver driver;

    private By byEmailAddress = By.name("username");
    private By byPassword = By.name("password");
    private By byAgreements = By.cssSelector("[name='agreements'] + div");
    private By bySubmit = By.cssSelector("[type='submit']");

    public CredentialPage(WebDriver driver) {
        this.driver = driver;
    }

    public OfferPage createAccount(String email, String password) {
        return fillForm(email, password, true);
    }

    public OfferPage login(String email, String password) {
        return fillForm(email, password, false);
    }

    private OfferPage fillForm(String email, String password, boolean isNew) {
        driver.findElement(byEmailAddress).sendKeys(email);
        driver.findElement(byPassword).sendKeys(password);
        if (isNew) {
            driver.findElement(byAgreements).click();
        }
        driver.findElement(bySubmit).click();
        return new OfferPage(driver);
    }
}
