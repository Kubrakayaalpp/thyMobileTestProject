package thy.util;


import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import thy.base.BaseTest;
import thy.helper.StoreHelper;
import thy.model.ElementInfo;
import thy.model.SelectorInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class BaseMethods extends BaseTest {
    public WebDriverWait wait = new WebDriverWait(appiumDriver, 20);
    final static Logger logger = Logger.getLogger(BaseMethods.class);

    public MobileElement findElementByKey(String key) {

        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        By by = selectorInfo.getBy();
        MobileElement element;
        try {
            logger.info("findElement method called:  finding " + key);
            element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception ex) {
            Assert.fail(key + " sayfada görüntülenemedi!!!");
            throw ex;
        }
        return element;
    }

    public List<MobileElement> findElementsByKey(String key) {

        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        List<MobileElement> mobileElements = null;
        try {
            logger.info("findElements method called:  finding" + key);
            wait.until(ExpectedConditions.presenceOfElementLocated(selectorInfo.getBy()));
            mobileElements = appiumDriver.findElements(selectorInfo.getBy());
        } catch (Exception ex) {
            Assert.fail(key + " sayfada görüntülenemedi!!!");
            throw ex;
        }
        return mobileElements;
    }

    public void clickButton(String key){
        WebElement element=findElementByKey(key);
        element.click();

    }

    public void sendKey(String key, String value)
    {
        WebElement element=findElementByKey(key);
        element.sendKeys(value);
    }

    public static void wait (int sure){

        try {
            Thread.sleep(sure*1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public String ReadCellData(int vRow, int vColumn ,int sayfaNumarasi)

    {

        Workbook wb=null;

        try

        {

            FileInputStream fis=new FileInputStream("/Users/testinium/Desktop/THY_PROJECT/ExcelDosyası/thy_excel.xlsx");

            wb=new XSSFWorkbook(fis);

        }

        catch(FileNotFoundException e)

        {

            e.printStackTrace();

        }

        catch(IOException e1)

        {

            e1.printStackTrace();

        }

        Sheet sheet=wb.getSheetAt(sayfaNumarasi);

        DataFormatter formatter = new DataFormatter();

        Cell cell = sheet.getRow(vRow).getCell(vColumn);

        String j_username = formatter.formatCellValue(cell);

        return j_username;

    }

    public String IkiGunSonraSec()
    {
        Calendar today=Calendar.getInstance();
        int date=today.get(Calendar.DATE)+2;
        return String.valueOf(date);

    }

    public void RandomKoltukSec(String key)
    {
        List<MobileElement> elements=findElementsByKey(key);
        Random random=new Random();
        int index=random.nextInt(elements.size());
        elements.get(index).click();

    }

    public void swipeDown() {
        if (appiumDriver instanceof AndroidDriver) {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 80) / 100;
            int swipeEndHeight = (height * 40) / 100;

            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release().perform();

        }
    }

}
