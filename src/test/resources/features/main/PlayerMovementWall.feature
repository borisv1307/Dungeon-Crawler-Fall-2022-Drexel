@IntegrationTest
Feature: Move the player into wall

  Scenario Outline: Move into wall
    Given the level design is:
      | 3   |
      | 3   |
      | XXX |
      | XPX |
      | XXX |
    When the player moves <direction>
    Then the player is located at (2, 2)
    Examples:
      | direction |
      | left      |
      | right     |
      | up        |
      | down      |