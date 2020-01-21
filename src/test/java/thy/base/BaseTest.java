package thy.base;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import thy.selector.Selector;
import thy.selector.SelectorFactory;
import thy.selector.SelectorType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static final String LOCALHUB = "http://127.0.0.1:4723/wd/hub";
    String TESTINIUMHUB = "http://hub.testinium.io/wd/hub";

    protected static AppiumDriver<MobileElement> appiumDriver;
    protected static FluentWait<AppiumDriver> appiumFluentWait;
    protected static Selector selector;
    protected boolean localAndroid = true;
    BaseCapabilities capabilities = new BaseCapabilities();


    @BeforeScenario
    public void beforeScenario() throws MalformedURLException {


        if (StringUtils.isEmpty(System.getenv("key"))) {
            if (localAndroid) {
                appiumDriver = new AndroidDriver<>(new URL(LOCALHUB), capabilities.setLocalAndroidCapabilities());
            } else {
                appiumDriver = new IOSDriver<>(new URL(LOCALHUB), capabilities.setLocalIOSCapabilities());
            }

        } else {
            if (System.getenv("platform").equals("ANDROID")) {

                appiumDriver = new AndroidDriver<>(new URL(TESTINIUMHUB), capabilities.setRemoteAndroidCapabilities());
                localAndroid = true;
            } else {
                appiumDriver = new IOSDriver<>(new URL(TESTINIUMHUB), capabilities.setRemoteIOSCapabilities());
                localAndroid = false;
            }
        }
        selector = SelectorFactory
                .createElementHelper(localAndroid ? SelectorType.ANDROID : SelectorType.IOS);
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        appiumFluentWait = new FluentWait(appiumDriver);
        appiumFluentWait.withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(250, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @AfterScenario
    public void afterScenario() {
        if (appiumDriver != null)
            appiumDriver.quit();
    }


}
