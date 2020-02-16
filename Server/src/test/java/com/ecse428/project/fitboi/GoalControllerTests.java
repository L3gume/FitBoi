package com.ecse428.project.fitboi;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.*;

import com.ecse428.project.repository.*;
import com.ecse428.project.fitboi.model.*;
import com.ecse428.project.fitboi.TestHelpMethods.*;


@SpringBootTest
@AutoConfigureMockMvc
class GoalControllerTests {
    private static final Logger LOGGER = Logger.getLogger(GoalControllerTests.class.getName());

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private	ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
        goalRepository.deleteAll();
    }

    @AfterEach
    public void teardown() {
        userRepository.deleteAll();
        goalRepository.deleteAll();
    }

    @Test
    public void testPostGoalControllerSuccess() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);


        int baseCalories = 100;
        boolean result = false;
        String startDate =  "2020-05-09";
        float weight = 68;
        ActivityLevel activityLevel = ActivityLevel.Medium;
        float fatsForMacroDistribution = 0.3f;
        float carbsForMacroDistribution = 0.4f; 
        float proteinForMacroDistribution = 0.3f;

        SerializableGoal serializableGoal = new SerializableGoal(baseCalories, result, startDate, weight, activityLevel, fatsForMacroDistribution, carbsForMacroDistribution, proteinForMacroDistribution);


		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
            .andExpect(status().isCreated());
            
        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal));
		mockMvc.perform(post("/users/"+ aEmail + "/goals").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal)))
            .andExpect(status().isCreated());
            
        return;
    }


    @Test
    public void testGetGoalControllerSuccess() throws Exception {
        String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);


        int baseCalories = 100;
        boolean result = false;
        String startDate =  "2020-05-09";
        float weight = 68;
        ActivityLevel activityLevel = ActivityLevel.Medium;
        float fatsForMacroDistribution = 0.3f;
        float carbsForMacroDistribution = 0.4f; 
        float proteinForMacroDistribution = 0.3f;

        SerializableGoal serializableGoal = new SerializableGoal(baseCalories, result, startDate, weight, activityLevel, fatsForMacroDistribution, carbsForMacroDistribution, proteinForMacroDistribution);


		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
            .andExpect(status().isCreated());
            
        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal));
		mockMvc.perform(post("/users/"+ aEmail + "/goals").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal)))
            .andExpect(status().isCreated());
       
        mockMvc.perform(get("/users/"+aEmail+"/goals")).andExpect(status().isOk());

        return;
    }
    
    @Test
    public void testDeleteGoalControllerSuccess() throws Exception{
        String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);


        int baseCalories = 100;
        boolean result = false;
        String startDate =  "2020-05-09";
        float weight = 68;
        ActivityLevel activityLevel = ActivityLevel.Medium;
        float fatsForMacroDistribution = 0.3f;
        float carbsForMacroDistribution = 0.4f; 
        float proteinForMacroDistribution = 0.3f;

        SerializableGoal serializableGoal = new SerializableGoal(baseCalories, result, startDate, weight, activityLevel, fatsForMacroDistribution, carbsForMacroDistribution, proteinForMacroDistribution);


		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
            .andExpect(status().isCreated());

        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal));
		mockMvc.perform(post("/users/"+ aEmail + "/goals").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal)))
            .andExpect(status().isCreated());


      
        testUser = userRepository.findUserByEmail(aEmail);
        int goalId = testUser.getGoal(0).getId();

        mockMvc.perform(delete("/users/"+aEmail+"/goals/" + Integer.toString(goalId))).andExpect(status().isOk());
        return;
    }

    @Test
    public void testPostGoalControllerNull() throws Exception {
        String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);

        SerializableGoal serializableGoal = null;

        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
            .andExpect(status().isCreated());
            
        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal));
		mockMvc.perform(post("/users/"+ aEmail + "/goals").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal)))
            .andExpect(status().isBadRequest());
            
        return;

    }

}