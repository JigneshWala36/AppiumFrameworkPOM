package com.pfa.qa.android.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfa.qa.android.base.TestBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils extends TestBase {

    public static AppiumDriverLocalService appService;


    public static AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        appService = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\jignesh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress).usingPort(port).build();
        appService.start();
        return appService;
    }
    public static String getScreenshot(String imageName) throws IOException, IOException {

        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "./screenshot/" + imageName;
        FileUtils.copyFile(source, new File(destinationFile));
        System.out.println(destinationFile);
        return destinationFile;
    }

    public static String convertImg_Base64(String screenshotPath) throws IOException {

        byte[] file = FileUtils.readFileToByteArray(new File(screenshotPath));
        String base64Img = Base64.encodeBase64String(file);
        return base64Img;
    }

    public Double getFormattedAmount(String amount) {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public static List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
//       System.getProperty("user.dir") + "\\src\\main\\java\\com\\pfa\\qa\\android\\testData\\eCommerce.json");

//		 convert json file content to json string
        @SuppressWarnings("NewApi") String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }



    public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
        @SuppressWarnings("NewApi") WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }

    //    Broken Image getting generated on  Extent Report for the below method
    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        // Create a new Folder ->
        String destinationFile = System.getProperty("user.dir") + "./screenshot/" + testCaseName + ".jpg";
//		String destinationFile = System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
//
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
        //1. capture and place in folder //2. extent report pick file and attach to report
    }


}
