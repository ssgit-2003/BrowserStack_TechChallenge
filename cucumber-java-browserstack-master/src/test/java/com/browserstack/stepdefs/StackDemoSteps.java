package com.browserstack.stepdefs;

import com.browserstack.pageobjects.HomePage;
import com.browserstack.utils.WebDriverUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.testng.Assert;

public class StackDemoSteps {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() throws MalformedURLException {
    	
   // Load BrowserStack credentials
        String username = "satyamsharma_bOU1C6"; 
        String accessKey = "9DRKFzx6AcPX8QKeCuxS"; 

        
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.putIfAbsent("source", "cucumber-java:sample-master:v1.2");
        capabilities.setCapability("bstack:options", bstackOptions);
        
        //Update URL to include credentials
        String browserStackUrl = "https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub";
        driver = new RemoteWebDriver(new URL(browserStackUrl), capabilities);

        homePage = new HomePage(driver);
    }

    @Given("the user navigates to {string}")
    public void the_user_navigates_to(String url) {
        driver.get(url);
    }

    @Given("user login with username {string} and password {string}")
    public void user_login_with_username_and_password(String username, String password) {
    	homePage.signIn(username, password);
    	//System.out.println("User Logged in Successfully");
    }

    @When("user filter the product view to show {string} devices only")
    public void user_filter_the_product_view_to_show_devices_only(String productFilter) {
    	WebDriverUtils.setImplicitWait(driver, 2);
    	homePage.setFilter(productFilter);
    	//System.out.println(productFilter + " Filtered successfully");
    }

    @When("user favorite the {string} device by clicking the yellow heart icon")
    public void user_favorite_the_device_by_clicking_the_yellow_heart_icon(String model) {
    	WebDriverUtils.setImplicitWait(driver, 2);
    	homePage.setFavourite(model);
    	//System.out.println(model + " Added to Favourites successfully");
    }

    @Then("user verify that the {string} device is listed on the Favorites page")
    public void user_verify_that_the_device_is_listed_on_the_favorites_page(String model) {
    	homePage.navigateToFavourites();
    	WebDriverUtils.setImplicitWait(driver, 2);
    	 String favouriteProductText = homePage.getFavourite(model);
    	 //System.out.println(favouriteProductText + " is available under Favourites");
         // Assert that the text contains the model
         Assert.assertTrue(favouriteProductText.contains(model));

    }

    @After
    public void teardown(Scenario scenario) throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
