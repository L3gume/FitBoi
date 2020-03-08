package com.example.fitboi.cucumber.steps;

import com.example.fitboi.Fitboi;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.UserDto;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.mockito.Mockito.mock;

public class ID001_User_Creates_New_Account {

    private Fitboi app;
    private UserDto user;

    // form data
    private String email;
    private String password;
    private String dob;
    private Integer height;
    private String biologicalSex;
    private String userName;
    private String name;

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


    @Before
    public void init() {
        app = mock(Fitboi.class);
        app.onCreate();
    }

    @Given("the User is not currently a member of the the FitBoi application")
    public void the_User_is_not_currently_a_member_of_the_the_FitBoi_application() {
        UserAPI.deleteUser(email,null);
    }

    @Given("the entered email is valid")
    public void the_entered_email_is_valid() {
        email = "test@gmail.com";
    }

    @And("the entered email is invalid")
    public void the_entered_email_is_invalid() {
        email = null;
    }

    @And("the individual's form data entered are valid")
    public void the_individual_s_form_data_entered_are_valid() {
        email = "test@gmail.com";
        password = "12345";
        dob = "1998-01-01";
        height = 180;
        biologicalSex = "Male";
        name = "Test";
        userName = "test";
    }

    @And("the entered form data is invalid")
    public void the_entered_form_data_is_invalid() {
        email = null;
        password = "123";
        dob = "1998-01-01";
        height = null;
        biologicalSex = "Male";
        name = "Test";
        userName = "test";
    }

    @When("the request to create a profile is made")
    public void request_create_profile_made() {
        user = new UserDto(email,name,userName, password,dob,biologicalSex,height);
        user = UserAPI.addUser(user, null);
    }

    @Then("a new User profile is created")
    public void a_new_user_profile_is_created() {
        assert user != null;
    }

    @Then("a new User profile is not created")
    public void a_new_user_profile_is_not_created() {
        assert user == null;
    }
}
