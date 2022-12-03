@IntegrationTest
Feature: Player combat

  Scenario: Player win the fight
    Given player fight the enemy
    When the player attack first
    Then the player win and level up

  Scenario: Player lose the fight
    Given player fight the enemy
    When the player attack is lower than the enemy health
    Then the player lose
