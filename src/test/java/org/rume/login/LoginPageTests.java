package org.rume.login;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.rume.base.BaseTest;
import org.rume.Base.HomePage;
import org.rume.Base.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginPageTests extends BaseTest {

    private LoginPage loginPage;
    private Logger log = LogManager.getLogger(LoginPageTests.class);

    private static final String LOGIN_FORM_LABEL = "Sign in";
    private static final String HOME_PAGE_TITLE = "ISkillo";
    public static final String LOGIN_SUCCESSFUL_MSG = "Successful login!";
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";
    public static final String USER = "alfa159";
    public static final String PASS = "Alfa159";

    @Test
    public void loginPageTitleIsDisplayed() {
        HomePage homePage = new HomePage(driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1: Verify the user is on the home page");
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(actualHomePageTitle,HOME_PAGE_TITLE);

        log.info("STEP 1.2: Verify that the login link is presented");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The user is navigating to the login page via click on navigation bar login link");
        homePage.clickOnLoginNavBar();

        log.info("STEP 2.1: The user is successfully on the login page");
        LoginPage loginPage = new LoginPage(driver, log);

        log.info("STEP 3: Verify login form header text is shown");
        boolean isHeaderDisplayed = loginPage.isLoginFormHeaderTextShown();
        Assert.assertTrue(isHeaderDisplayed, "Login form header is NOT shown");
    }


    @Test
    public void loginWithValidCredentials() {

        HomePage homePage = new HomePage(driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1: Verify the user is on the home page");
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(actualHomePageTitle, HOME_PAGE_TITLE);

        log.info("STEP 1.2: Verify that the login link is presented");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The user is navigating to the login page via click on navigation bar login link");
        homePage.clickOnLoginNavBar();

        log.info("STEP 2.1: The user is successfully on the login page");
        LoginPage loginPage = new LoginPage(driver, log);

        log.info("STEP 3: Provide valid username");
        loginPage.provideUser(USER);

        log.info("STEP 4: Provide valid password");
        loginPage.providePass(PASS);

        log.info("STEP 5: Click on login button");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 6: Verify the user is successfully logged in");
        String actualLoginSuccessfulMsg = loginPage.getLoginPageToastSuccessfullMsg();

        log.info("STEP 6.1: Verify login successful message is presented to the user");
        Assert.assertEquals(actualLoginSuccessfulMsg, LOGIN_SUCCESSFUL_MSG);

        log.info("STEP 6.2: Verify for an already logged-in user a logout button is shown");
        boolean isLogOutButtonPresented = homePage.isLogOutButtonShown();
        Assert.assertTrue(isLogOutButtonPresented);

        log.info("STEP 6.3: Verify for an already logged-in user a nav bar profile link is shown");
        boolean isNavBarProfileShown = homePage.isNavBarProfileLinkShown();
        Assert.assertTrue(isNavBarProfileShown);
    }

    @Test
    public void loginWithInvalidCredentials() {

        HomePage homePage = new HomePage(driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1: Verify the user is on the home page");
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(actualHomePageTitle,HOME_PAGE_TITLE);

        log.info("STEP 1.2: Verify that the login link is presented");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The user is navigating to the login page via click on navigation bar login link");
        homePage.clickOnLoginNavBar();

        log.info("STEP 2.1: The user is successfully on the login page");
        LoginPage loginPage = new LoginPage(driver, log);

        log.info("STEP 3: Provide invalid username");
        loginPage.provideUser("invalidUser");

        log.info("STEP 4: Provide invalid password");
        loginPage.providePass("wrongPass");

        log.info("STEP 5: Click on login button");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 6: Verify error message for invalid login is displayed");
        String errorMessage = loginPage.getLoginPageToastUnssecsesfullMsg();
        Assert.assertTrue(errorMessage.contains(LOGIN_NOT_SUCCESSFUL_MSG),
                "Error message for invalid login was not displayed.");
    }

    @Test
    public void checkLoginButtonLabelText() {

        HomePage homePage = new HomePage(driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1: Verify the user is on the home page");
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(actualHomePageTitle,HOME_PAGE_TITLE);

        log.info("STEP 1.2: Verify that the login link is presented");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The user navigates to the login page by clicking the login link");
        homePage.clickOnLoginNavBar();

        log.info("STEP 2.1: User is now on the login page");
        LoginPage loginPage = new LoginPage(driver, log);

        log.info("STEP 3: Retrieve login button label");
        String actualButtonLabel = loginPage.getLoginFormSubmitButtonlabel();

        log.info("STEP 4: Verify the login button label text is 'Sign in'");
        Assert.assertEquals(actualButtonLabel, LOGIN_FORM_LABEL, "Login button label is incorrect");
    }

    @Test
    public void rememberMeCheckboxFunctionality() {
        HomePage homePage = new HomePage(driver, log);
        log.info("STEP 1: Open Skillo Home Page");
        homePage.openHomePage();

        log.info("STEP 2: Navigate to Login Page from navigation bar");
        homePage.clickOnLoginNavBar();

        LoginPage loginPage = new LoginPage(driver, log);

        log.info("STEP 3: Click on 'Remember Me' checkbox");
        loginPage.clickRememberMeCheckbox();

        log.info("STEP 4: Verify the 'Remember Me' checkbox is selected");
        Assert.assertTrue(loginPage.isRememberMeSelected(), "'Remember Me' checkbox should be selected.");
    }

    @Test
    public void verifyUsernameFieldPlaceholderText() {
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();
        homePage.clickOnLoginNavBar();

        LoginPage loginPage = new LoginPage(driver, log);
        log.info("STEP 1: Get placeholder text for username field");
        String actualPlaceholder = loginPage.getUsernamePlaceHolderText();
        Assert.assertEquals(actualPlaceholder, "Username or email");
    }
}
