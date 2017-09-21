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


//sample test for desktop browser site
public class Test_TC001 {

    @Test(dataProvider = "SPECS") //intakes the data from data provider
    public void testCloudServices(Platform platformName, String browserName) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities(); // instantiates the capability
        capabilities.setPlatform(platformName); //sets platformname
        capabilities.setBrowserName(browserName); //sets browsername
        capabilities.setCapability("browserstack.debug", "true");  //sets the browserstack debug true

        String stringUrl = "https://username:secretkey@hub-cloud.browserstack.com/wd/hub";
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

    @DataProvider(name="SPECS", parallel = true)  //sets a dataprovider for testng test
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
    }
}


