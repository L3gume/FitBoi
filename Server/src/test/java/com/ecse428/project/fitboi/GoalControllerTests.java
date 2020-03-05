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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.*;

import com.ecse428.project.repository.*;
import com.ecse428.project.fitboi.model.*;
import com.ecse428.project.fitboi.TestHelpMethods.*;
import com.ecse428.project.fitboi.dto.UserDto;


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
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Female";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);
      
        int baseCalories = 100;
        boolean result = false;
        String startDate =  "2020-05-09";
        String endDate =  "2020-07-09";
        float weightGoal = 68;
        ActivityLevel activityLevel = ActivityLevel.Medium;
        GoalType goalType = GoalType.Gain;
        float fatsForMacroDistribution = 0.3f;
        float carbsForMacroDistribution = 0.4f; 
        float proteinForMacroDistribution = 0.3f;
      
        SerializableGoal serializableGoal = new SerializableGoal(baseCalories, result, startDate, endDate, weightGoal, activityLevel, goalType, fatsForMacroDistribution, carbsForMacroDistribution, proteinForMacroDistribution);
        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
            .andExpect(status().isCreated());
            
        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal));
		mockMvc.perform(post("/users/"+ aEmail + "/goal").contentType(MediaType.APPLICATION_JSON)
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
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Female";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);


        int baseCalories = 100;
        boolean result = false;
        String startDate =  "2020-05-09";
        String endDate =  "2020-07-09";
        float weightGoal = 68;
        ActivityLevel activityLevel = ActivityLevel.Medium;
        GoalType goalType = GoalType.Gain;
        float fatsForMacroDistribution = 0.3f;
        float carbsForMacroDistribution = 0.4f; 
        float proteinForMacroDistribution = 0.3f;

        SerializableGoal serializableGoal = new SerializableGoal(baseCalories, result, startDate, endDate, weightGoal, activityLevel, goalType, fatsForMacroDistribution, carbsForMacroDistribution, proteinForMacroDistribution);
        

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
            .andExpect(status().isCreated());
            
        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal));
		mockMvc.perform(post("/users/"+ aEmail + "/goal").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal)))
            .andExpect(status().isCreated());
        mockMvc.perform(get("/users/"+aEmail+"/goal")).andExpect(status().isOk());
      
        return;
     }
    

    @Test
    public void testDeleteGoalControllerSuccess() throws Exception{
      
        String aEmail = "testUser1@mail.mcgill.ca";
	    String aName = "testboi";
	    String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Female";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);


        int baseCalories = 100;
        boolean result = false;
        String startDate =  "2020-05-09";
        String endDate =  "2020-07-09";
        float weightGoal = 68;
        ActivityLevel activityLevel = ActivityLevel.Medium;
        GoalType goalType = GoalType.Gain;
        float fatsForMacroDistribution = 0.3f;
        float carbsForMacroDistribution = 0.4f; 
        float proteinForMacroDistribution = 0.3f;

        SerializableGoal serializableGoal = new SerializableGoal(baseCalories, result, startDate, endDate, weightGoal, activityLevel, goalType, fatsForMacroDistribution, carbsForMacroDistribution, proteinForMacroDistribution);
        

        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
            .andExpect(status().isCreated());

        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal));
		mockMvc.perform(post("/users/"+ aEmail + "/goal").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal)))
            .andExpect(status().isCreated());



        mockMvc.perform(delete("/users/"+aEmail+"/goal")).andExpect(status().isOk());
        
        return;
     }

    @Test
    public void testPostGoalControllerNull() throws Exception {
        String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		String aDOB = "2010-11-11";
		int aHeight = 193;
		String aBiologicalSex = "Female";
		UserDto testUser = new UserDto(aEmail, aName, aUserName, aPassword, aDOB, aBiologicalSex, aHeight);
      
        SerializableGoal serializableGoal = null;
      
        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
            .andExpect(status().isCreated());
            
        LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal));
		mockMvc.perform(post("/users/"+ aEmail + "/goal").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(serializableGoal)))
            .andExpect(status().isBadRequest());
      
        return;
     }

}