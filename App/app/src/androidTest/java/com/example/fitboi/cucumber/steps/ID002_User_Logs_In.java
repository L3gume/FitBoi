package com.example.fitboi.cucumber.steps;

import com.example.fitboi.Fitboi;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.data.model.LoggedInUser;
import com.example.fitboi.dto.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cucumber.api.java.en.*;

import static org.mockito.Mockito.mock;

public class ID002_User_Logs_In {
    private UserDto userDto = null;
    private LoggedInUser user = null;
    private String email = "";
    private String password = "";

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
        assert user == null;
    }

    @And("the entered e-mail is valid")
    public void and_entered_email_is_valid() {
        email = "boaty@mcboatface.ca";
        assert isValidEmail(email);
    }

    @And("the data entered into any field is invalid")
    public void and_data_entered_any_field_invalid() {
        email = "boatymcboatface.ca";
        password = "four"; // needs at least 5
        assert !isValidEmail(email) || password.length() <= 4;
    }

    @And("the password is valid")
    public void and_password_is_valid() {
        password = "morethanfivechars";
        assert password.length() > 4;
    }

    @And("the password entered is invalid")
    public void and_password_is_invalid() {
        password = "four";
        assert password.length() < 5;
    }

    @And("valid data is entered in the email and password fields")
    public void and_valid_data_in_email_and_password_fields() {
        email = "boaty@mcboatface.ca";
        password = "morethanfivechars";
        assert isValidEmail(email) && password.length() > 4;
    }

    @And("the User does not exist on the platform")
    public void and_user_does_not_exist_on_platform() {
        // ???
    }

    @And("there is no valid internet connection on the device")
    public void and_no_valid_internet_connection_on_device() {
        // ???
        //throw new io.cucumber.java.PendingException();
    }

   @And("there is no valid connection from the server")
   public void and_no_valid_connection_from_server() {
       // ???
       //throw new io.cucumber.java.PendingException();
   }

    @When("a login request is made")
    public void when_login_request_made() {
        Fitboi app = mock(Fitboi.class);
        app.onCreate();
        this.userDto = UserAPI.getUserByLogin(email, password, null);
    }

    @Then("a User is logged in to their account")
    public void then_user_logged_in_account() {
        assert this.userDto != null;
    }

    @Then("a User is not logged into their account")
    public void then_user_not_logged_in_account() {
        assert this.userDto == null;
    }
}
