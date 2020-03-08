package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cucumber.api.java.en.*;

import static org.mockito.Mockito.mock;

public class ID002_User_Logs_In {

    private UserDto user;
    private String email;
    private String password;

    private boolean isValidEmail(String email){
        boolean isValidEmail = false;

        String regExpn = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (email.matches(regExpn))
        {
            isValidEmail = true;
        }
        return isValidEmail;
    }

    @Given("the User is not currently logged into the FitBoi application")
    public void given_user_not_currently_logged_into_fitboi_app() {
        // create user if not already
        user = UserAPI.getUser("test@gmail.com",null);
        if (user == null) {
            user = new UserDto("test@gmail.com","Test", "test", "12345","1998-01-01","Male",180);
            user = UserAPI.addUser(user, null);
        }
        // not logged in
        user = null;
    }

    @And("the entered e-mail is valid")
    public void and_entered_email_is_valid() {
        email = "test@gmail.com";
    }

    @And("the data entered into any field is invalid")
    public void and_data_entered_any_field_invalid() {
        password = "123"; // too short
        email = "testgmail.com"; // no @
    }

    @And("the password is valid")
    public void and_password_is_valid() {
        password = "12345";
    }

    @And("the password entered is invalid")
    public void and_password_is_invalid() {
        password = "123";
    }

    @And("valid data is entered in the email and password fields")
    public void and_valid_data_in_email_and_password_fields() {
        email = "test@gmail.com";
        password = "12345";
    }

    @And("the User does not exist on the platform")
    public void and_user_does_not_exist_on_platform() {
        UserAPI.deleteUser(email,null);
    }

    @And("there is no valid internet connection on the device")
    public void and_no_valid_internet_connection_on_device() {

    }

    @And("there is no valid connection from the server")
    public void and_no_valid_connection_from_server() {

    }

    @When("a login request is made")
    public void when_login_request_made() {
        user = UserAPI.getUserByLogin(email, password, null);
    }

    @Then("a User is logged in to their account")
    public void then_user_logged_in_account() {
        assert (user != null);
    }

    @Then("a User is not logged into their account")
    public void then_user_not_logged_in_account() {
        assert (user == null);
    }
}
