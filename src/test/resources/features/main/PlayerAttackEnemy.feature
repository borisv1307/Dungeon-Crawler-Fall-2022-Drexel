@IntegrationTest
Feature: Player combat

  Scenario: Player win the fight
    Given player fight the enemy
    When the enemy die first
    Then the player win and level up

  Scenario: Player lose the fight
    Given player fight the enemy
    When the player die first
    Then the player lose
