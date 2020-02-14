Feature: User adds custom food item to database

As a User of the FitBoi application
I would like to be able to add new foods to the database, 
So I can personalize my items if what I am looking for does not appear in the search.

Scenario: User views current consumption (Normal flow)

Given the User is on the main dashboard
And the add custom item button is pressed 
And the food item descriptions are added for all fields
And the descriptions are valid
When the item is submitted
Then the item placed in a database to be approved
And the message "Item submitted" is displayed

Scenario: User submits an incomplete food item  (Error flow)

Given the User is on the main dashboard
And the add custom item button is pressed 
And the food item descriptions are added for not all fields
When the item is submitted
Then an error message "Incomplete form" is displayed
And the item is not sent to the database

Scenario: User submits an incomplete food item  (Error flow)

Given the User is on the main dashboard
And the add custom item button is pressed 
And the food item descriptions are added for all fields
And a description is invalid
When the item is submitted
Then an error message "Invalid form data" is displayed
And the item is not sent to the database

