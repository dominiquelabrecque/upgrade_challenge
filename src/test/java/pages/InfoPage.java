package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InfoPage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    private By byFirstName = By.name("borrowerFirstName");
    private By byLastName = By.name("borrowerLastName");
    private By byHomeAddress = By.name("borrowerStreet");
    private By byDOB = By.name("borrowerDateOfBirth");
    private By byContinue = By.cssSelector("[data-auto='continuePersonalInfo']");

    private By byGeoSuggest = By.className("geosuggest__item");

    public InfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public IncomePage submit
            (
                String firstName,
                String lastName,
                String homeAddress,
                String dateOfBirth
            )
    {
        driver.findElement(byFirstName).sendKeys(firstName);
        driver.findElement(byLastName).sendKeys(lastName);
        driver.findElement(byHomeAddress).sendKeys(homeAddress);

        List<WebElement> geoSuggestions = driver.findElements(byGeoSuggest);
        WebElement firstSuggestion = wait.until(ExpectedConditions.visibilityOfAllElements(geoSuggestions)).get(0);
        wait.until(ExpectedConditions.elementToBeClickable(firstSuggestion)).click();

        driver.findElement(byDOB).sendKeys(dateOfBirth);
        driver.findElement(byContinue).click();
        return new IncomePage(driver);
    }
}
