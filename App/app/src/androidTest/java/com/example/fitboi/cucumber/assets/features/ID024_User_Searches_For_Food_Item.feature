Feature: User searches for food item

As a user of the FitBoi application
I would like to be able to search for food items
So that I can log meals

Scenario: User enters a valid food item

Given the User is logged into FitBoi
And they entered an existing food item
When searching for the item
Then the search query is sent
And an response is received

Scenario: User enters no food item

Given the User is logged into FitBoi
And they did not enter a food item
When searching for the item
Then search query is not sent
And no response is received
