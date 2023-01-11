package com.pfa.qa.android.pages;

import com.pfa.qa.android.util.AndroidActions;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;

public class FormPage extends AndroidActions {
    AndroidDriver driver;
//    public FormPage(AndroidDriver driver) {
//        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
//
//    }
    public FormPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    //    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;
    @FindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    private WebElement maleOption;
    @FindBy(id = "android:id/text1")
    private WebElement formCountryDropDownClick;
    @FindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement formLetShopBtn;
    @FindBy(xpath = "(//android.widget.Toast)[1]")
    private WebElement formNameToastErrorMessage;


    public void setActivity() {
        AndroidActions.waiting(2000);
        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
        driver.startActivity(activity);
    }

    public void setNameField(String name) throws InterruptedException {
        nameField.sendKeys(name);
        AndroidActions.waiting(500);
        AndroidActions.hideKeyBard();

    }

    public void setGender(String gender) {
        if (gender.contains("female")) {
            AndroidActions.waiting(2000);
            driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
//            femaleOption.click();
        } else {
            AndroidActions.waiting(2000);
            maleOption.click();
        }

    }

    public void setCountrySelection(String countryName) throws InterruptedException {

        formCountryDropDownClick.click();
        AndroidActions.waiting(500);
        scrollToText(countryName);
        AndroidActions.waiting(500);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
        AndroidActions.waiting(500);

    }

    //    public ProductCatalog submitFrom() throws InterruptedException {
    public void submitFrom() throws InterruptedException {


        AndroidActions.waiting(500);
        formLetShopBtn.click();
        AndroidActions.waiting(500);
    }

    public void validateNameErrorMessage() {

        AndroidActions.waiting(500);
        String nameErrorMessage = formNameToastErrorMessage.getAttribute("name");
        AndroidActions.waiting(500);
//        AssertJUnit.assertEquals(nameErrorMessage,"Please enter your name");
        AssertJUnit.assertEquals(nameErrorMessage, "Please  your name"); // To check the TestNG Report Failing forcefully
        AndroidActions.waiting(500);


    }


}
