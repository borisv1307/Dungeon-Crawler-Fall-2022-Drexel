@UITest
Feature: Window Closing on X Button

  Scenario: Close the application by using the X button.
    Given the application is running
    When I close the application by using the X button
    Then the application is terminated
