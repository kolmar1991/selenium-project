package com.selenium.project.desktop;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

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
  public void createSurvey() throws Exception {
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
    assertEquals("Wykluczenia", driver.findElement(By.cssSelector("div.panel-heading")).getText());
    driver.findElement(By.xpath("//td[4]/input")).click();
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
    assertEquals("Adresaci", driver.findElement(By.cssSelector("div.panel-heading")).getText());
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
}
