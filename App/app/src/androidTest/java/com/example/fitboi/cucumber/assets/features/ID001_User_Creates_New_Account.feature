Feature: User creates new account

As a User of the FitBoi application
I would like to create a new user profile
So that I can use the application as intended to log my personal goals and track my fitness progress.

Scenario: User profile is created (Normal flow)

Given the User is not currently a member of the the FitBoi application
And the entered email is valid
And the individual's form data entered are valid 
When the request to create a profile is made
Then a new User profile is created 

Scenario: User enters invalid email (Error flow)

Given the User is not currently a member of the the FitBoi application
And the entered email is invalid
When the request to create a profile is made
Then a new User profile is not created

Scenario: User enters invalid data (Error flow)

Given the User is not currently a member of the the FitBoi application
And the entered form data is invalid
When the request to create a profile is made
Then a new User profile is not created

