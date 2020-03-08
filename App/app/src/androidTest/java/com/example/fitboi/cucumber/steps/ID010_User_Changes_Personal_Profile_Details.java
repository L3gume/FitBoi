package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.UserAPI;
import com.example.fitboi.dto.UserDto;

import cucumber.api.java.en.*;

public class ID010_User_Changes_Personal_Profile_Details {
    private UserDto user;
    private UserDto updatedUser;
    private UserDto actualUpdatedUser;

    @Given("the User is logged into FitBoi")
    public void the_user_is_logged_into_Fitboi() {
        user = UserAPI.getUser("test@gmail.com",null);
        if (user == null) {
            user = new UserDto("test@gmail.com","Test", "test",
                    "12345","1998-01-01","Male",180);
            user = UserAPI.addUser(user, null);
        }
        updatedUser = new UserDto(user.getEmail(), user.getName(), user.getUserName(),
                user.getPassword(), user.getDob(), user.getBiologicalSex(), user.getHeight());
    }

    @And("the User has modified one value in their profile")
    public void the_user_has_modified_one_value_in_their_profile() {
        updatedUser.setName("TestTest");
    }

    @And("the User has modified multiple values at once in their profile")
    public void the_user_has_modified_multiple_values_in_their_profile() {
        updatedUser.setName("TestTest");
        updatedUser.setBiologicalSex("Female");
    }

    @And("the User has modified multiple values in their profile with invalid data")
    public void the_user_has_modified_multiple_values_in_their_profile_with_invalid_data() {
        updatedUser.setHeight(-1);
        updatedUser.setBiologicalSex("Attack Helicopter");
    }

    @When("clicking the save changes button")
    public void clicking_the_save_changes_button() {
        actualUpdatedUser = UserAPI.updateUser(updatedUser, null);
    }

    @When("the User does not save the changes")
    public void user_does_not_save_the_changes() {
        // Nothing happens
    }

    @Then("the profile changes are saved")
    public void the_profile_changes_are_saved() {
        assert actualUpdatedUser == updatedUser;
        // revert the change
        UserAPI.updateUser(user, null);
    }

    @Then("the profile changes are not saved")
    public void the_profile_changes_are_not_saved() {
        assert actualUpdatedUser == null;
    }
}
