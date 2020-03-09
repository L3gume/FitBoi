Feature: User searches for food item

As a user of the FitBoi application
I would like to be able to search for food items
So that I can log meals

Scenario: User enters a valid food item

Given the User is logged into FitBoi
And they entered some data to search for
When searching for the item
Then the search query is sent
And a response is received

Scenario: User enters no food item

Given the User is logged into FitBoi
And they did not enter any data to search for
When searching for the item
Then the search query is not sent
And no response is received
