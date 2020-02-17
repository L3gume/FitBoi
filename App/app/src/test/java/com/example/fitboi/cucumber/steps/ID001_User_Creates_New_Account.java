package com.example.fitboi.cucumber.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import com.example.fitboi.dto.UserDto;
import com.example.fitboi.api.UserAPI;
import java.util.function.Consumer;



public class ID001_User_Creates_New_Account {

    String email = "johndoe@email.com";
    int age = 20;
    boolean sex  = true;
    int weight = 60;
    int height = 160;

    UserDto user = new UserDto(email, age, sex, weight, height);

    boolean isAdded;
    Consumer addUserConfirmation = new Consumer<UserDto> fn) {
        @Override
        public void accept(UserDto user) {
        // This is only if you want to return something is it was successfull
        // i.e. 
        if (user.email == userDto.email) {
             isAdded = true;
            }
        }
    };

    @When("a new User requests to create an account with valid input data")
    public void a_new_user_requests_to_create_an_account_with_valid_input_data() {

        UserAPI.addNewUser(user, addUserConfirmation);

        //throw new io.cucumber.java.PendingException();
    }

    @Then("a new User account is created with the valid input data")
    public void a_new_User_account_is_created_with_the_valid_input_data() {
        // Write code here that turns the phrase above into concrete actions

        //use the UserAPI.getUserByLoginInfo(fn , email) to verify that we can get the created user

        throw new io.cucumber.java.PendingException();
    }

    Assert.assertTrue(isAdded);
}
