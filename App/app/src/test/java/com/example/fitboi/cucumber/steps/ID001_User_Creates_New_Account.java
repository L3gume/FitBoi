package com.example.fitboi.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import com.example.fitboi.cucumber.steps.ID000_Common_Steps;

public class ID001_User_Creates_New_Account {

    private String validEmail;
    private String validForm;

    @Given("the User is not currently a memeber of the the FitBoi application")
    public void the_User_is_not_currently_a_memeber_of_the_the_FitBoi_application() {
        // Write code here that turns the phrase above into concrete actions

        // Call the function that checks if a user is in the database using the email
        // Assert false

        //throw new io.cucumber.java.PendingException();
    }

    @Given("the entered email is valid")
    public void the_entered_email_is_valid() {
        // Write code here that turns the phrase above into concrete actions

        // Function that checks an email is valid

        //throw new io.cucumber.java.PendingException();
    }

    @Given("the individual's form data entered are valid")
    public void the_individual_s_form_data_entered_are_valid() {
        // Write code here that turns the phrase above into concrete actions

        // Function that checks a users form data

        //throw new io.cucumber.java.PendingException();
    }

    @When("clicking the create profile button")
    public void clicking_the_create_profile_button() {
        // Write code here that turns the phrase above into concrete actions

        // Click the create profile button with the previous information
        // returnString equal to the return of the function

        //throw new io.cucumber.java.PendingException();
    }

    @Then("a new user profile is created using the entered email and associated form data <DataType>")
    public void a_new_user_profile_is_created_using_the_entered_email_and_associated_form_data_DataType() {
        // Write code here that turns the phrase above into concrete actions

        // Check that the user exists in the database
        // Assert true

        ID000_Common_Steps.returnString = "Successful profile creation";

        //throw new io.cucumber.java.PendingException();
    }

    @Given("the entered email is invalid")
    public void the_entered_email_is_invalid() {
        // Write code here that turns the phrase above into concrete actions

        // Function that checks the email is valid

        throw new io.cucumber.java.PendingException();
    }

    @Then("the email field does not accept the user's entry")
    public void the_email_field_does_not_accept_the_user_s_entry() {
        // Get the return from the email acceptance into returnString

        throw new io.cucumber.java.PendingException();
    }

    @Given("the entered form data is invalid")
    public void the_entered_form_data_is_invalid() {
        // Make sure the form data is invalid

        throw new io.cucumber.java.PendingException();
    }

    @Then("the form fields do not accept the user's entry")
    public void the_form_fields_do_not_accept_the_user_s_entry() {
        // Get the return from the function into the returnString

        throw new io.cucumber.java.PendingException();
    }
}