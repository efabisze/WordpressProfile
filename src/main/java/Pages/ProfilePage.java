package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.Reporter;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Locale;

public class ProfilePage {
    private WebDriver driver;

    @FindBy(css = ".formatted-header__title.wp-brand-font")
    protected WebElement mainProfileHeader;

    @FindBy(css = ".section-header__label-text")
    protected WebElement profileHeaderSection;

    @FindBy(css = ".file-picker")
    protected WebElement editPictureLink;
/*
    @FindBy(css = ".button.image-editor__buttons-button")
    protected WebElement editPictureLinkCancelButton;
*/

    @FindBy(css = ".button.image-editor__buttons-button.is-primary")
    protected WebElement editPicturePopupChangeMyPhotoButton;

    @FindBy(css = ".image-editor__buttons")
    protected WebElement editPictureButtonContainer;

    @FindBy(id = "gridicons-cross")
    protected WebElement closeSavePopup;

    @FindBy(css = ".button.image-editor__buttons-button")
    protected WebElement editPictureResetLink;

    @FindBy(css = ".edit-gravatar__image-container")
    protected WebElement editPictureChangePhotoButton;

    @FindBy(css = ".edit-gravatar__explanation")
    protected WebElement profilePicExplanation;

    @FindBy(css = ".info-popover.edit-gravatar__pop-over")
    protected WebElement moreInfoExplanation;

    @FindBy(css = ".info-popover.edit-gravatar__pop-overpopover.info-popover__tooltip.edit-gravatar__pop-over.is-left")
    protected WebElement moreInfoPopup;

    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    public WebElement lastNameIntput;

    @FindBy(id = "display_name")
    protected WebElement displayNameInput;

    @FindBy(id = "description")
    protected WebElement aboutMeDescriptionArea;

    @FindBy(css = ".components-form-toggle")
    protected WebElement uncheckedHideGravatarProfileToggle;

    @FindBy(css = ".components-form-toggle.is-checked")
    protected WebElement checkedHideGravatarProfileToggle;

    @FindBy(css = ".components-form-toggle__input")
    protected WebElement hideGravatarProfileInput;

    @FindBy(css = ".button.form-button.is-primary")
    protected WebElement saveProfileDetails;

    @FindBy(css = ".components-toggle-control__label")
    protected WebElement toggleProfileLabel;

    @FindBy(css = ".section-header__label-text")
    protected WebElement profileLinksHeader;

    @FindBy(css = ".gridicon.gridicons-add-outline")
    protected WebElement addLinkButton;

    @FindBy(css = ".popover__menu")
    protected WebElement addProfilePopupMenu;

    @FindBy(css = ".popover__menu-item")
    List<WebElement> addLinkOptions;

    @FindBy(css = ".profile-links__no-links")
    protected WebElement noLinkMessage;

    @FindBy(css = ".form-text-input.profile-links-add-other__value")
    protected WebElement urlLinkInput;

    @FindBy(css = ".form-text-input.profile-links-add-other__title")
    protected WebElement urlDescriptionInput;

    @FindBy(css = ".button.profile-links-add-other__add.form-button.is-primary")
    protected WebElement addButtonSiteButton;

    @FindBy(css = ".button.profile-links-add-other__cancel.form-button")
    protected WebElement cancelButtonSiteButton;

    @FindBy(css = ".card")
    protected WebElement manageSitesCard;

    @FindBy(css = ".profile-links__list")
    protected WebElement profileLinkList;

    @FindBy(css = ".notice__text")
    protected WebElement noticeText;

    @FindBy(css = ".popover__inner")
    protected WebElement moreInfoPopupText;

    @FindBy(css = "input[type=\"file\"]")
    protected WebElement photoFileInput;

    @FindBy(css = ".notice.is-error.profile-links__error.is-dismissable")
    protected WebElement notice;

 //   @FindBy(css = "button[text='Create Site']")
  //  protected WebElement createSiteButton;

    @FindBy(css = ".button.profile-links-add-wordpress__cancel.form-button")
    protected WebElement cancelWordpressSiteButton;

    private By createSiteButton = new ByChained(By.xpath("//button[text()='Cancel']"));

    private By addLinkBy = new ByChained(By.cssSelector(".gridicon.gridicons-add-outline"));
    private By addLinkPopupOptions = new ByChained(By.cssSelector(".popover__menu-item"));
    private By noticeContent = new ByChained(By.cssSelector(".notice.is-error profile-links__error.is-dismissable"));
    private By photoCancelButton = new ByChained(By.xpath("//button[text()='Cancel']"));

    private By linkListDescr = new ByChained(By.cssSelector(".profile-link__title"));
    private By linkListUrl = new ByChained(By.cssSelector(".profile-link__url"));
    private By removeXButton = new ByChained(By.cssSelector(".gridicon.gridicons-cross"));

    private By closeSavePopupBy = new ByChained(By.id("gridicons-cross"));
    private By noticeTextBy = new ByChained(By.cssSelector(".notice__text"));

