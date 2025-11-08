package org.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class FormTest {
    static WebDriver driver;
    static String URL = "https://demoqa.com/automation-practice-form";
    static ATUTestRecorder recorder;

    static final String FIRST_NAME = "Iulia";
    static final String LAST_NAME = "Erina";
    static final String EMAIL = "erina.iulia5@gmail.com";
    static final String GENDER = "Female";
    static final String USER_NUMBER = "0676785670";
    static final String DATE_OF_BIRTH = "07 November,2006";
    static final String SUBJECT = "Maths";
    static final String HOBBY = "Reading";
    static final String ADDRESS = "Str. Bucuriei 23";
    static final String STATE = "Haryana";
    static final String CITY = "Panipat";

    @BeforeClass
    public static void setup() throws ATUTestRecorderException {
        // Creează folderul pentru video dacă nu există
        java.io.File videoDir = new java.io.File("test-videos");
        if (!videoDir.exists()) videoDir.mkdir();

        // Pornește înregistrarea video
        recorder = new ATUTestRecorder("test-videos", "FormTest_Video", false);
        recorder.start();

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
            // dacă nu găsește ad-ul, ignoră
        }

        FormPom form = new FormPom(driver);

        form.setFirstName(FIRST_NAME);
        form.setLastName(LAST_NAME);
        form.setEmail(EMAIL);
        form.setFemaleGender();
        form.setMobile(USER_NUMBER);
        form.setDateOfBirth(DATE_OF_BIRTH);
        form.setSubjects(SUBJECT);
        form.setHobbyReading();
        form.setAddress(ADDRESS);
        form.setState(STATE);
        form.setCity(CITY);
        form.clickSubmit();

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        String actualName = driver.findElement(By.xpath("//tbody//tr[1]/*[2]")).getText();
        String actualEmail = driver.findElement(By.xpath("//tbody//tr[2]/*[2]")).getText();
        String actualGender = driver.findElement(By.xpath("//tbody//tr[3]/*[2]")).getText();
        String actualNumber = driver.findElement(By.xpath("//tbody//tr[4]/*[2]")).getText();
        String actualDate = driver.findElement(By.xpath("//tbody//tr[5]/*[2]")).getText();
        String actualSubjects = driver.findElement(By.xpath("//tbody//tr[6]/*[2]")).getText();
        String actualHobbies = driver.findElement(By.xpath("//tbody//tr[7]/*[2]")).getText();
        String actualStateCity = driver.findElement(By.xpath("//tbody//tr[10]/*[2]")).getText();

        Assert.assertEquals(FIRST_NAME + " " + LAST_NAME, actualName);
        Assert.assertEquals(EMAIL, actualEmail);
        Assert.assertEquals(GENDER, actualGender);
        Assert.assertEquals(USER_NUMBER, actualNumber);
        Assert.assertEquals(DATE_OF_BIRTH, actualDate);
        Assert.assertEquals(SUBJECT, actualSubjects);
        Assert.assertEquals(HOBBY, actualHobbies);
        Assert.assertEquals(STATE + " " + CITY, actualStateCity);
    }

    @AfterClass
    public static void tearDown() throws ATUTestRecorderException {
        // Oprește recorder-ul înainte de quit
        if (recorder != null) recorder.stop();

        if (driver != null) driver.quit();
    }
}
