package org.rume.Base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private static final String LOGIN_PAGE_SUFIX = "/users/login";

    private static final String USER = "alfa159";
    private static final String PASS = "Alfa159";

    @FindBy(css = "p.h4")
    private WebElement loginFormTitle;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameInputField;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputField;

    @FindBy(id = "sign-in-button")
    private WebElement loginSubmitButton;

    @FindBy(xpath = "//div[contains(@aria-label,\"Successful login!\")]")
    private WebElement signInToastMessage;

    @FindBy(xpath = "//div[contains(@aria-label,\"Wrong username or password!\")]")
    private WebElement wrongSignInMessage;

    @FindBy(xpath = "//input[@type='checkbox' and @formcontrolname='rememberMe']")
    private WebElement rememberMeCheckbox;


    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    public void navigateToLoginPage(){
        navigateTo(LOGIN_PAGE_SUFIX);
    }

    public void provideUser(String userNameText){
        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
        usernameInputField.clear();
        usernameInputField.sendKeys(userNameText);
    }

    public void providePass(String pass){
        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
        passwordInputField.clear();
        passwordInputField.sendKeys(pass);
    }

    public void clickOnLoginFormSubmitButton(){
        wait.until(ExpectedConditions.visibilityOf(loginSubmitButton));
        loginSubmitButton.click();
    }

    public void loginWithTestUser(){
        provideUser(USER);
        providePass(PASS);
        clickOnLoginFormSubmitButton();
    }

    public String getLoginFormHeaderText(){

        return getElementText(loginFormTitle);
    }

    public String getUsernamePlaceHolderText(){

        return getElementPlaceholderValue(usernameInputField);
    }

    public String getPassPlaceHolderText(){

        return getElementPlaceholderValue(passwordInputField);
    }

    public String getLoginFormSubmitButtonlabel(){

        return getElementText(loginSubmitButton);
    }

    public String getLoginPageToastSuccessfullMsg(){

        return getElementText(signInToastMessage);
    }

    public String getLoginPageToastUnssecsesfullMsg(){

        return getElementText(wrongSignInMessage);
    }

    public boolean isLoginFormHeaderTextShown(){

        return isElementPresented(loginFormTitle);
    }

    public void clickRememberMeCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        rememberMeCheckbox.click();
    }

    public boolean isRememberMeSelected() {
        wait.until(ExpectedConditions.visibilityOf(rememberMeCheckbox));
        return rememberMeCheckbox.isSelected();
    }
}
