package com.pfa.qa.android.test;

import com.pfa.qa.android.base.TestBase;
import com.pfa.qa.android.pages.GoToCartPage;
import com.pfa.qa.android.pages.ProductCatalogPage;
import com.pfa.qa.android.util.AndroidActions;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class EndToEndTestPage extends TestBase {


    @Test
    public void FormToBuy() throws InterruptedException
    {

        Thread.sleep(5000);
//        FormPage formPage = new FormPage(driver); // Initialize added in the Base Test // Not Requires as initialize already done in Base Test
        formPage.setNameField("Jignesh Wala");
        AndroidActions.waiting(500);
        formPage.setGender("female");
        AndroidActions.waiting(500);
        formPage.setCountrySelection("Argentina");
        AndroidActions.waiting(500);
        formPage.submitFrom();

//        ProductCatalogue productCatalogue = formPage.submitForm();


//        ProductCatalog productCatalog = formPage.submitFrom(); // Initialize Done in the Form Page
        AndroidActions.waiting(500);

        ProductCatalogPage productCatalog = new ProductCatalogPage(driver); // Not Requires as initialize already done

        productCatalog.setItemToAddCartByIndex(0);
        AndroidActions.waiting(500);
        productCatalog.setItemToAddCartByIndex(0);
        AndroidActions.waiting(500);


        GoToCartPage goToCartPage = productCatalog.goToCartPage(); // Initialize Done in the ProductCatalog Page

        Thread.sleep(1000);

        double totalSum = goToCartPage.getProductsSum();
        AndroidActions.waiting(500);
        double displayFormattedSum = goToCartPage.getTotalAmountDisplayed();
        AndroidActions.waiting(500);
        AssertJUnit.assertEquals(totalSum, displayFormattedSum);
        System.out.println(displayFormattedSum);
        AndroidActions.waiting(500);


        goToCartPage.acceptTermsConditions();
        AndroidActions.waiting(500);
        goToCartPage.submitOrder();
        Thread.sleep(5000);

        //Hybrid - Google page->

    }


}
