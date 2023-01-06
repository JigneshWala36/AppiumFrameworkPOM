package com.pfa.qa.android.pages;

import com.pfa.qa.android.util.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoToCartPage extends AndroidActions {

    AndroidDriver driver;

//    public GoToCartPage(AndroidDriver driver) {
//        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//    }

    public GoToCartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> cartProductPricesList;

    @FindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement cartTotalAmount;

    @FindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement cartTermAndConditionText;

    @FindBy(className = "android.widget.CheckBox")
    private WebElement cartTermAndConditionCheckBoxBtn;

    @FindBy(id = "android:id/button1")
    public WebElement acceptButton;

    @FindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement cartProceedBtn;


    // Method
    public List<WebElement> getCartProductPricesList() {
        AndroidActions.waiting(500);
        return cartProductPricesList;
    }

    public double getProductsSum() throws InterruptedException {

        int count = cartProductPricesList.size();
        double totalSum = 0;
        for (int i = 0; i < count; i++) {
            String amountString = cartProductPricesList.get(i).getText();
            AndroidActions.waiting(500);
            Double price = getFormattedAmount(amountString);
            AndroidActions.waiting(500);
            totalSum = totalSum + price;  //160.97 + 120 =280.97
            Thread.sleep(500);

        }

        return totalSum;
    }

    public Double getTotalAmountDisplayed()
    {
        AndroidActions.waiting(500);
        return getFormattedAmount(cartTotalAmount.getText());
    }

    public void acceptTermsConditions()
    {

        WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        AndroidActions.waiting(500);
        longPressAction(ele);
//        AndroidActions.waiting(500);
//        longPressAction(cartTermAndConditionText);
        AndroidActions.waiting(500);
        acceptButton.click();
        AndroidActions.waiting(500);
    }

    public void submitOrder()
    {
        cartTermAndConditionCheckBoxBtn.click();
        AndroidActions.waiting(500);
        cartProceedBtn.click();
        AndroidActions.waiting(500);
    }


}
