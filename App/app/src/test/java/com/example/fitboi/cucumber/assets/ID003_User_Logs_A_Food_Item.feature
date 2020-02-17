Feature: Log a food item

As a User
I would like to be able to log my consumed food items
So that my personal analytics will update with accurate data and provide me with information about reaching my goals

Scenario: User successfully logs food item using search (Normal flow)

Given the User is logged in
And the database contains the desired food item
And the User selects the desired food item from the search results
When a request to log the food item is made
Then the User's calorie count is updated using the desired food item

Scenario: User fails to logs item (Error flow)

Given the User is logged in
And the database does not contain the desired food item
When a request to log the food item is made
Then the User will be unable to log the food item


