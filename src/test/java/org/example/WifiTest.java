package org.example;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class WifiTest extends BaseTest {
    @Test
    public void AppiumTest() throws MalformedURLException {


        // xpaths, id , accessibilityId , classname , AndroidUiAutomator is only supported in appium
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        System.out.println("Successfully clicked on the Preference Button");
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String alertText =driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertText,"WiFi settings");
        System.out.println("Enter Wifi Name :");
        driver.findElement(By.id("android:id/edit")).sendKeys("AmanWifi");
        driver.findElement(By.id("android:id/button1")).click();

    }
}
