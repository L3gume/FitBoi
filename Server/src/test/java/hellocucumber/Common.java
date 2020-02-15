package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class Common {

    public static String returnString;

    @Then("the {string} message is issued")
    public void the_message_is_issued(String string) {
        // Assert that the returnString is the same as the string
        assertEquals(returnString, string);
    }

}