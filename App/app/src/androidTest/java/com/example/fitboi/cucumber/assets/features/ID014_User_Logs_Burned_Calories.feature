Feature: User logs burned calories

As a user of the FitBoi application
I would like to input my calories burned during the day
So that my daily caloric balance is accurate and exercise is accounted for if necessary

Scenario: User successfully adds burned calories for first time that day (Normal flow)

Given the User is logged into FitBoi
And the User has entered burned calories to add to their day
And the User decides to add the entered calories to their day
Then the calories are added to the total burnt calorie count for that day

Scenario: User successfully sets burned calories for first time that day (Alternative flow)

Given the User is logged into FitBoi
And the User has entered burned calories to set as their day total
And the User decides to set the entered calories to their day
Then the calories are set as the total burnt calorie count for that day

Scenario: User enters invalid data to add to calorie count (Error flow)

Given the User is logged into FitBoi
And the User has entered invalid data in the burned calories to add to their day
And the User decides to add the entered calories to their day
Then the calories are not added to the total burnt calorie count for that day

Scenario: User enters invalid data to set as calorie count (Error flow)

Given the User is logged into FitBoi
And the User has entered invalid data in the burned calories to set to their day
And the User decides to set the entered calories to their day
Then the calories are not set as the total burnt calorie count for that day