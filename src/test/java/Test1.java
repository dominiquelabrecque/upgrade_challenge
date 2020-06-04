import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import utils.Utils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Test1 {

    public WebDriver driver;
    public String baseUrl = "https://www.credify.tech/funnel/nonDMFunnel";
    public String loginUrl = "https://www.credify.tech/portal/login";


    @BeforeClass
    public void setUp () {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(baseUrl);
    }


    @Test
    public void test () {

        String email = "candidate-" + Utils.nowStamp() + "@upgrade-challenge.com";
        String password = "Password" + Utils.randomInt(100, 999);
        int loanAmount = Utils.randomInt(2000, 8000);
        String firstName = "sunny";
        String lastName = "day";
        String homeAddress = "123";
        String dob = "12251999";
        int income = Utils.randomInt(120000, 300000);
        int additionalIncome = Utils.randomInt(5000, 9999);

        LandingPage landingPage = new LandingPage(driver);
        InfoPage infoPage = landingPage.submitWithRandomPurpose(loanAmount);
        IncomePage incomePage = infoPage.submit(firstName, lastName, homeAddress, dob);
        CredentialPage credentialPage = incomePage.submit(income, additionalIncome);
        OfferPage offerPage = credentialPage.createAccount(email, password);
        HashMap<String, String> loanInfo = offerPage.getLoanInfo();
        System.out.println(loanInfo);
        offerPage.signOut();

        driver.navigate().to(loginUrl);
        credentialPage.login(email, password);
        offerPage.validateLoanInfo(loanInfo);

    }


    @AfterClass
    public void tearDown () {
//        driver.quit();
    }

}
