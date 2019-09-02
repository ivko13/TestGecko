import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.html.HTMLInputElement;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class TestComputersDatabase {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private HTMLInputElement selenium;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.katalon.com/";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    //01.TC Submit empty field for all data
    @Test
    public void noDataTest() throws Exception {

        System.out.println("--- No data test ---");

        driver.get("http://computer-database.gatling.io/computers");
        System.out.println("01.Successfully open home page");

        //Click on Add a new computer button
        driver.findElement(By.id("add")).click();
        //Click on Submit button
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Company'])[1]/following::input[1]")).click();
        System.out.println("02.Click on Add a new computer button");

        WebElement requiredField = driver.findElement(By.cssSelector("div.clearfix.error"));
        System.out.println("03.Computer name field is required");

        assertNotNull(requiredField);

    }

    //02.TC Submit empty field for Company name, and enter valid data for all
    @Test
    public void doNotEnterComputersTest() throws Exception {

        System.out.println("--- Do not enter computer name test ---");

        driver.get("http://computer-database.gatling.io/computers");

        System.out.println("01.Successfully open home page");

        //Click on Add a new computer button
        driver.findElement(By.id("add")).click();

        System.out.println("02.Click on Add a new computer button");

        //Enter Introduced date
        driver.findElement(By.id("introduced")).clear();
        driver.findElement(By.id("introduced")).sendKeys("2019-09-01");

        System.out.println("03.Pick Introduced date");

        //Enter Discounted date
        driver.findElement(By.id("discontinued")).click();
        driver.findElement(By.id("discontinued")).clear();
        driver.findElement(By.id("discontinued")).sendKeys("2019-09-10");

        System.out.println("04.Pick Discontinued date");

        //Choose company company from dropdown list
        driver.findElement(By.id("company")).click();

        new Select(driver.findElement(By.id("company"))).selectByVisibleText("IBM");
        driver.findElement(By.id("company")).click();

        System.out.println("05.Choose Company");

        //Submit button create
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Company'])[1]/following::input[1]")).click();
        WebElement requiredField = driver.findElement(By.cssSelector("div.clearfix.error"));

        System.out.println("06.Click on Add a new computer button");

        assertNotNull(requiredField);

    }

    //03.TC Submit valid data for all
    @Test
    public void enterValidDataComputersTest() throws Exception {

        System.out.println("--- Enter valid data test ---");

        driver.get("http://computer-database.gatling.io/computers");

        System.out.println("01.Successfully open home page");

        //Click on Add a new computer
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("name")).click();

        System.out.println("02.Click on Add a new computer button");

        //Enter Computer name
        driver.findElement(By.id("name")).sendKeys("NikolaTest");

        System.out.println("03.Enter Name");

        //Enter Introduced date
        driver.findElement(By.id("introduced")).click();
        driver.findElement(By.id("introduced")).clear();
        driver.findElement(By.id("introduced")).sendKeys("2019-09-01");

        System.out.println("04.Pick Introduced date");

        //Enter Discounted date
        driver.findElement(By.id("discontinued")).click();
        driver.findElement(By.id("discontinued")).clear();
        driver.findElement(By.id("discontinued")).sendKeys("2019-09-10");

        System.out.println("05.Pick Discontinued date");

        //Choose company company from dropdown list
        driver.findElement(By.id("company")).click();
        new Select(driver.findElement(By.id("company"))).selectByVisibleText("IBM");
        driver.findElement(By.id("company")).click();

        System.out.println("05.Choose Company");

        //Submit button create
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Company'])[1]/following::input[1]")).click();
        WebElement alertMessage = driver.findElement(By.cssSelector(".alert-message"));

        System.out.println("06.Done! Computer has been created");

        assertNotNull(alertMessage);

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
