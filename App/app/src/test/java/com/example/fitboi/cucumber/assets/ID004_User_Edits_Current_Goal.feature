Feature: User edits a current goal

As a User of the FitBoi application
I would like to edit an outstanding goal associated to my profile
So that I can better adapt my goals to my current progress

Scenario: User goal is edited (Normal flow)

Given the User goal is created
And the goal is selected
And changes made to the goal targets are valid
When clicking the update goal button
Then the goal is updated and persisted to the database
And the "Successful goal update" message is issued

Scenario: User cannot perform updates (Alternate flow)

Given a User in on the goal page
And no goals are available
Then no information is accessible

Scenario: User enters invalid goal targets (Error flow)

Given the User goal is created
And the goal is selected
And changes made to the goal targets are invalid
When clicking the update goal button
Then the goal is not updated
And the "Invalid updates" message is issued

Scenario: User does not save updates (Error flow)

Given the User goal is created
And the goal is selected
And changes are made to the goal targets
When clicking the exit button
Then the goal is not updated
And the "Updates cancelled" message is issued

