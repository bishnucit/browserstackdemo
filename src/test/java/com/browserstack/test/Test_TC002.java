package com.browserstack.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;


//Sample Test for mobile browser site
public class Test_TC002 {

    @Test//(dataProvider = "SPECS") //intakes the data from data provider
    public void testCloudServices() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities(); // instantiates the capability
        capabilities.setCapability("browserstack.appium_version", "1.6.3");
        capabilities.setCapability("realMobile", "true");
        capabilities.setCapability("deviceName", "iPhone 7");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("os_version", "10.3");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability("name", "iPhone7 test");

        capabilities.setCapability("browserstack.debug", "true");  //sets the browserstack debug true

        String stringUrl = "https://username:passkey@hub-cloud.browserstack.com/wd/hub";
        URL serverUrl = new URL(stringUrl); //make the string for url using a url instance


        WebDriver driver = new RemoteWebDriver(serverUrl, capabilities); //serve the remotedriver with link and capabilities
        driver.get("http://www.google.com"); // go to the test link
        WebElement element = driver.findElement(By.name("q"));  //search for the text box.

        element.sendKeys("BrowserStack"); //send a text
        element.submit();  //search for the text

        String pageUrl = driver.getCurrentUrl(); //get the current url after searching


        Assert.assertTrue(pageUrl.contains("BrowserStack"));  //verify the search text is in the url.
        driver.quit();
    }

/*    @DataProvider(name="SPECS", parallel = true)  //sets a dataprovider for testng test
    public Object[][] getSpecs(){
        Object[][] testData = new Object[][] {
                {Platform.WIN8, "firefox"},
                {Platform.MAC, "firefox"},
                {Platform.XP, "firefox"},
                {Platform.WIN8, "chrome"},
                {Platform.MAC, "chrome"},
                {Platform.XP, "chrome"},


        };
        return testData;  //returns the set of data
    }*/
}


