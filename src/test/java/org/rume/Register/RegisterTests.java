package org.rume.Register;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.rume.Base.BaseTest;
import org.rume.Base.RegistrationPage;
import org.testng.annotations.Test;


public class RegisterTests extends BaseTest {

    private final Logger log = LogManager.getLogger(RegisterTests.class);

    @Test
    public void verifyUserCanRegisterWithValidCredentials() throws InterruptedException {

        RegistrationPage registerPage = new RegistrationPage(super.driver, log);
        registerPage.navigateToRegistrationPageByURL();

        log.info("STEP 1: Filling in registration fields");
        registerPage.provideUserName();
        registerPage.provideEmail();
        registerPage.providePass();
        registerPage.providePassConfirm();
        registerPage.providePublicInfo();

        log.info("STEP 2: Clicking submit");
        registerPage.clickOnSubmitBtn();
    }

    @Test
    public void verifyUserCannotRegisterWithoutFilledUserNameField() throws InterruptedException {

        RegistrationPage registerPage = new RegistrationPage(super.driver, log);
        registerPage.navigateToRegistrationPageByURL();

        log.info("STEP 1: Filling in registration fields");
//        registerPage.provideUserName();
        registerPage.provideEmail();
        registerPage.providePass();
        registerPage.providePassConfirm();
        registerPage.providePublicInfo();

        log.info("STEP 2: Clicking submit");
        registerPage.clickOnSubmitBtn();
        log.info("STEP 3: Staying on the same page after pressing Sign In button.");
    }
}
