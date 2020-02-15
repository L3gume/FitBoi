Feature: User logs in

As a user of the FitBoi application
I would like to log in with my credentials
So that I can view my personal data and log values relevant to myself

Scenario: User successfully logs in (Normal flow)

Given the User is not currently logged into the FitBoi application
And the entered e-mail is valid
And the password is valid
And the combination of email and password is correct
When clicking the sign in button
Then a user is logged in to their account
And the "Successfully logged in" message is issued

Scenario: User enters invalid data (Error flow)

Given the User is not currently logged into the FitBoi application
And the data entered into any field is invalid
When clicking the sign in button
Then the field does not accept the user's entry
And the "Invalid input" message is issued

Scenario: User enters a correct e-mail but an incorrect password (Error flow)

Given the User is not currently logged into the FitBoi application
And the entered e-mail is valid
And the password entered is incorrect
When clicking the sign in button
Then the User is not logged in
And the "Password is incorrect" message is issued

Scenario: User enters credentials for an account that does not exist (Error flow)

Given the User is not currently logged into the FitBoi application
And the entered e-mail is valid
When clicking the sign in button
And the entered e-mail is not found
Then the User is not logged in
And the "Account cannot be found" message is issued

Scenario: The connection to the database cannot be established from a user connection point (Error flow)

Given the User is not currently logged into the FitBoi application
And there is data typed into both fields
And there is no valid internet connection on the device
When clicking the sign in button
Then the User is not logged in
And the "No valid internet connection" message is issued
And a pop-up window will prompt the user to check their internet connection

Scenario: The connection to the database cannot be established from a server problem (Error flow)

Given the User is not currently logged into the FitBoi application
And there is data typed into both fields
And there is no valid connection from the server
When clicking the sign in button
Then the User is not logged in
And the "Server error" message is issued
And a pop-up window will inform the user that there is a server error