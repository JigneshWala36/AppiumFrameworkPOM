package com.pfa.qa.android.base;

import com.aventstack.extentreports.ExtentReports;
import com.pfa.qa.android.pages.FormPage;
import com.pfa.qa.android.util.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    public static AppiumDriverLocalService appService;
    public static AndroidDriver driver;
    public static Properties prop;
    public static ExtentReports extentReports;
    public static FormPage formPage;
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\pfa\\qa\\android\\config\\config.properties");
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("NewApi")
    @BeforeClass(alwaysRun = true)
    public static void configureAppium() throws MalformedURLException {
//        appService = new AppiumServiceBuilder()
//                .withAppiumJS(new File("C:\\Users\\jignesh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//                .withIPAddress("127.0.0.1").usingPort(4723).build();
//        appService.start();
        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
        appService = AppiumUtils.startAppiumServer(ipAddress, Integer.parseInt(prop.getProperty("port")));
        UiAutomator2Options uiOptions = new UiAutomator2Options();
        uiOptions.setDeviceName(prop.getProperty("emulatorName"));  //emulator
//        uiOptions.setDeviceName("Android Device");                // real device
/*   ----------------------------------------VVIP------------------------------------
     Autodownload chrome version of the mobile in CMD
     appium --allow-insecure chromedriver_autodownload
*/
        uiOptions.setApp(System.getProperty("user.dir") + "\\src\\main\\java\\com\\pfa\\qa\\android\\resources\\General-Store.apk");
//        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), uiOptions);
        driver = new AndroidDriver(appService.getUrl(), uiOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        formPage = new FormPage(driver);
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        appService.stop();
    }
}
