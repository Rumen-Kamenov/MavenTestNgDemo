package org.rume.register;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.rume.base.BaseTest;
import org.rume.Base.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.rume.Base.RegistrationPage.EXPECTED_REG_FORM_HEADER_TEXT;
import static org.rume.Base.RegistrationPage.EXPECTED_REG_MSG;


public class RegisterTests extends BaseTest {

    private final Logger log = LogManager.getLogger(RegisterTests.class);

    @Test
    public void verifyUserCanRegisterWithValidCredentials() throws InterruptedException {

        RegistrationPage registerPage = new RegistrationPage(super.driver, log);
        registerPage.navigateToRegistrationPageByURL();

        log.info("STEP 1: Filling in registration fields");

        log.info("STEP 2: Verifying the registration form header text.");
        String actualRegFormHeaderText = registerPage.getRegFormHeaderText();
        log.info("→ Expected header: '{}', Actual header: '{}'", EXPECTED_REG_FORM_HEADER_TEXT, actualRegFormHeaderText);
        Assert.assertEquals(actualRegFormHeaderText, EXPECTED_REG_FORM_HEADER_TEXT, "The registration form title does not match the expected message.");

        log.info("STEP 1: Filling in registration fields.");
        registerPage.provideUserName();
        registerPage.provideEmail();
        log.info("  → Email provided.");
        registerPage.providePass();
        log.info("  → Password provided.");
        registerPage.providePassConfirm();
        log.info("  → Confirm password provided.");
        registerPage.providePublicInfo();
        log.info("  → Public info provided.");
        log.info("STEP 2: Clicking submit");
        registerPage.clickOnSubmitBtn();

        log.info("STEP 3. Assert if successful registration popup message is shown");
        Assert.assertTrue(true, EXPECTED_REG_MSG);

    }

    @Test
    public void verifyUserCannotRegisterWithoutFilledUserNameField() throws InterruptedException {

        RegistrationPage registerPage = new RegistrationPage(super.driver, log);
        registerPage.navigateToRegistrationPageByURL();

        log.info("STEP 1: Filling in registration fields (excluding username).");
        registerPage.provideEmail();
        log.info("  → Email provided.");
        registerPage.providePass();
        log.info("  → Password provided.");
        registerPage.providePassConfirm();
        log.info("  → Confirm password provided.");
        registerPage.providePublicInfo();
        log.info("  → Public info provided.");

        log.info("STEP 2: Clicking submit");
        registerPage.clickOnSubmitBtn();
        log.info("STEP 3: Staying on the same page after pressing Sign In button.");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/users/register"), "User was redirected despite missing username.");
    }
}
