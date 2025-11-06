package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Driver;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FormTest {

    WebDriver driver;
    static String URL = "https://demoqa.com/automation-practice-form";

    @BeforeClass
    public void setup() {
        driver = Driver.getLocalDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void fillFormTest() {
        driver.get(URL);

         try {
            WebElement ad = driver.findElement(By.cssSelector("iframe[id^='google_ads_iframe']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", ad);
        } catch (Exception e) {

        }

        FormPom form = new FormPom(driver);

        form.setFirstName("Iulia");
        form.setLastName("Erina");
        form.setEmail("erina.iulia5@gmail.com");

        WebElement female = driver.findElement(By.cssSelector("label[for='gender-radio-2']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", female);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", female);

        form.setMobile("0676785670");
        form.setDateOfBirth("07 Nov 2006");
        form.setSubjects("Maths");
        form.setHobbyReading();
        form.setAddress("Str. Bucuriei 23");
        form.setState("Haryana");
        form.setCity("Panipat");
        form.clickSubmit();


        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");


        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        String actualName = driver.findElement(By.xpath("//td[text()='Student Name']/following-sibling::td")).getText();
        Assert.assertEquals(actualName, "Iulia Erina", "Numele introdus nu corespunde!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
