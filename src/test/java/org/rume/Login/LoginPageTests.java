package org.rume.Login;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.rume.Base.BaseTest;
import org.rume.Base.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginPageTests extends BaseTest {

    private LoginPage loginPage;
    private Logger log = LogManager.getLogger(LoginPageTests.class);

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(driver, log);  // initialize loginPage here
        loginPage.navigateToLoginPage();
    }

    @Test
    public void LoginPageTitleIsDisplayed() {
        log.info("Verifying login page header is shown");
        Assert.assertTrue(loginPage.isLoginFormHeaderTextShown(), "Login form header is NOT shown");
    }

    @Test
    public void LoginWithValidCredentials() {
        loginPage.loginWithTestUser();

        // Assert for succsesful login
        String toastMessage = loginPage.getLoginPageToastSuccessfullMsg();
        Assert.assertTrue(toastMessage.contains("Successful login!"),
                "Expected success message was not displayed. Actual: " + toastMessage);
    }

    @Test
    public void LoginWithInvalidCredentials() {
        loginPage.provideUser("invalidUser");
        loginPage.providePass("wrongPass");

        loginPage.clickOnLoginFormSubmitButton();

        //Assert: check for error toast
        String errorMessage = loginPage.getLoginPageToastUnssecsesfullMsg();
        Assert.assertTrue(errorMessage.contains("Wrong username or password!"), "Error message for invalid login was not displayed.");
    }

    @Test
    public void LoginButtonLabelText() {
        loginPage.getLoginFormSubmitButtonlabel();

        String buttonLabel = loginPage.getLoginFormSubmitButtonlabel();
        Assert.assertEquals(buttonLabel, "Sign in", "Login button label is incorrect");
    }

    @Test
    public void RememberMeCheckboxFunctionality() {
        
        loginPage.clickRememberMeCheckbox();

        // Assert checkbox is selected
        Assert.assertTrue(loginPage.isRememberMeSelected(), "Remember Me checkbox should be selected");
    }


}
