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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.springframework.http.MediaType;

import com.ecse428.project.fitboi.dto.UserDto;
import com.ecse428.project.fitboi.dto.MetricsDto;
import com.ecse428.project.fitboi.dto.MealDto;


import com.ecse428.project.fitboi.model.Meal;
import com.ecse428.project.fitboi.model.MealType;
import com.ecse428.project.fitboi.model.Metrics;
import com.ecse428.project.repository.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.util.logging.*;

@SpringBootTest
@AutoConfigureMockMvc
class MealControllerTests {

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
	public void testGetAllMeals() throws Exception{
		mockMvc.perform(get("/users/meals").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString("")))
            .andExpect(status().isOk());
	}

	@Test
	public void testGetUserMeals() throws Exception{
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
        
        Metrics tMetrics = new Metrics(new Date(0), 3);
        tMetrics.addMeal(new Meal(MealType.Breakfast));

        mockMvc.perform(post("/users/" + aEmail + "/metrics").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tMetrics)))
        	.andExpect(status().isOk());
        
        mockMvc.perform(get("/users/" + aEmail + "/metrics/" + tMetrics.getId() + "/meal").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString("")))
        	.andExpect(status().isOk());
	}

	@Test
	public void testGetMeal() throws Exception{
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
        m.addMeal(meal);

        mockMvc.perform(post("/users/" + aEmail + "/metrics").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(m)))
        	.andExpect(status().isOk());
        
        mockMvc.perform(get("/users/" + aEmail + "/metrics/" + m.getId() + "/meal/" + meal.getId()).contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString("")))
        	.andExpect(status().isOk());
    }
    @Test
	public void testPostMeal() throws Exception{
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
        m.addMeal(meal);

        mockMvc.perform(post("/users/" + aEmail + "/metrics").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(m)))
        	.andExpect(status().isOk());
        
        mockMvc.perform(post("/users/" + aEmail + "/metrics/" + m.getId() + "/meal").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(meal)))
        	.andExpect(status().isCreated());
	}

	@Test
	public void testDeleteMeal() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Female";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);
		MetricsDto testMetricsDto = new MetricsDto(0, "2020-11-10", 100);
		MealDto mealDto = new MealDto(0, "Breakfast");

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
            .andExpect(status().isCreated());
		

        mockMvc.perform(post("/users/" + aEmail + "/metricsId").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testMetricsDto)))
        	.andExpect(status().isOk());
        
        mockMvc.perform(post("/users/" + aEmail + "/metrics/" + testMetricsDto.getId() + "/mealId").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mealDto)))
			.andExpect(status().isCreated());
			
		mockMvc.perform(delete("/users/" + aEmail + "/metrics/" + testMetricsDto.getId() + "/meal/" + mealDto.getId())).andExpect(status().isOk());

	}
}