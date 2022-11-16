@IntegrationTest
Feature: Move the player into wall

  Background: 
    Given the level design is:
      | XXX |
      | XPX |
      | XXX |

  Scenario: Move left into wall
    When the player moves left
    Then the player is located at (2, 2)

  Scenario: Move right into wall
    When the player moves right
    Then the player is located at (2, 2)

  Scenario: Move up into wall
    When the player moves up
    Then the player is located at (2, 2)

  Scenario: Move down into wall
    When the player moves down
    Then the player is located at (2, 2)
