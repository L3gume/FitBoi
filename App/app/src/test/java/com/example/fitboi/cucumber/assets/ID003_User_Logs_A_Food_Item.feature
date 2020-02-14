Feature: Log a food item

As a user
I would like to be able to log my consumed food items
So that my personal analytics will update with accurate data and provide me with information about reaching my goals

Scenario: User sucessfully logs item using most recents (Normal flow)

Given the User is logged in
And the dersired food item is in the database
And the User selects the desired food item from the most recents
When requesting the addition of a new food item
Then the calories of the food item will be added to the total calorie count
And the "Item successfully added" message will be displayed

Scenario: User sucessfully logs item using search (Normal flow)

Given the User is logged in
And the dersired food item is in the database
And the User selects the desired food item from the search results
When requesting the addition of a new food item
Then the calories of the food item will be added to the total calorie count
And the "Item successfully added" message will be displayed

Scenario: User sucessfully logs item using substitute (Alternate flow)

Given the User is logged in
But selects a substitute food item to replace the desired food item
When requesting the addition of a new food item
Then the calories of the substitute food item will be added to the total calorie count
And the "Item successfully added" message will be displayed

Scenario: User fails to logs item (Error flow)

Given the User is logged in
But the desired food item is not in the database
And the User cancels the request for a food item
When requesting the addition of a new food item
Then the User will see the dashboard


