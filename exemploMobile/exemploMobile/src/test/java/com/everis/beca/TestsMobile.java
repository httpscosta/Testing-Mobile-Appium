package com.everis.beca;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestsMobile {

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
    public void validarTextosTelaInicial() {
        String title = driver.findElementById("android:id/title").getText();
        assertEquals("selendroid-test-app", title);

        String subheading = driver.findElementByXPath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.TextView[1]"
        ).getText();
        assertEquals("Hello Default Locale, Selendroid-test-app!", subheading);

        String localization = driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"l10nCD\"]/android.widget.TextView").getText();
        assertEquals("Localization (L10n) locator test", localization);
    }

    @Test
    public void interagirBotaoEn() {
        driver.findElementById("io.selendroid.testapp:id/buttonTest").click();
        driver.findElementById("android:id/button2").click();
    }

    @Test
    public void interagirBotaoChrome() {

        driver.findElementByAccessibilityId("buttonStartWebviewCD").click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        MobileElement enterName = driver.findElementByAccessibilityId("Enter your name here!");
        enterName.clear();

        driver.findElementByXPath(
                "//android.view.View[@content-desc=\"Say Hello Demo\"]/android.view.View[2]/android.widget.EditText"
        ).sendKeys("Priscila");
        driver.navigate().back();

        driver.findElementByAccessibilityId("Volvo").click();
        driver.findElementByXPath(
                "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]"
        ).click();

        driver.findElementByAccessibilityId("Send me your name!").click();

        assertTrue("Botão Send me your name! não funcionou", driver.findElementByAccessibilityId("Hello ! Heading").isDisplayed());

        driver.findElementById("io.selendroid.testapp:id/goBack").click();
    }

    @Test
    public void interagirBarraDeTextoERemoverCheckAds() {
        driver.findElementById("io.selendroid.testapp:id/my_text_field").sendKeys("Eu testo Tu testas Ele testa Nós testamos");
        driver.findElementById("io.selendroid.testapp:id/input_adds_check_box").click();

        assertTrue("Elemento não está em tela", driver.findElementById("io.selendroid.testapp:id/input_adds_check_box").isDisplayed());
    }

    @Test
    public void interagirProgressBar() {
        driver.findElementById("io.selendroid.testapp:id/waitingButtonTest").click();

        assertTrue("Barra de loading não exibida", driver.findElementById("android:id/message").isDisplayed());
    }

    @Test
    public void validarRegisterNewUser() {

        driver.findElementById("io.selendroid.testapp:id/waitingButtonTest").click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        List<MobileElement> textView = driver.findElements(By.className("android.widget.TextView"));

        for (int i = 0; i < textView.size(); i++) {
            String texto = textView.get(i).getText();
            System.out.println(texto);
        }

    }

    @After
    public void finishTest() {
        driver.closeApp();
        driver.quit();

    }
}
