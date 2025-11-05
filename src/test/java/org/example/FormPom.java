package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPom {

    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;
    Actions actions;

    public FormPom(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName") WebElement firstName;
    @FindBy(id = "lastName") WebElement lastName;
    @FindBy(id = "userEmail") WebElement email;
    @FindBy(xpath = "//label[text()='Female']") WebElement femaleGender;
    @FindBy(id = "userNumber") WebElement mobile;
    @FindBy(id = "dateOfBirthInput") WebElement dateOfBirth;
    @FindBy(id = "subjectsInput") WebElement subjects;
    @FindBy(xpath = "//label[text()='Reading']") WebElement hobbyReading;
    @FindBy(id = "currentAddress") WebElement address;
    @FindBy(id = "react-select-3-input") WebElement state;
    @FindBy(id = "react-select-4-input") WebElement city;
    @FindBy(id = "submit") WebElement submitButton;

    public void setFirstName(String name) { firstName.clear(); firstName.sendKeys(name); }
    public void setLastName(String name) { lastName.clear(); lastName.sendKeys(name); }
    public void setEmail(String mail) { email.clear(); email.sendKeys(mail); }
    public void setFemaleGender() { femaleGender.click(); }
    public void setMobile(String number) { mobile.clear(); mobile.sendKeys(number); }

    public void setDateOfBirth(String dob) {
        dateOfBirth.sendKeys(Keys.CONTROL + "a");
        dateOfBirth.sendKeys(dob);
        dateOfBirth.sendKeys(Keys.ENTER);
    }

    /** Subjects corect */
    public void setSubjects(String subject) {
        js.executeScript("arguments[0].scrollIntoView(true);", subjects);
        wait.until(ExpectedConditions.elementToBeClickable(subjects));

        subjects.click();
        subjects.sendKeys(subject);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-2-option-0")));
        subjects.sendKeys(Keys.ENTER);
    }

    public void setHobbyReading() {
        js.executeScript("arguments[0].scrollIntoView(true);", hobbyReading);
        js.executeScript("arguments[0].click();", hobbyReading);
    }

    public void setAddress(String addr) {
        js.executeScript("arguments[0].scrollIntoView(true);", address);
        address.sendKeys(addr);
    }

    public void setState(String st) {
        js.executeScript("arguments[0].scrollIntoView(true);", state);
        state.sendKeys(st);
        state.sendKeys(Keys.ENTER);
    }

    public void setCity(String ct) {
        js.executeScript("arguments[0].scrollIntoView(true);", city);
        city.sendKeys(ct);
        city.sendKeys(Keys.ENTER);
    }

    public void clickSubmit() {
        js.executeScript("arguments[0].scrollIntoView(true)", submitButton);
        js.executeScript("arguments[0].click();", submitButton);
    }
}
