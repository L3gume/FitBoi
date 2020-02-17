package com.example.fitboi.cucumber.steps;

import com.example.fitboi.Fitboi;
import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.UserDto;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.mockito.Mockito.mock;

public class ID001_User_Creates_New_Account {

    private Fitboi app;
    private UserDto user;
    private String email;

    private static boolean isCreated;

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
        isCreated = false;
        user = null;
        email = "boatyyy@mcboatface.com";
        app = mock(Fitboi.class);
        app.onCreate();
        UserAPI.getUserByLoginInfo(new Consumer<UserDto>() {
            @Override
            public void accept(UserDto userDto) {
                user = userDto;
            }
        }, email);
    }

    @Given("the User is not currently a member of the the FitBoi application")
    public void the_User_is_not_currently_a_member_of_the_the_FitBoi_application() {
        // Write code here that turns the phrase above into concrete actions
        assert user == null;

    }

    @Given("the entered email is valid")
    public void the_entered_email_is_valid() {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        assert isValidEmail(email);
    }

    @And("the entered email is invalid")
    public void the_entered_email_is_invalid() {
        email = "boatymcboatface.com";
        assert !isValidEmail(email);

        user = new UserDto();
        user.setAge(18);
        user.setEmail(email);
        user.setHeight(180);
        user.setName("Boaty McBoatFace");
        user.setPassword("boatboat");
        user.setSex(false);
        user.setUserName("boaty");
    }

    @And("the individual's form data entered are valid")
    public void the_individual_s_form_data_entered_are_valid() {
        // Write code here that turns the phrase above into concrete actions
 //       throw new io.cucumber.java.PendingException();
        user = new UserDto();
        user.setAge(18);
        user.setEmail(email);
        user.setHeight(180);
        user.setName("Boaty McBoatFace");
        user.setPassword("boatboat");
        user.setSex(false);
        user.setUserName("boaty");
    }

    @And("the entered form data is invalid")
    public void the_entered_form_data_is_invalid() {
        user = new UserDto();
        user.setAge(-1);
        user.setEmail(email);
        user.setHeight(180);
        user.setName("Boaty McBoatFace");
        user.setPassword(null);
        user.setSex(false);
        user.setUserName(null);
    }

    @When("the request to create a profile is made")
    public void request_create_profile_made() {
        // Write code here that turns the phrase above into concrete actions
  //      throw new io.cucumber.java.PendingException();
        UserAPI.addNewUser(user, new Consumer<UserDto>() {
            @Override
            public void accept(UserDto userDto) {
                if (userDto != null)
                    isCreated = user.getEmail().equals(userDto.getEmail());
                else
                    isCreated = false;
            }
        });
    }

    @Then("a new User profile is created")
    public void a_new_user_profile_is_created() {
        // Write code here that turns the phrase above into concrete actions
   //     throw new io.cucumber.java.PendingException();
        assert isCreated;
    }

    @Then("a new User profile is not created")
    public void a_new_user_profile_is_not_created() {
        assert !isCreated;
    }
}
