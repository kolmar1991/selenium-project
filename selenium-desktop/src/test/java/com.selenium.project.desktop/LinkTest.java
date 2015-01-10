package com.selenium.project.desktop;

import com.gargoylesoftware.htmlunit.WebClient;
import com.google.common.collect.ImmutableList;
import com.selenium.project.desktop.model.ActionType;
import com.selenium.project.desktop.model.LinkTestObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.selenium.project.desktop.model.ActionType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LinkTest {
    private WebDriver driver;
    private String baseUrl;
    private final ImmutableList<LinkTestObject> linkList = new ImmutableList.Builder<LinkTestObject>()
            .add(new LinkTestObject("", "createSurvey.php", REDIRECT))
            .add(new LinkTestObject("fillSurvey.php", EXISTS))
            .add(new LinkTestObject("fillSurvey.html", 404, STATUS))
            .add(new LinkTestObject("createSurveyStep1.php", 200, STATUS))
            .build();


    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        baseUrl = "http://student.agh.edu.pl/~kolaczyk/Survey/";
    }

    @Test
    public void testLinks() throws Exception {
        for(LinkTestObject linkTestObject : linkList){
            switch (linkTestObject.getAction()){
                case EXISTS:
                    isPageExists(linkTestObject.getDestination());
                    break;

                case REDIRECT:
                    assertRedirection(linkTestObject.getDestination(), linkTestObject.getExpected());
                    break;

                case STATUS:
                    assertStatusCode(linkTestObject.getDestination(), linkTestObject.getStatusCode());
                    break;
            }

        }
    }

    private void isPageExists(String link) throws IOException {
        assertEquals(200, getStatusCode(link));
    }

    private void assertStatusCode(String link, int expectedStatusCode) throws IOException {
        assertEquals(getStatusCode(link), expectedStatusCode);
    }

    private void assertRedirection(String link, String exectedRedirection) {
        driver.get(baseUrl+link);
        assertEquals(driver.getCurrentUrl(), baseUrl+exectedRedirection);
    }

    private int getStatusCode(String link) throws IOException {
        WebClient webClient = new WebClient();
        webClient.setThrowExceptionOnFailingStatusCode(false);
        int code = webClient.getPage(baseUrl + link).getWebResponse().getStatusCode();
        webClient.closeAllWindows();
        return code;
    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
