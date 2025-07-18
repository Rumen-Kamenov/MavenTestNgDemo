package org.rume.Base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrationPage extends BasePage {

    public static final String REG_PAGE_SUFIX = "/users/register";
    public static final String EXPECTED_REG_FORM_HEADER_TEXT = "Sign up";
    public static final String EXPECTED_REG_MSG = "Successful register!";

    //2.LOCATORS
    //Registration form
    @FindBy(xpath = "//input[contains(@name, \"username\")]") // = driver.findElement(By.xpath(USERNAME_REG_INPUT_FIELD_XPATH));
    private WebElement regUsernameInputField;

    @FindBy (xpath = "//input[contains(@type, \"email\")]")
    private WebElement regEmailInputField;

    @FindBy (xpath = "//input[contains(@formcontrolname, \"birthDate\")]")
    private WebElement regBirthDateInputField;

    @FindBy (id = "defaultRegisterFormPassword" )
    private WebElement regPasswordInputField;

    @FindBy (id = "defaultRegisterPhonePassword")
    public WebElement regConfirmPasswordInputField;

    @FindBy (id = "toast-message")
    public WebElement toastMessage;

    @FindBy(css = "h4")
    private WebElement regFormTitle;

    @FindBy (xpath = "//textarea")
    private WebElement publicInfoTextArea;

    @FindBy (id = "sign-in-button")
    private WebElement registrationFormSubmitButton;

    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    // Navigation to the page
    public void navigateToRegistrationPageByURL(){
        navigateTo("/users/register");
    }

    //User Actions
    //Populate data in input fields
    public void provideUserName(){
        String providedDemoUser = demoUsername();
        typeTextIn(regUsernameInputField,providedDemoUser);
    };

    public void provideEmail(){
        typeTextIn(regEmailInputField,randomValidEmail());
    };

    public void provideBDayInfo(){
        typeTextIn(regBirthDateInputField,"22022022");
    };

    public void providePass(){
        typeTextIn(regPasswordInputField,"22022022!A");
    };

    public void providePassConfirm(){
        typeTextIn(regConfirmPasswordInputField,"22022022!A");
    };

    public void providePublicInfo(){
        typeTextIn(publicInfoTextArea,"Public profile");
    };

    public void provideMismatchedConfirmPassword() {
        typeTextIn(regConfirmPasswordInputField, "DifferentPassword123!");
    }

    public String getToastMessage() {
        log.info("Fetching the toast message after form submission.");
        return toastMessage.getText();
    }


    public void clickOnSubmitBtn(){
        wait.until(ExpectedConditions.visibilityOf(registrationFormSubmitButton));
        registrationFormSubmitButton.click();
    }

    public String getRegFormHeaderText(){
        return getElementText(regFormTitle);
    }

    //Support methods for reg page
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

    //Support utils for test data gen
    public String demoUsername() {
        String username = "Demo" + getCurrentTime();
        return username;
    }

    public String randomValidEmail() {
        String email = "demo" + getCurrentTime() + "@gmail.com";
        return email;
    }
}

