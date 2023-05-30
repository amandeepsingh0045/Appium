package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BrowserBaseTest {

    public static AppiumDriverLocalService localAppiumServer;
   // public static AndroidDriver driver;
    public static AndroidDriver driver;

    @BeforeSuite
    public void startAppiumServer() throws MalformedURLException {
        System.out.println(String.format("Start local Appium Server"));
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        //Use any port , in case the default 4723 is already taken (maybe by another Appium Server)
        serviceBuilder.usingAnyFreePort();

        //Appium 1.x
        // localAppiumServer = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withArgument(() -> "--base-path","/wd/hub/"));

        //Appium.2.x
       localAppiumServer = AppiumDriverLocalService.buildService(serviceBuilder);

        localAppiumServer.start();
        System.out.println(String.format("Appium server started on url : '%s'", localAppiumServer.getUrl().toString()));

    }

   //@BeforeClass
    @Test
    public void startTest(){

        DesiredCapabilities dcap = new DesiredCapabilities();
        dcap.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
        dcap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        dcap.setCapability("chromedriverExecutable","C:\\Users\\AMANDEEP SINGH\\IdeaProjects\\TestAppium\\src\\test\\java\\resources\\chromedriver.exe");
        dcap.setCapability("browserName","Chrome");
        dcap.setCapability(MobileCapabilityType.NO_RESET,true);
        dcap.setCapability("chromeOptions", ImmutableMap.of("w3c",false));
        dcap.setCapability(MobileCapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        dcap.setCapability(MobileCapabilityType.ACCEPT_INSECURE_CERTS,true);
        driver = new AndroidDriver(getAppiumServerUrl(),dcap);
        driver.configuratorSetWaitForSelectorTimeout(Duration.ofSeconds(5));
        String URL = "https://"+"storefront"+":"+"hkt-2019"+"@"+ "stg-cc.hackett.com/gb/home";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL);
        driver.findElement(AppiumBy.id("details-button")).click();
        driver.findElement(AppiumBy.id("proceed-link")).click();
    }


  //  @AfterClass
    public void endTest() {


    }


    @AfterSuite
    public void stopAppiumServer() throws MalformedURLException {
        if (null != localAppiumServer) {
            System.out.println(String.format("Stopping the local Appium Server running on url : '%s'", localAppiumServer.getUrl().toString()));
            localAppiumServer.stop();
            System.out.println("Is Appium Server running ? "+ localAppiumServer.isRunning());
        }
    }

    public URL getAppiumServerUrl(){
        System.out.println("Appium server url :" + localAppiumServer.getUrl());
        return localAppiumServer.getUrl();
    }

}
	
