@Ignore
Feature: Move the enemy into player

  Scenario: Move enemy right into player
    Given the level design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | X EPX |
      | X   X |
      | XXXXX |
    When the player moves right
    Then level 1 is generated
    And win count is 0
    And lose count is 1

  Scenario: Move enemy left into player
    Given the level design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | XPE X |
      | X   X |
      | XXXXX |
    When the enemy moves left
    Then level 1 is generated
    And win count is 0
    And lose count is 1

  Scenario: Move enemy up into player
    Given the level design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X P X |
      | X E X |
      | X   X |
      | XXXXX |
    When the enemy moves up
    Then level 1 is generated
    And win count is 0
    And lose count is 1

  Scenario: Move enemy down into player
    Given the level design is:
      | 5     |
      | 5     |
      | XXXXX |
      | X   X |
      | X E X |
      | X P X |
      | XXXXX |
    When the enemy moves down
    Then level 1 is generated
    And win count is 0
    And lose count is 1