Feature: User logs in

As a User of the FitBoi application
I would like to log in with my credentials
So that I can view my personal data and log values relevant to myself

Scenario: User successfully logs in (Normal flow)

Given the User is not currently logged into the FitBoi application
And the entered e-mail is valid
And the password is valid
When a login request is made
Then a User is logged in to their account

Scenario: User enters invalid data (Error flow)

Given the User is not currently logged into the FitBoi application
And the data entered into any field is invalid
When a login request is made
Then a User is not logged into their account

Scenario: User enters a correct e-mail but an incorrect password (Error flow)

Given the User is not currently logged into the FitBoi application
And the entered e-mail is valid
And the password entered is invalid
When a login request is made
Then a User is not logged into their account

Scenario: User enters credentials for an account that does not exist (Error flow)

Given the User is not currently logged into the FitBoi application
And valid data is entered in the email and password fields
And the User does not exist on the platform
When a login request is made
Then a User is not logged into their account

Scenario: The connection to the database cannot be established from a user connection point (Error flow)

Given the User is not currently logged into the FitBoi application
And valid data is entered in the email and password fields
And there is no valid internet connection on the device
When a login request is made
Then a User is not logged into their account

Scenario: The connection to the database cannot be established from a server problem (Error flow)

Given the User is not currently logged into the FitBoi application
And valid data is entered in the email and password fields
And there is no valid connection from the server
When a login request is made
Then a User is not logged into their account
