package com.browserstack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.browserstack.utils.WebDriverUtils;

public class HomePage {
	
	 private WebDriver webDriver;
	 
	// Web Elements Definition
	 
		@FindBy(id = "signin")
		private WebElement signin;
	 
	    @FindBy(id = "react-select-2-input")
	    private WebElement userName_input;
	    
	    @FindBy(id = "react-select-3-input")
	    private WebElement password_input;

	    @FindBy(id = "login-btn")
	    private WebElement loginButton;
	    
	    @FindBy(id = "logout")
	    private WebElement logout;

	    private WebElement filter;

	    //@FindBy(xpath = "//div[@class='shelf-item'][.//p[@class='shelf-item__title' and text()='Galaxy S20+']]//div[@class='shelf-stopper']//button")
	    public WebElement favIcon;

	    @FindBy(id = "favourites")
	    private WebElement favLink;
	    
	    @FindBy(xpath = "//div[@class='shelf-container-header']//span")
	    private WebElement productFoundMessage;

	    private WebElement favProduct;
	    
	    // Constructor
	    public HomePage(WebDriver driver) {
	        this.webDriver = driver;
	        PageFactory.initElements(webDriver, this);
	    }

	    // Actions
	    
		 // Setter method to set the filter based on the the test value of filter for the products
		    private void setFilterByText(String filterText) {
		        String dynamicXPath = "//span[@class='checkmark' and text()='" + filterText + "']";
		        filter = webDriver.findElement(By.xpath(dynamicXPath));
		    }
		    
		    // Setter method to select favourite based on the the test value of the model
		    private void setFavouriteByModel(String ModelName) {
		        String dynamicXPath = "//div[@class='shelf-item']//p[@class='shelf-item__title' and text()='" + ModelName + "']/parent::div/div[@class = 'shelf-stopper']//button";
		        favIcon = webDriver.findElement(By.xpath(dynamicXPath));
		    }
		    
		    public void signIn(String username, String password)
		    {
		    	signin.click();
		    	WebDriverUtils.setImplicitWait(webDriver, 5);
		    	enterUserName(username);
		    	enterPassword(password);
		    	clickLoginButton();
		    }
		    
		    private void enterUserName(String username) {
		    	userName_input.sendKeys(username);
		    	userName_input.sendKeys(Keys.RETURN);
		    	WebDriverUtils.setImplicitWait(webDriver, 2);
		    }

		    private void enterPassword(String password) {
		        password_input.sendKeys(password);
		        password_input.sendKeys(Keys.RETURN);
		        WebDriverUtils.setImplicitWait(webDriver, 2);
		    }

		    public void clickLoginButton() {
		        loginButton.click();
		    }

		    public void setFilter(String filtertext) {
		    	setFilterByText(filtertext);
		    	filter.click();
		    }
		    
		    public void setFavourite(String modelname)
		    {
		    	setFavouriteByModel(modelname);
		    	favIcon.click();
		    }

		    public void navigateToFavourites() {
		         favLink.click();
		    }
		    
		    public String getFavouriteProductFoundText() {
		    	return productFoundMessage.getText();
		    }
		    
		    // Getter for Favourite product in the Favourites list
		    public String getFavourite(String ModelName) {
		        String dynamicXPath = "//p[@class='shelf-item__title' and text()='" + ModelName + "']";
		        favProduct = webDriver.findElement(By.xpath(dynamicXPath));
		        return favProduct.getText();
		    }

		    public void logout() {
		        logout.click();
		    }

}
