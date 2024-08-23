Feature: Favorite a Product on bstackdemo.com

  Background: 
    Given the user navigates to "https://www.bstackdemo.com/"
    And user login with username "demouser" and password "testingisfun99"

  Scenario Outline: Filter products and favorite a specific model
    When user filter the product view to show "<ProductFilter>" devices only
    And user favorite the "<Model>" device by clicking the yellow heart icon
    Then user verify that the "<Model>" device is listed on the Favorites page

    Examples: 
      | ProductFilter | Model       |
      | Samsung       | Galaxy S20+ |
