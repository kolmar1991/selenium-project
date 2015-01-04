package com.selenium.project;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateSurveyTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://student.agh.edu.pl/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSelenium1() throws Exception {
    driver.get(baseUrl + "/~kolaczyk/Survey/createSurveyStep1.php");
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("test");
    driver.findElement(By.cssSelector("input.btn")).click();
    driver.findElement(By.name("product[]")).clear();
    driver.findElement(By.name("product[]")).sendKeys("produkt 1");
    driver.findElement(By.name("description1")).clear();
    driver.findElement(By.name("description1")).sendKeys("produkt 1");
    driver.findElement(By.xpath("(//input[@name='product[]'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@name='product[]'])[2]")).sendKeys("produkt 2");
    driver.findElement(By.name("description2")).clear();
    driver.findElement(By.name("description2")).sendKeys("produkt 2");
    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
    driver.findElement(By.xpath("(//input[@name='product[]'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@name='product[]'])[3]")).sendKeys("produkt 3");
    driver.findElement(By.name("description3")).clear();
    driver.findElement(By.name("description3")).sendKeys("produkt3");
    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
    driver.findElement(By.xpath("(//input[@name='product[]'])[4]")).clear();
    driver.findElement(By.xpath("(//input[@name='product[]'])[4]")).sendKeys("produkt4");
    driver.findElement(By.name("description4")).clear();
    driver.findElement(By.name("description4")).sendKeys("produkt 4");
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
    driver.findElement(By.xpath("//td[4]/input")).click();
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
    driver.findElement(By.name("emails")).clear();
    driver.findElement(By.name("emails")).sendKeys("kds@o2.pl");
    driver.findElement(By.cssSelector("input.btn.btn-success")).click();
    assertEquals("Ankieta została wysłana!", driver.findElement(By.cssSelector("div.panel-body.text-center")).getText());
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
