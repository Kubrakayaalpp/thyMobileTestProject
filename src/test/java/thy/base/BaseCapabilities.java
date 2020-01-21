package thy.base;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseCapabilities {

    DesiredCapabilities capabilities = new DesiredCapabilities();

    private String app_package="com.turkishairlines.mobile";
    private String app_activity="com.turkishairlines.mobile.ui.ACSplash";

    public DesiredCapabilities setLocalAndroidCapabilities() {


        capabilities
                .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy J7 Prime");
        capabilities
                .setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                        app_package);
        capabilities
                .setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                        app_activity);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
        capabilities.setCapability("unicodeKeyboard", false);
        capabilities.setCapability("resetKeyboard", false);
        capabilities.setCapability("newCommandTimeout","240");
        capabilities.setCapability("autoAcceptAlerts",true);
        return capabilities;
    }

    public DesiredCapabilities setLocalIOSCapabilities(){

        capabilities
                .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities
                .setCapability(MobileCapabilityType.UDID, "");
        capabilities
                .setCapability(IOSMobileCapabilityType.BUNDLE_ID, "");
        capabilities
                .setCapability(MobileCapabilityType.DEVICE_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
        capabilities.setCapability("sendKeyStrategy", "setValue");
        capabilities.setCapability("newCommandTimeout","");

        return capabilities;
    }
    public DesiredCapabilities setRemoteAndroidCapabilities() {

        capabilities.setCapability("key", System.getenv("key"));
        capabilities
                .setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                        "");
        capabilities
                .setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                        "");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability("unicodeKeyboard", false);
        capabilities.setCapability("resetKeyboard", false);
        capabilities.setCapability("newCommandTimeout","");
        return capabilities;
    }
    public DesiredCapabilities setRemoteIOSCapabilities(){

        capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.MAC);
        capabilities.setCapability("usePrebuiltWDA", true);
        capabilities.setCapability("key", System.getenv("key"));
        capabilities.setCapability("waitForAppScript", "");
        capabilities.setCapability("bundleId", "");
        capabilities.setCapability("newCommandTimeout","");
        return capabilities;
    }
}
