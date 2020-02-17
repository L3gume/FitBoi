Feature: User creates new account

As a User of the FitBoi application
I would like to create a new user profile
So that I can use the application as intended to log my personal goals and track my fitness progress.

Scenario: User profile is created (Normal flow)

When a new User requests to create an account with valid input data
Then a new User account is created with the valid input data 

Scenario: User enters invalid data (Error flow)

When a new User requests to create an account with invalid input data
Then the new User account is not created


