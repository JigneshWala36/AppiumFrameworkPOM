package com.pfa.qa.android.test;

import com.pfa.qa.android.base.TestBase;
import com.pfa.qa.android.util.AndroidActions;
import com.pfa.qa.android.util.AppiumUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class FormPageWithDataProviderTest extends TestBase {

    @BeforeMethod
    public void preSetup() {
        //screen to home page
        formPage.setActivity();
    }

    @DataProvider
    public Object[][] getDataWithValue(){

        return new Object[][] {{"Jignesh Wala","male","Brazil"},{"Shubham Wala","female","Argentina"}};
    }

    @DataProvider
    public Object[][] getDataWithJsonFile() throws IOException {

        List<HashMap<String,String>> data = AppiumUtils.getJsonData(System.getProperty("user.dir") + "\\src\\main\\java\\com\\pfa\\qa\\android\\testData\\eCommerce.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }

    @Test(dataProvider = "getDataWithValue")
    public void FillFormWithData(String name, String gender, String country) throws InterruptedException {

        AndroidActions.waiting(1000);
//        FormPage formPage = new FormPage(driver); // Initialize added in the Base Test // Not Requires as initialize already done in Base Test
        formPage.setNameField(name);
        AndroidActions.waiting(500);
        formPage.setGender(gender);
        AndroidActions.waiting(500);
        formPage.setCountrySelection(country);
        AndroidActions.waiting(500);
        formPage.submitFrom();
        AndroidActions.waiting(500);


    }

    @Test(dataProvider = "getDataWithJsonFile")
    public void FillFormWithJSONData(HashMap<String,String> input) throws InterruptedException {

        AndroidActions.waiting(1000);
//        FormPage formPage = new FormPage(driver); // Initialize added in the Base Test // Not Requires as initialize already done in Base Test
        formPage.setNameField(input.get("name"));
        AndroidActions.waiting(500);
        formPage.setGender(input.get("gender"));
        AndroidActions.waiting(500);
        formPage.setCountrySelection(input.get("country"));
        AndroidActions.waiting(500);
        formPage.submitFrom();
        AndroidActions.waiting(500);


    }
}