    public ProfilePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened()
    {
        Reporter.log("Verifying profile page is currently opened", true);

        return mainProfileHeader.getText().contains("My Profile");
    }

    public ProfilePage fillin(String firstName, String lastName, String displayName, String aboutMe)
    {
        Reporter.log("Fill in the inputs of text boxes and text area of profile", true);

        waitForVisibility(firstNameInput);

        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        lastNameIntput.clear();
        lastNameIntput.sendKeys(lastName);

        displayNameInput.clear();
        displayNameInput.sendKeys(displayName);

        aboutMeDescriptionArea.clear();
        aboutMeDescriptionArea.sendKeys(aboutMe);

        return this;
    }

    public ProfilePage clickSaveProfile()
    {
        Reporter.log("Clicking save profile button", true);
        waitForVisibility(firstNameInput);

        saveProfileDetails.click();
        return this;
    }

    public ProfilePage verifySaveText(String text)
    {
        Reporter.log("Verifying save text is displayed", true);

        waitForVisibility(noticeText);

        String displayedText = noticeText.getText().toLowerCase().trim();
        Assert.assertEquals(displayedText, text.toLowerCase().trim());

        return this;
    }
/*    public ProfilePage closeSavePopup()
    {
        Reporter.log("Verifying save button is disabled", true);

        WebDriverWait wait = new WebDriverWait(driver, 15);

        wait.until(ExpectedConditions.visibilityOfElementLocated(closeSavePopupBy));
        closeSavePopup.click();

        return this;
    }*/

    public ProfilePage verifySaveIsDisabled()
    {
        Reporter.log("Verifying save button is disabled", true);
        waitForVisibility(saveProfileDetails);
        Assert.assertFalse(saveProfileDetails.isEnabled());
        return this;
    }

    public ProfilePage enableHideProfile()
    {
        Reporter.log("Enable or make sure hide profile toggle is on", true);

        if(!hideGravatarProfileInput.isSelected())
            hideGravatarProfileInput.click();

        return this;
    }

    public ProfilePage disableHideProfile()
    {
        Reporter.log("Disable or make sure hide profile toggle is off", true);

        if(hideGravatarProfileInput.isSelected())
            hideGravatarProfileInput.click();

        return this;
    }
    public ProfilePage verifyHideProfileText(String hideText)
    {
        Reporter.log("Verify the helper text for hide profile toggle", true);

        waitForVisibility(toggleProfileLabel);

        String displayedHideProfile = toggleProfileLabel.getText().trim().toLowerCase();
        Assert.assertEquals(displayedHideProfile, hideText.toLowerCase().trim());

        return this;
    }

    public ProfilePage clickPhotoMoreInfo()
    {
        Reporter.log("Clicking the more info explanation", true);

        waitForVisibility(moreInfoExplanation);
        moreInfoExplanation.click();

        return this;
    }

    public ProfilePage verifyPhotoDisclaimer()
    {
        Reporter.log("Verifying the photo disclaimer has correct text ", true);

        waitForVisibility(profilePicExplanation);

        String profileExplanation = "Your profile photo is public.";
        String displayedHideProfile = profilePicExplanation.getText().trim().toLowerCase();

        Assert.assertEquals(displayedHideProfile, profileExplanation.toLowerCase().trim());

        return this;
    }

    public ProfilePage verifyDisclaimerPopup()
    {
        Reporter.log("Verifying disclaimer popup contains correct text", true);

        String expectedInfo = "the avatar you use on wordpress.com comes from gravatar\n" +
                "(opens in a new tab)\n" +
                ", a universal avatar service (it stands for \"globally recognized avatar,\" get it?).\n" +
                "your image may also appear on other sites using gravatar whenever you're logged in with the email efabisze@gmail.com.";

        waitForVisibility(moreInfoPopupText);

        String displayedHideProfile = moreInfoPopupText.getText().trim().toLowerCase();

        Assert.assertEquals(displayedHideProfile, expectedInfo.toLowerCase().trim());

        return this;
    }

    public ProfilePage saveEditPhoto() throws AWTException, InterruptedException {
        Reporter.log("Saving new photo to the profile", true);
        waitForVisibility(editPictureChangePhotoButton);

        editPictureChangePhotoButton.click();
        Thread.sleep(2000);
        StringSelection ss = new StringSelection(System.getProperty("user.dir")+"\\src\\test\\resources\\barber.png");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        editPicturePopupChangeMyPhotoButton.click();

        return this;
    }

    public ProfilePage verifyErrorAddingLink()
    {
        Reporter.log("Verify when adding duplicate links", true);

        String text = "That link is already in your profile links. No changes were made.";

        waitForVisibility(noticeText);

        String displayedText = noticeText.getText().toLowerCase().trim();
        Assert.assertEquals(displayedText, text.toLowerCase().trim());

        return this;
    }

    public ProfilePage closeErrorMessage()
    {
        Reporter.log("Dismiss first error message displayed", true);
        waitForVisibility(noticeText);

        WebElement gparent = noticeText.findElement(By.xpath("../.."));
        gparent.findElement(removeXButton).click();

        return this;
    }

