Feature: User edits a current goal

As a User of the FitBoi application
I would like to edit an outstanding goal associated to my profile
So that I can better adapt my goals to my current progress

Scenario: User goal is edited (Normal flow)

Given the User goal exists
When valid target goals are entered
Then the goal is updated and persisted to the database

Scenario: User enters invalid goal targets (Error flow)

Given the User goal exists
When invalid target goals are entered
Then the goal is not updated
