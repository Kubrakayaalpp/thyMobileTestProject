package thy.test;

import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import thy.base.BaseTest;
import thy.util.BaseMethods;

import java.util.List;
import java.util.Random;


public class thySteps extends BaseMethods {

    @Step("<key> Butonuna Tikla")
    public void ButtonClick(String key) {
       clickButton(key);
    }

    @Step("<key> Textine Yazdir <text>")
    public void TextSendKeys(String key, String text) {

    }
@Step("<key> saniye bekle")
    public void bekle(int key) throws InterruptedException {
    Thread.sleep(key*1000);
}
    @Step("<key> <0> numaralı sayfanın <column> ve <row> girilir")

    public void sendkey(String key,int sheet,int col,int row) {

        MobileElement element= findElementByKey(key);

        element.sendKeys(ReadCellData(row,col,sheet));

    }


    @Step("2 gün sonrayı seç.")
   public void ikiGunSonraSec()
   {
       MobileElement mobileElement = appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='"+IkiGunSonraSec()+"']"));
       mobileElement.click();

   }


    @Step("Sayfada <key> alanı var mı ? Yoksa <mesaj>")
    public void getButtonControl(String key, String msg)
    {
        Assert.assertTrue(msg,findElementByKey(key).isDisplayed());
    }

    @Step("kaydır")
    public void kaydir()

    {
        Dimension dim = appiumDriver.manage().window().getSize();
        int height = dim.getHeight();
        int width = dim.getWidth();
        int x = width/2;
        int top_y = (int)(height*0.80);
        int bottom_y = (int)(height*0.50);
        System.out.println("coordinates :" + x + "  "+ top_y + " "+ bottom_y);
        TouchAction ts = new TouchAction(appiumDriver);
        ts.press(PointOption.point(x,top_y)).moveTo(PointOption.point(x,bottom_y)).release().perform();
    }

    @Step("<Koltuk> koltuk seçilir")
    public void RandomKoltukSec(String key) {

    }

    @Step("<key> bulana kadar kaydır.")
    public void kaydir(String key){
        int deger=0;

        Random random=new Random();


    while(deger<4){
        List<MobileElement> elements=findElementsByKey(key);

        int index=random.nextInt(elements.size());

        if(elements.size()>=2){
            elements.get(index).click();
            break;
        }
        else
        {
            swipeDown();
            deger++;
        }
    }

    }
}




