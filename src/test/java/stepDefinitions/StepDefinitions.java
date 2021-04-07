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

    // Method to enter value into text field:
    public void sendKeys(By by, String input) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement textField = driver.findElement(by);
        textField.sendKeys(input);
    }

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
        if (cookies.isDisplayed()) {
            cookies.click();
        }
    }

    @Given("I enter an email address as {string}")
    public void i_enter_an_email_address_as(String inputEmail) {
        sendKeys(By.id("email"), inputEmail);
    }

    @Given("I enter a new username as {string}")
    public void i_enter_a_new_username_as(String inputUsername) {
        if (inputUsername.equals("carrot")) {
            int randomNumber = (int) (Math.random() * 100000000);
            String randomUserName = inputUsername + (String.valueOf(randomNumber));
            sendKeys(By.id("new_username"), randomUserName);
        } else if (inputUsername.equals("longUserName")) {
            sendKeys(By.id("new_username"), "strawberry13571strawberry13571strawberry13571strawberry13571strawberry13571strawberry13571strawberry13571");
        } else
            sendKeys(By.id("new_username"), inputUsername);
    }

    @Given("I also enter a new password as {string}")
    public void i_also_enter_a_new_password_as(String inputPassword) {
        sendKeys(By.id("new_password"), inputPassword);
    }

    @When("I press the sign up button")
    public void i_press_the_sign_up_button() {
        WebElement signUpButton = driver.findElement(By.id("create-account"));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton));

        if (signUpButton.isDisplayed()) {
            signUpButton.click();
        }
    }

    @Then("Registration is successful\\/unsuccessful, as displayed with {string}")
    public void registration_is_successful_unsuccessful_as_displayed_with(String status) {
        String bodyText = driver.findElement(By.tagName("body")).getText();

        if (status.equals("success")) {
            boolean textActivateAccount = bodyText.contains("with a link to activate your account");
            assertTrue(textActivateAccount);
        } else if (status.equals("fail")) {

            if (bodyText.contains("Enter a value less than 100 characters long")) {
                boolean text100Characters = bodyText.contains("Enter a value less than 100 characters long");
                assertTrue(text100Characters);

            } else if (bodyText.contains("evil twin")) {
                boolean textEvilTwin = bodyText.contains("evil twin");
                assertTrue(textEvilTwin);

            } else if (bodyText.contains("Please enter a value")) {
                boolean textEmailMissing = bodyText.contains("Please enter a value");
                assertTrue(textEmailMissing);

            } else {
                System.out.println("Something went wrong.");
            }
        }

    }


}
