package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;

public class OfferPage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    private By byLoanAmount = By.cssSelector("[data-auto='userLoanAmount']");
    private By byMonthlyPayment = By.cssSelector("[data-auto='defaultMonthlyPayment']");
    private By byLoanTerm = By.cssSelector("[data-auto='defaultLoanTerm']");
    private By byInterestRate = By.cssSelector("[data-auto='defaultLoanInterestRate']");
    private By byAPR = By.cssSelector("[data-auto='defaultMoreInfoAPR']");
    private By byMenuNav = By.className("header-nav");
    private By bySignoutLink = By.linkText("Sign Out");

    public OfferPage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public HashMap<String, String> getLoanInfo () {
        HashMap<String, String> loanInfo = new HashMap<>();
        loanInfo.put("amount", wait.until(ExpectedConditions.visibilityOfElementLocated(byLoanAmount)).getText());
        loanInfo.put("monthly", driver.findElement(byMonthlyPayment).getText());
        loanInfo.put("term", driver.findElement(byLoanTerm).getText());
        loanInfo.put("rate", driver.findElement(byInterestRate).getText());
        loanInfo.put("apr", driver.findElement(byAPR).getText());
        return loanInfo;
    }

    public void validateLoanInfo (HashMap<String, String> loanInfo) {
        String actualAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(byLoanAmount)).getText();
        String actualMonthly = driver.findElement(byMonthlyPayment).getText();
        String actualTerm = driver.findElement(byLoanTerm).getText();
        String actualRate = driver.findElement(byInterestRate).getText();
        String actualARP = driver.findElement(byAPR).getText();

        Assert.assertEquals(actualAmount, loanInfo.get("amount"));
        Assert.assertEquals(actualMonthly, loanInfo.get("monthly"));
        Assert.assertEquals(actualTerm, loanInfo.get("term"));
        Assert.assertEquals(actualRate, loanInfo.get("rate"));
        Assert.assertEquals(actualARP, loanInfo.get("apr"));
    }

    public void signOut () {
        wait.until(ExpectedConditions.elementToBeClickable(byMenuNav)).click();
        driver.findElement(bySignoutLink).click();
    }

}
