Feature: User deletes logged food item

As a user of the FitBoi application
I would like to be able to delete a food item I had previously logged
So that I can fix any mistakes made while logging

Scenario: User successfully deletes logged food item (Normal flow)

Given the User has at least one logged food item for the meal
When deleting a food item from the meal
Then the food item is dissociated from the meal

Scenario: User successfully deletes many or all food items in a meal (Alternate flow)

Given the User has more than one logged food items for the meal
When deleting multiple food items from the meal 
Then the food items are dissociated from the meal

Scenario: User exits the window without saving (Error flow)
Given the User has at least one logged food item for the meal
And they are indicating a food item deletion
When the User exits the current window
Then the food item is not dissociated from the meal

Scenario: Connectivity issue prevents data from persisting (Error flow)

Given the User is logged into FitBoi
And they have clicked on the profile settings menu
And the User has modified multiple values in their profile
And there is a lack of connection between the server and client
When clicking the save changes button
Then the profile changes are not saved
And the "Connectivity issues prevented saving" message is issued