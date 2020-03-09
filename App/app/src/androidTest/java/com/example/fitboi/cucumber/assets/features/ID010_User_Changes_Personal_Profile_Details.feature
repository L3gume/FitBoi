Feature: User changes personal profile details

As a user of the FitBoi application
I would like to modify my personal profile details
So that FitBoi can tailor information to me and provide accurate analytics and to rectify any mistakes made

Scenario: User successfully changes one profile value (Normal flow)

Given the User is logged into FitBoi
And the User has modified one value in their profile
When the User saves the changes
Then the profile changes are saved

Scenario: User successfully changes many or all values (Alternate flow)

Given the User is logged into FitBoi
And the User has modified multiple values at once in their profile
When the User saves the changes
Then the profile changes are saved

Scenario: User enters invalid data (Error flow)

Given the User is logged into FitBoi
And the User has modified multiple values in their profile with invalid data
When the User saves the changes
Then the profile changes are not saved

Scenario: User exits the window without saving (Error flow)

Given the User is logged into FitBoi
And the User has modified multiple values at once in their profile
When the User does not save the changes
Then the profile changes are not saved
