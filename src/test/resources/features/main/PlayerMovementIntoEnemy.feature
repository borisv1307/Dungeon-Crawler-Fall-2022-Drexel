@IntegrationTest
Feature: Move the player into enemy

  Scenario: Move player right into enemy
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | X PEX |
      | X   X |
      | XXXXX |
    When the player moves right
    Then level 1 is generated
    And win count is 0
    And lose count is 1
    And the player is located at (3, 3)
    And the enemy is located at (4, 3)

  Scenario: Move player left into enemy
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | XEP X |
      | X   X |
      | XXXXX |
    When the player moves left
    Then level 1 is generated
    And win count is 0
    And lose count is 1
    And the player is located at (3, 3)
    And the enemy is located at (2, 3)

  Scenario: Move player up into enemy
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X E X |
      | X P X |
      | X   X |
      | XXXXX |
    When the player moves up
    Then level 1 is generated
    And win count is 0
    And lose count is 1
    And the player is located at (3, 3)
    And the enemy is located at (3, 2)

  Scenario: Move player down into enemy
    Given the level 1 design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | X P X |
      | X E X |
      | XXXXX |
    When the player moves down
    Then level 1 is generated
    And win count is 0
    And lose count is 1
    And the player is located at (3, 3)
    And the enemy is located at (3, 4)