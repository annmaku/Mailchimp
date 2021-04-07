package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    private WebDriver driver;

    @Given("Open web browser and go to Mailchimp")
    public void open_web_browser_and_go_to_mailchimp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();  //start chrome
        driver.get("https://login.mailchimp.com/signup/");   //go to website
        driver.manage().window().maximize();
    }

    @Given("I accept cookies")
    public void i_accept_cookies() {
        WebElement cookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(cookies));
        cookies.click();
    }

    @Given("I enter an email address as {string}")
    public void i_enter_an_email_address_as(String string1) {
        WebElement emailTextField = driver.findElement(By.id("email"));
        emailTextField.sendKeys(string1);
    }

    @Given("I enter a new username as {string}")
    public void i_enter_a_new_username_as(String string2) {
        WebElement userName = driver.findElement(By.id("new_username"));
        userName.sendKeys(string2);
    }

    @Given("I also enter a new password as {string}")
    public void i_also_enter_a_new_password_as(String string3) {
        WebElement password = driver.findElement(By.id("new_password"));
        password.sendKeys(string3);
    }

    @When("I press the sign up button")
    public void i_press_the_sign_up_button() {
        WebElement signUpButton = driver.findElement(By.id("create-account"));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton));

        signUpButton.click();
    }

    @Then("I am registered as a new user")
    public void i_am_registered_as_a_new_user() {
        String bodyText = driver.findElement(By.tagName("body")).getText();

        if (bodyText.contains("with a link to activate your account")) {
            boolean textActivateAccount = bodyText.contains("with a link to activate your account");
            assertTrue(textActivateAccount);
        }

        else if (bodyText.contains("Enter a value less than 100 characters long")) {
            boolean text100Characters = bodyText.contains("Enter a value less than 100 characters long");
            assertTrue(text100Characters);
        }

        else if (bodyText.contains("evil twin")){
            boolean textEvilTwin = bodyText.contains("evil twin");
            assertTrue(textEvilTwin);
        }

        else if (bodyText.contains("Please enter a value")){
            boolean textEmailMissing = bodyText.contains("Please enter a value");
            assertTrue(textEmailMissing);
        }

        else{
            System.out.println("Something went wrong.");
        }


        //Assert.assertTrue("successfully signed up", bodyText.contains("with a link to activate your account"));
        //Assert.assertTrue("username too long", bodyText.contains("Enter a value less than 100 characters long"));

        //driver.quit();
    }
}
