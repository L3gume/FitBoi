Feature: User sees daily consumption against goal

As a User of the FitBoi application
I would like to see my current caloric for the current date versus my current goal
So that I can gauge what I should consume for the remainder of the day

Scenario: User views current consumption (Normal flow)

Given the User is on the main dashboard
When viewing the screen
Then the daily caloric consumtion values are displayed

Scenario: User cannot perform updates (Alternate flow)

Given the User is on the main dashboard
When viewing the screen
Then the daily caloric consumtion values are not displayed

Scenario: User cannot fetch information from server  (Error flow)

Given the User is on the main dashboard
When the connection between User and server is lost
Then no information is displayed
