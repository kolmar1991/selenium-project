package com.selenium.project.android;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CreateSurveyTest {
  private SelendroidLauncher selendroidServer = null;
  private WebDriver driver = null;
  private String baseUrl = "http://student.agh.edu.pl/";

  @Test
  public void createSurvey() {
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
      driver.quit();
  }

  @Before
  public void setUp() throws Exception {
    if (selendroidServer != null) {
      selendroidServer.stopSelendroid();
    }
    SelendroidConfiguration config = new SelendroidConfiguration();

    selendroidServer = new SelendroidLauncher(config);
    selendroidServer.launchSelendroid();

    DesiredCapabilities caps = SelendroidCapabilities.android();

    driver = new SelendroidDriver(caps);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @After
  public void shutDown() {
    if (driver != null) {
      driver.quit();
    }
    if (selendroidServer != null) {
      selendroidServer.stopSelendroid();
    }
  }
}
