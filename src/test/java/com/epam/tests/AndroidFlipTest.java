package com.epam.tests;

import com.epam.config.TestData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Артур.
 */
public class AndroidFlipTest {

    private static AppiumDriver<WebElement> driver;

    private static TestData testData;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        File app = new File("src/test/resources/apps", "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "io.appium.android.apis");
        capabilities.setCapability("appActivity", ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://10.27.11.141:4444/wd/hub"), capabilities);
        testData = new TestData();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testFlipPage() {
        driver.findElementByXPath(".//*[@text='Animation']").click();
        driver.findElementById("android:id/list")
                .findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"View Flip\"));"))
                .click();
        List<String> numbers = new ArrayList<>();

        List<WebElement> elementsList = driver.findElementsById("android:id/text1");
        for (WebElement number : elementsList) {
            numbers.add(number.getText());
        }
        Assert.assertEquals(numbers, testData.getEnglishNumbers());
    }

    @Test(dependsOnMethods = "testFlipPage")
    public void testFirstFlip() {
        driver.findElementByXPath(".//*[@text='Flip']").click();
        List<String> numbers = new ArrayList<>();
        List<WebElement> elementsList = driver.findElementsById("android:id/text1");
        for (WebElement number : elementsList) {
            numbers.add(number.getText());
        }
        Assert.assertEquals(numbers, testData.getFrenchNumbers());
    }

    @Test(dependsOnMethods = "testFirstFlip")
    public void testSecondFlip() {
        driver.findElementByXPath(".//*[@text='Flip']").click();
        List<String> numbers = new ArrayList<>();
        List<WebElement> elementsList = driver.findElementsById("android:id/text1");
        for (WebElement number : elementsList) {
            numbers.add(number.getText());
        }
        Assert.assertEquals(numbers, testData.getEnglishNumbers());
    }
}
