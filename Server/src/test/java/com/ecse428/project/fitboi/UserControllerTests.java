package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import com.ecse428.project.fitboi.dto.UserDto;
import com.ecse428.project.fitboi.model.ActivityLevel;
import com.ecse428.project.fitboi.model.FoodItem;
import com.ecse428.project.fitboi.model.Goal;
import com.ecse428.project.fitboi.model.GoalType;
import com.ecse428.project.fitboi.model.MacroDistribution;
import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.MealType;
import com.ecse428.project.fitboi.model.Metrics;
import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.repository.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.util.logging.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {

	private static final Logger LOGGER = Logger.getLogger( UserControllerTests.class.getName() );

	@Autowired 
	private UserRepository userRepository;

	@Autowired
    private MockMvc mockMvc;

	private	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	public void setup(){
		userRepository.deleteAll();
	}

	@AfterEach
	public void teardown(){
		userRepository.deleteAll();
	}

	@Test
	public void testPostUserControllerSuccess() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Female";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isCreated());
	}

	@Test
	public void testPostUserControllerInvalidUserInfo() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Female";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
			.andExpect(status().isCreated());
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isBadRequest());	
	}

	@Test
	public void testPostUserControllerDuplicate() throws Exception{
		String aEmail = "";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = -1;
		String aBiologicalSex = "Female";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isNotAcceptable());
	}


	@Test
	public void testPostUserControllerNull() throws Exception{
		UserProfile testUser = null;

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isBadRequest());
	}

	@Test
	public void testPutUserControllerSuccess() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Male";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isCreated());

        testUser.setName("updatedBoi");
        mockMvc.perform(put("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isOk());
	}

	@Test
	public void testGetUserControllerSuccess() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Male";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isCreated());

        mockMvc.perform(get("/users/" + aEmail)).andExpect(status().isOk());
	}

	@Test
	public void testGetUserControllerFail() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Male";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isCreated());

        mockMvc.perform(get("/users/" + "test")).andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteUserControllerSuccess() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Male";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isCreated());

        mockMvc.perform(delete("/users/" + aEmail)).andExpect(status().isOk());
	}

	@Test
	public void testGetLoginUserSuccess() throws Exception {
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Male";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
				.andExpect(status().isCreated());

		mockMvc.perform(get("/users/" + aEmail + "/" + aPassword)).andExpect(status().isOk());
	}

	@Test
	public void testGetLoginUserFailureNotFound() throws Exception {
		String aEmail = "testUser1@mail.mcgill.ca";
		String aBadEmail = "testUser2@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Male";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
				.andExpect(status().isCreated());

		mockMvc.perform(get("/users/" + aBadEmail + "/" + aPassword)).andExpect(status().isNotFound());
	}

	@Test
	public void testGetLoginUserFailureBadPassword() throws Exception {
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aBadPassword = "password1";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Male";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
				.andExpect(status().isCreated());

		mockMvc.perform(get("/users/" + aEmail + "/" + aBadPassword)).andExpect(status().isNotFound());
	 }



	@Test
	public void testAddFoodToMeal() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Female";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);


		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
		.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
		.andExpect(status().isCreated());
		
		Metrics m = new Metrics(new Date(0), 3);
		Meal meal = new Meal(MealType.Breakfast);

		mockMvc.perform(post("/users/" + aEmail + "/metrics")
		.contentType(MediaType.APPLICATION_JSON)
		.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(m)))
		.andExpect(status().isOk());
	
		Goal g = new Goal(2000, false, new Date(0), new Date(0), 150.0f, ActivityLevel.Medium, GoalType.Maintain, 150.0f, 200.0f, 150.0f);
		FoodItem food = new FoodItem("food", 250, 15.0f, 10.0f, 10.0f, 10.0f, g);

		mockMvc.perform(post("/users/" + aEmail + "/metrics/" + m.getId() + "/meal/" + meal.getId() + "/food")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(food)))
			.andExpect(status().isCreated());
	}

	@Test
	public void testGetFoodFromMeal() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Female";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
		.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
		.andExpect(status().isCreated());
		
		Metrics m = new Metrics(new Date(0), 3);
		Meal meal = new Meal(MealType.Breakfast);

		mockMvc.perform(post("/users/" + aEmail + "/metrics")
		.contentType(MediaType.APPLICATION_JSON)
		.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(m)))
		.andExpect(status().isOk());
	
		Goal g = new Goal(2000, false, new Date(0), new Date(0), 150.0f, ActivityLevel.Medium, GoalType.Maintain, 150.0f, 200.0f, 150.0f);
		FoodItem food = new FoodItem("food", 250, 15.0f, 10.0f, 10.0f, 10.0f, g);
		mockMvc.perform(post("/users/" + aEmail + "/metrics/" + m.getId() + "/meal/" + meal.getId() + "/food")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(food)))
			.andExpect(status().isCreated());
		mockMvc.perform(get("/users/" + aEmail + "/metrics/" + m.getId() + "/meal/" + meal.getId() + "/food")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(food)))
			.andExpect(status().isCreated());
	}

}