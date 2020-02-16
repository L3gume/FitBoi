Feature: User sets a new goal

As a user of the FitBoi application
I would like to specify my preferred fitness goal
So that FitBoi can tailor information to me and provide accurate analytics

Scenario: User successfully adds a preset goal with no current goal in progress (Normal flow)

Given the User is on the goals dashboard
And there is no goal currently in progress
And the User has selected one of three preset goals
When clicking the set goal button
Then the User adds a goal to their profile
And the "Successfully added new goal" message is issued

Scenario: User successfully adds a preset goal with a current goal in progress (Normal flow)

Given the User is on the goals dashboard
And there is a goal currently in progress
And the User has selected one of three preset goals
When clicking the set goal button
Then the User is given a prompt informing that they will override their current goal
And the User clicks the confirm button
Then the User adds a goal to their profile
And the "Successfully added new goal" message is issued

Scenario: User successfully adds a custom goal with no current goal in progress (Alternate flow)

Given the User is on the goals dashboard
And there is no goal currently in progress
And the User has entered a custom goal
When clicking the set goal button
Then the User adds a goal to their profile
And the "Successfully added new custom goal" message is issued

Scenario: User successfully adds a custom goal with a current goal in progress (Alternate flow)

Given the User is on the goals dashboard
And there is a goal currently in progress
And the User has entered a custom goal
When clicking the set goal button
Then the User is given a prompt informing that they will override their current goal
And the User clicks the confirm button
Then the User adds a goal to their profile
And the "Successfully added new custom goal" message is issued

Scenario: User enters invalid data (Error flow)

Given the User is on the goals dashboard
And the User has entered a custom goal
And the data is invalid
When clicking the set goal button
Then a User adds a goal to their profile
And the User is given a prompt informing that they will override their current goal
And the "Input invalid" message is issued