    public ProfilePage verifyEmptyLinkList()
    {
        Reporter.log("Verify no links are displayed under profile links and correct message is displayed", true);

        String expectedMessage = "You have no sites in your profile links. You may add sites if you'd like.";
        waitForVisibility(noLinkMessage);

        String displayedMessage = noLinkMessage.getText().toLowerCase().trim();

        Assert.assertEquals(displayedMessage, expectedMessage.toLowerCase().trim());

        return this;
    }

    public ProfilePage clickAddProfileLink()
    {
        Reporter.log("Click the add button on links section", true);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addLinkButton);

        waitForVisibility(addLinkButton);
        addLinkButton.click();

        return this;
    }

    public ProfilePage verifyAddEmptyWordpressSite()
    {
        Reporter.log("Verifying empty wordpress site", true);
        String noLinks = "You have no public sites on WordPress.com yet, but if you like you can create one now. You may also add self-hosted WordPress sites here after installing Jetpack on them.";

        WebElement option = null;

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addLinkButton);

        waitForVisibility(addLinkButton);
        addLinkButton.click();

        waitForVisibility(addProfilePopupMenu);
        List<WebElement> addOptions = addProfilePopupMenu.findElements(addLinkPopupOptions);

        for(int i = 0; i < addOptions.size(); i++) {
            option = addOptions.get(i);

            String text = option.getText().trim().toLowerCase();

            if (option.getText().trim().equalsIgnoreCase("add wordpress site"))
            {
                option.click();
                break;
            }
        }

        waitForVisibility(cancelWordpressSiteButton);
        WebElement parent = cancelWordpressSiteButton.findElement(By.xpath("./..")).findElement(By.xpath(".//p"));
        Assert.assertEquals(parent.getText().trim().toLowerCase(), noLinks.toLowerCase());

        return this;
    }

    public ProfilePage clickCancelWordpressSite()
    {
        Reporter.log("Clicking the cancel button on Adding Wordpress Site", true);

        waitForVisibility(cancelWordpressSiteButton);
        cancelWordpressSiteButton.click();

        return this;
    }

    public ProfilePage addLink(String url, String descr)
    {
        Reporter.log("Adding a link to the profile : " + url, true);

        WebElement option = null;

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addLinkButton);

        waitForVisibility(addLinkButton);
        addLinkButton.click();

        waitForVisibility(addProfilePopupMenu);
        List<WebElement> addOptions = addProfilePopupMenu.findElements(addLinkPopupOptions);

        for(int i = 0; i < addOptions.size(); i++) {
            option = addOptions.get(i);

            String text = option.getText().trim().toLowerCase();

            if (option.getText().trim().equalsIgnoreCase("add url"))
            {
                option.click();
                break;
            }
        }

        waitForVisibility(urlLinkInput);
        urlLinkInput.sendKeys(url);
        urlDescriptionInput.sendKeys(descr);
        addButtonSiteButton.click();

        Reporter.log("Added url "+url+ " to link list", true);

        return this;
    }

    public ProfilePage verifyLinkList(String url, String descr)
    {
        Reporter.log("Verifying the url " + url + "\n with description : " + descr, true);

        waitForVisibility(profileLinkList);

        List<WebElement> descrList = profileLinkList.findElements(linkListDescr);
        WebElement foundDescr = null;

        int i =0;

        for(i = 0; i < descrList.size(); i++) {
            foundDescr = descrList.get(i);

            Reporter.log("Description expected : " + descr + "\n Displayed link descr for : " + foundDescr.getText().trim(), true);

            if (foundDescr.getText().trim().equalsIgnoreCase(descr.trim()))
            {
                Reporter.log("Found description " + foundDescr.getText(), true);

                WebElement parent = foundDescr.findElement(By.xpath("./.."));

                Assert.assertEquals(parent.findElement(linkListUrl).getText().trim().toLowerCase(), url.trim().toLowerCase());
                break;
            }
        }

        Assert.assertNotEquals(i,descrList.size(), "The description was not found in link list");

        return this;
    }

    public ProfilePage removeLink(String url, String descr)
    {
        Reporter.log("Removing the url link : " + url + "\n with description : " + descr, true);

        waitForVisibility(profileLinkList);
        List<WebElement> descrList = profileLinkList.findElements(linkListDescr);
        WebElement foundDescr = null;

        int i =0;

        for(i = 0; i < descrList.size(); i++)
        {
            foundDescr = descrList.get(i);

            if (foundDescr.getText().trim().equalsIgnoreCase(descr.trim()))
            {
                WebElement gparent = foundDescr.findElement(By.xpath("../.."));

                if(gparent.findElement(linkListUrl).getText().trim().equalsIgnoreCase(url.trim().toLowerCase()))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", gparent.findElement(removeXButton));

                    gparent.findElement(removeXButton).click();
                    break;
                }
            }
        }

        Assert.assertNotEquals(i,descrList.size(), "The description was not found in link list");

        return this;
    }

    private void waitForVisibility(WebElement element) throws Error
    {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(element));
    }

}
