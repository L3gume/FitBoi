Feature: User logs in

As a user of the FitBoi application
I would like to log in with my credentials
So that I can view my personal data and log values relevant to myself

Scenario: User successfully logs in (Normal flow)

Given the User is not currently logged into the FitBoi application d
And the entered e-mail is valid d
And the password is valid
And the combination of email and password is correct
When clicking the sign in button d
Then a user is logged in to their account
And the "Successfully logged in" message is issued d

Scenario: User enters invalid data (Error flow)

Given the User is not currently logged into the FitBoi application e
And the data entered into any field is invalid
When clicking the sign in button e
Then the field does not accept the user's entry
And the "Invalid input" message is issued e

Scenario: User enters a correct e-mail but an incorrect password (Error flow)

Given the User is not currently logged into the FitBoi application f
And the entered e-mail is valid f
And the password entered is incorrect
When clicking the sign in button f
Then the User is not logged in f
And the "Password is incorrect" message is issued f

Scenario: User enters credentials for an account that does not exist (Error flow)

Given the User is not currently logged into the FitBoi application g
And the entered e-mail is valid g
When clicking the sign in button g
And the entered e-mail is not found
Then the User is not logged in g
And the "Account cannot be found" message is issued g

Scenario: The connection to the database cannot be established from a user connection point (Error flow)

Given the User is not currently logged into the FitBoi application h
And there is data typed into both fields h
And there is no valid internet connection on the device
When clicking the sign in button h
Then the User is not logged in h
And the "No valid internet connection" message is issued h
And a pop-up window will prompt the user to check their internet connection

Scenario: The connection to the database cannot be established from a server problem (Error flow)

Given the User is not currently logged into the FitBoi application i
And there is data typed into both fields i
And there is no valid connection from the server
When clicking the sign in button i
Then the User is not logged in i
And the "Server error" message is issued i
And a pop-up window will inform the user that there is a server error