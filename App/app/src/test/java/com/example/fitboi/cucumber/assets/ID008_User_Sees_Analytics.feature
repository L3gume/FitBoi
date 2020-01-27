Feature: User sees analytics

As a user
I would like to see my data tracked over time
So that I may visualize improvement or failures according to the goals I set 
and make decisions for myself based on data

Scenario: User sees the analytics dashboard with data (Normal Scenario)

Given the User is on the analytics dashboard
And there is data to display
And the User selects an analytic type from a dropdown menu
And the User selects their viewing scale for the graph
When viewing the analytics dashboard
Then the graph will display the corresponding analytics type with the appropriate scale

Scenario: User sees the analytics dashboard void of data (Alternate Scenario)

Given the User is on the analytics dashboard
But there is no data to display
When viewing the analytics dashboard
Then the graph will be empty