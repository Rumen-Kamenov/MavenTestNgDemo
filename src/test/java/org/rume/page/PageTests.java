package org.rume.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rume.Base.*;
import org.rume.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;

public class PageTests extends BaseTest {

    private final Logger log = LogManager.getLogger(PageTests.class);

    public static final String testUser = "alfa159";
    public static final String testPassword = "Alfa159";
    public static final String caption = "Testing the create post caption";
    File postPicture = new File("src/test/resources/upload/dog.jpg");

    @Test(priority = 0)
    public void verifyUserCanCreatePost() {
        log.info("STEP 1: Navigating to the Home Page");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Clicking on the Login button in the navigation bar");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Logging in with test user credentials");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUser();

        log.info("STEP 4: Navigating to the 'New Post' page");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 5: Creating a new Post");
        PostPage postPage = new PostPage(super.driver, log);

        log.info("→ Uploading post picture");
        postPage.uploadPicture(postPicture);

        log.info("→ Providing post caption: '{}'", caption);
        postPage.providePostCaption(caption);

        log.info("→ Clicking on 'Create Post' button");
        postPage.clickCreatePostButton();

        log.info("STEP 6: Verifying the post is visible in the Profile");
        ProfilePage profilePage = new ProfilePage(super.driver, log);

        boolean isMorePostShown = profilePage.getPostCount() > 0;
        Assert.assertTrue(isMorePostShown);

        log.info("→ Clicking on the most recent post");
        profilePage.clickPost(0);

        log.info("STEP 7: Verifying the post modal");
        PostModal postModal = new PostModal(super.driver, log);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");

        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, testUser,"Post author does not match expected user.");
    }

    @Test(priority = 1)
    public void verifyUserCanDeletePost() {
        log.info("STEP 1: Navigating to the Home Page");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Clicking on the Login button in the navigation bar");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Logging in with test user credentials");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUser();

        log.info("STEP 4: Navigating to the Profile page");
        homePage.clickOnProfileNavBar();

        log.info("STEP 5: Clicking on the first available post");
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickPost(0);

        log.info("STEP 6: Initiating deletion of the post");
        profilePage.ClickOnDeleteButton();

        log.info("STEP 7: Confirming deletion in the confirmation dialog");
        profilePage.ClickOnYesButton();

        log.info("STEP 8: Verifying that the deletion confirmation message is visible");
        boolean isDeleted = profilePage.isDeletedMessageVisible();
        log.info("→ Deletion message visibility: {}", isDeleted);

        Assert.assertTrue(isDeleted, "Post was not deleted successfully.");
        log.info("✅ Post was deleted successfully.");
    }
}

