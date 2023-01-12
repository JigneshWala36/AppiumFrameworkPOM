package com.pfa.qa.android.test;

import com.pfa.qa.android.base.TestBase;
import com.pfa.qa.android.util.AndroidActions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormTestPageTest extends TestBase {
    @BeforeMethod
    public void preSetup() {
        //screen to home page
        formPage.setActivity();
    }
    @Test
    public void FillForm_ErrorValidation() throws InterruptedException {

        AndroidActions.waiting(1000);
        formPage.setGender("female");
        AndroidActions.waiting(500);
        formPage.setCountrySelection("Argentina");
        AndroidActions.waiting(500);
        formPage.submitFrom();
        AndroidActions.waiting(500);
        formPage.validateNameErrorMessage();
        AndroidActions.waiting(500);

    }
    @Test
    public void FillForm_PositiveFlow() throws InterruptedException {

        AndroidActions.waiting(1000);
//        FormPage formPage = new FormPage(driver); // Initialize added in the Base Test // Not Requires as initialize already done in Base Test
        formPage.setNameField("Jignesh Wala");
        AndroidActions.waiting(500);
        formPage.setGender("female");
        AndroidActions.waiting(500);
        formPage.setCountrySelection("Argentina");
        AndroidActions.waiting(500);
        formPage.submitFrom();
        AndroidActions.waiting(500);

    }
}
