package com.pfa.qa.android.pages;

import com.pfa.qa.android.util.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends AndroidActions {

    AndroidDriver driver;

//    public ProductCatalogPage(AndroidDriver driver) {
//        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//    }

    public ProductCatalogPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> productAddToCart;


    @FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement productCartButton;


    // Method

    public void setItemToAddCartByIndex(int index) {
        AndroidActions.waiting(1000);
        productAddToCart.get(index).click();
        AndroidActions.waiting(1000);
    }

    public GoToCartPage goToCartPage() {

        AndroidActions.waiting(500);
        productCartButton.click();
        AndroidActions.waiting(2500);
        return new GoToCartPage(driver);

    }


}
