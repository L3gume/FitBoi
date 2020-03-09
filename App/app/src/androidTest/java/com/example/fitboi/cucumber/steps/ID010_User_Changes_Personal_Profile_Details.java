package com.example.fitboi.cucumber.steps;

import com.example.fitboi.api.UserAPI;
import cucumber.api.java.en.*;

public class ID010_User_Changes_Personal_Profile_Details {

    @And("the User has modified one value in their profile")
    public void the_user_has_modified_one_value_in_their_profile() {
        StaticTestData.updatedUser.setName("TestTest");
    }

    @And("the User has modified multiple values at once in their profile")
    public void the_user_has_modified_multiple_values_in_their_profile() {
        StaticTestData.updatedUser.setName("TestTest");
        StaticTestData.updatedUser.setBiologicalSex("Female");
    }

    @And("the User has modified multiple values in their profile with invalid data")
    public void the_user_has_modified_multiple_values_in_their_profile_with_invalid_data() {
        StaticTestData.updatedUser.setHeight(-1);
        StaticTestData.updatedUser.setBiologicalSex("Attack Helicopter");
    }

    @When("the User saves the changes")
    public void clicking_the_save_changes_button() {
        StaticTestData.actualUpdatedUser = UserAPI.updateUser(StaticTestData.updatedUser, null);
    }

    @When("the User does not save the changes")
    public void user_does_not_save_the_changes() {
        // Nothing happens
    }

    @Then("the profile changes are saved")
    public void the_profile_changes_are_saved() {
        assert StaticTestData.actualUpdatedUser.equals(StaticTestData.updatedUser);
        // revert the change
        UserAPI.updateUser(StaticTestData.user, null);
    }

    @Then("the profile changes are not saved")
    public void the_profile_changes_are_not_saved() {
        assert StaticTestData.actualUpdatedUser == null;
    }
}
