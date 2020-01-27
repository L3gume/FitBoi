Feature: User creates new account

As a User of the FitBoi application
I would like to create a new user profile
So that I can use the application as intended to log my personal goals and track my fitness progress.

Scenario: User profile is created (Normal flow)

Given the User is not currently a member of the the FitBoi application
And the entered email is valid
And the individual's form data entered are valid 
When clicking the create profile button
Then a new user profile is created using the entered email and associated form data <DataType>
And the "Successful profile creation" message is issued


Scenario: User enters invalid email (Error flow)

Given the User is not currently a member of the the FitBoi application
  And the entered email is invalid
  Then the email field does not accept the user's entry
And the "Invalid E-mail" message is issued

Scenario: User enters invalid data (Error flow)

Given the User is not currently a member of the the FitBoi application
And the entered form data is invalid
Then the form fields do not accept the user's entry
And the "Invalid Input" message is issued

