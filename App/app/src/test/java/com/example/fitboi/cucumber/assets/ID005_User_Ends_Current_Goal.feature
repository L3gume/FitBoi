Feature: User ends a current goal

As a User of the FitBoi application
I would like to end an outstanding goal associated to my profile
So that I can start a new goal to better track my future fitness trajectory 

Scenario: User goal is ended (Normal flow)

Given the User goal is created
And the goal is selected
When clicking the end button
Then the goal is archived and persisted to the database
And the "Finished goal" message is issued

Scenario: User cannot perform updates (Alternate flow)

Given a User in on the goal page
And no goals are available
Then no information is accessible

Scenario: User losses connection to goal (Error flow)

Given the User goal is created
And the goal is selected
And connection is between User and server is dropped
When clicking the end button
Then the goal is not updated
And the "Cannot update goal" message is issue

