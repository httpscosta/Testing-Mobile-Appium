package com.everis.beca;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;
import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class ExemploAndroid {

        private AppiumDriver<MobileElement> driver;

        @Before
        public void setup() throws MalformedURLException {
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator_container");
                String appFile = "C:\\Users\\psantoma\\Downloads\\selendroid-test-app-0.17.0.apk";
                cap.setCapability(MobileCapabilityType.APP, appFile);
                cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
                cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");
                cap.setCapability("automationName", "UiAutomator1");
                driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        }

        @Test
        public void simpleTest() {
                driver.findElementById("io.selendroid.testapp:id/my_text_field").sendKeys("Hello World!!");
                driver.findElementById("io.selendroid.testapp:id/input_adds_check_box").click();
                String result = driver.findElementById("io.selendroid.testapp:id/input_adds_check_box").getText();
                System.out.println("\n\n===> " + result + "\n\n");
                assertEquals("I accept adds", result);
        }

        @After
        public void finishTest() {
                driver.closeApp();
                driver.quit();
        }
}