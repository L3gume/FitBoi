Feature: User changes personal profile details

As a user of the FitBoi application
I would like to modify my personal profile details
So that FitBoi can tailor information to me and provide accurate analytics and to rectify any mistakes made

Scenario: User successfully changes one profile value (Normal flow)

Given the User is logged into FitBoi
And they have clicked on the profile settings menu
And the User has modified one value in their profile
When clicking the save changes button
Then the profile changes are saved
And the "Successfully modified one value" message is issued

Scenario: User successfully changes many or all values (Alternate flow)

Given the User is logged into FitBoi
And they have clicked on the profile settings menu
And the User has modified multiple values at once in their profile
When clicking the save changes button
Then the profile changes are saved
And the "Successfully modified multiple value" message is issued

Scenario: User enters invalid data (Error flow)

Given the User is logged into FitBoi
And they have clicked on the profile settings menu
And the User has modified multiple values in their profile with invalid data
When clicking the save changes button
Then the profile changes are not saved
And the "Input invalid" message is issued

Scenario: User exits the window without saving (Error flow)

Given the User is logged into FitBoi
And they have clicked on the profile settings menu
And the User has modified multiple values in their profile
When the User exits the current window
Then the profile changes are not saved
And the "Data was not saved" message is issued

Scenario: Connectivity issue prevents data from persisting (Error flow)

Given the User is logged into FitBoi
And they have clicked on the profile settings menu
And the User has modified multiple values in their profile
And there is a lack of connection between the server and client
When clicking the save changes button
Then the profile changes are not saved
And the "Connectivity issues prevented saving" message is issued