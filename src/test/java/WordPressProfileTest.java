import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Pages.*;
import support.Utils;

import java.awt.*;

public class WordPressProfileTest extends MainBeforeAfter{

    @Test
    public void fillOutBasicInfo() {

        ProfilePage profilePagePage = new ProfilePage(driver);
        Assert.assertTrue(profilePagePage.isPageOpened());
        Reporter.log("Verified profile page is opened", true);

        String description  = String.format("Testing for automation : %s", Utils.genRandom(10));

        profilePagePage
                .verifySaveIsDisabled()
                .fillin("Eric","Fab","efabisze",description)
                .clickSaveProfile()
                .verifySaveText("Settings saved successfully!");
    }

    @Test
    public void verifyHideProfile() {
        ProfilePage profilePagePage = new ProfilePage(driver);
        Assert.assertTrue(profilePagePage.isPageOpened());
        Reporter.log("Verified profile page is opened", true);

        String hideText = "Hide my Gravatar profile. This will prevent your Gravatar profile and photo from appearing on any site. It may take some time for the changes to take effect. Gravatar profiles can be deleted at Gravatar.com.";

        profilePagePage
                .verifySaveIsDisabled()
                .verifyHideProfileText(hideText)
                .enableHideProfile()
                .clickSaveProfile()
                .verifySaveText("Settings saved successfully!");
    }

    @Test
    public void verifyAvatarProfile() throws AWTException, InterruptedException {
        ProfilePage profilePagePage = new ProfilePage(driver);
        Assert.assertTrue(profilePagePage.isPageOpened());
        Reporter.log("Verified profile page is opened", true);

        final String dash = "\u2014";

        profilePagePage
                .verifySaveIsDisabled()
                .verifyPhotoDisclaimer()
                .clickPhotoMoreInfo()
                .verifyDisclaimerPopup()
                .clickPhotoMoreInfo()
                .saveEditPhoto()
                .verifySaveText("You successfully uploaded a new profile photo "+dash+" looking sharp!")
                .verifySaveIsDisabled();
    }

    @Test
    public void verifyAddRemoveLinksProfile() {
        ProfilePage profilePagePage = new ProfilePage(driver);
        Assert.assertTrue(profilePagePage.isPageOpened());
        Reporter.log("Verified profile page is opened", true);

        String url1 = "www.test.com", descr1 = "Description for testing add links";
        String url2 = "www.test2.com", descr2 = "Another test link";


        profilePagePage
                .addLink(url1, descr1)
                .addLink(url2, descr2)
                .verifyLinkList(url1, descr1)
                .verifyLinkList(url2, descr2)
                .removeLink(url1, descr1)
                .removeLink(url2, descr2)
                .verifyEmptyLinkList();

    }

    @Test
    public void verifyAddExistingLinksProfile() {
        ProfilePage profilePagePage = new ProfilePage(driver);
        Assert.assertTrue(profilePagePage.isPageOpened());
        Reporter.log("Verified profile page is opened", true);

        String url1 = "www.test.com", descr1 = "Description for testing add links";

        profilePagePage
                .addLink(url1, descr1)
                .addLink(url1, descr1)
                .verifyErrorAddingLink()
                .closeErrorMessage()
                .verifyLinkList(url1, descr1)
                .removeLink(url1, descr1)
                .verifyEmptyLinkList();
    }
}