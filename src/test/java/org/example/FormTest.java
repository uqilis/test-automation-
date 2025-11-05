package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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

        FormPom form = new FormPom(driver );

        form.setFirstName("Iulia");
        form.setLastName("Erina");
        form.setEmail("erina.iulia5@gmail.com");
        form.setFemaleGender();
        form.setMobile("0676785670");
        form.setDateOfBirth("07 Nov 2006");
        form.setSubjects("Maths");
        form.setHobbyReading();
        form.setAddress("Str. Bucuriei 23");
        form.setState("Haryana");
        form.setCity("Panipat");
        form.clickSubmit();

        // Scroll top pentru a evita ca modalul să fie în afara vizualizării
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

        // Așteaptă apariția modalului (titlul modalului)
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        // Verificăm numele introdus
        String actualName = driver.findElement(By.xpath("//td[text()='Student Name']/following-sibling::td")).getText();
        Assert.assertEquals(actualName, "Iulia Erina", "Numele introdus nu corespunde!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
