@IntegrationTest
Feature: Create a Level with randomly generated objects

  Scenario: Randomly generated level
    Given I have a seed of 0
    When I randomly generate level 1 with an x of 5 and a y of 5
    Then starting from the top-left:
    And the player's x coordinate is 2
    And the player's y coordinate is 3
    And the goal's x coordinate is 4
    And the goal's y coordinate is 3
    And the enemy's x coordinate is 3
    And the enemy's y coordinate is 2
    And (1, 1) is "X"
    And (2, 1) is "X"
    And (3, 1) is "X"
    And (4, 1) is "X"
    And (5, 1) is "X"
    And (1, 2) is "X"
    And (2, 2) is " "
    And (3, 2) is "E"
    And (4, 2) is " "
    And (5, 2) is "X"
    And (1, 3) is "X"
    And (2, 3) is "P"
    And (3, 3) is " "
    And (4, 3) is "G"
    And (5, 3) is "X"
    And (1, 4) is "X"
    And (2, 4) is " "
    And (3, 4) is " "
    And (4, 4) is " "
    And (5, 4) is "X"
    And (1, 5) is "X"
    And (2, 5) is "X"
    And (3, 5) is "X"
    And (4, 5) is "X"
    And (5, 5) is "X"

  Scenario: Randomly generated level with a seed and dimensions that causes Goal location collision on first coordinate generated
    Given I have a seed of 4
    When I randomly generate level 1 with an x of 3 and a y of 5
    Then starting from the top-left:
    And the player's x coordinate is 2
    And the player's y coordinate is 3
    And the goal's x coordinate is 2
    And the goal's y coordinate is 2
    And the enemy's x coordinate is 2
    And the enemy's y coordinate is 4
    And (1, 1) is "X"
    And (2, 1) is "X"
    And (3, 1) is "X"
    And (1, 2) is "X"
    And (2, 2) is "G"
    And (3, 2) is "X"
    And (1, 3) is "X"
    And (2, 3) is "P"
    And (3, 3) is "X"
    And (1, 4) is "X"
    And (2, 4) is "E"
    And (3, 4) is "X"
    And (1, 5) is "X"
    And (2, 5) is "X"
    And (3, 5) is "X"

  Scenario: Randomly generate level 1 and level 2 with a seed where player, goal, and enemy are different
    Given I have a seed of 8
    When I randomly generate level 1 with an x of 5 and a y of 5
    And I randomly generate level 2
    Then the enemy locations are different
    And the goal locations are different
    And the player locations are different


  Scenario: Randomly generate level 1 twice with the same seed produces the same level
    Given I have a seed of 8
    When I randomly generate level 1 with an x of 5 and a y of 5
    And I randomly generate level 1
    Then the enemy locations are the same
    And the goal locations are the same
    And the player locations are the same