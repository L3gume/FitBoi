package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import com.ecse428.project.fitboi.dto.UserDto;

import com.ecse428.project.repository.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
	   
		ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("date" , "2001-11-10");
		requestBody.put("exerciseSpending", 3);
		requestBody.put("footNote", "Testing Footnotes");

		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(requestBody);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
			.andExpect(status().isCreated());

		MvcResult result = mockMvc.perform(post("/users/" + aEmail + "/metrics").contentType(MediaType.APPLICATION_JSON)
        	.content(requestJson))
			.andExpect(status().isOk())
			.andReturn();
		
		String s = result.getResponse().getContentAsString();
		JSONObject jsonObj = new JSONObject(s);
        String id = jsonObj.getString("id");
        
        requestBody = mapper.createObjectNode();
        requestBody.put("mealType" , "Breakfast");

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ow = mapper.writer().withDefaultPrettyPrinter();
		requestJson = ow.writeValueAsString(requestBody);
        
        result = mockMvc.perform(post("/users/" + aEmail + "/metrics/" + id + "/meal").contentType(MediaType.APPLICATION_JSON)
        .content(requestJson))
        .andExpect(status().isCreated())
        .andReturn();

        s = result.getResponse().getContentAsString();
		jsonObj = new JSONObject(s);
        String mealId = jsonObj.getString("id");

        mockMvc.perform(get("/users/" + aEmail + "/metrics/" + id + "/meal").contentType(MediaType.APPLICATION_JSON)
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
	   
		ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("date" , "2001-11-10");
		requestBody.put("exerciseSpending", 3);
		requestBody.put("footNote", "Testing Footnotes");

		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(requestBody);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
			.andExpect(status().isCreated());

		MvcResult result = mockMvc.perform(post("/users/" + aEmail + "/metrics").contentType(MediaType.APPLICATION_JSON)
        	.content(requestJson))
			.andExpect(status().isOk())
			.andReturn();
		
		String s = result.getResponse().getContentAsString();
		JSONObject jsonObj = new JSONObject(s);
        String id = jsonObj.getString("id");
        
        requestBody = mapper.createObjectNode();
        requestBody.put("mealType" , "Breakfast");

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ow = mapper.writer().withDefaultPrettyPrinter();
		requestJson = ow.writeValueAsString(requestBody);
        
        result = mockMvc.perform(post("/users/" + aEmail + "/metrics/" + id + "/meal").contentType(MediaType.APPLICATION_JSON)
        .content(requestJson))
        .andExpect(status().isCreated())
        .andReturn();

        s = result.getResponse().getContentAsString();
		jsonObj = new JSONObject(s);
        String mealId = jsonObj.getString("id");

        mockMvc.perform(get("/users/" + aEmail + "/metrics/" + id + "/meal/" + mealId).contentType(MediaType.APPLICATION_JSON)
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
	   
		ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("date" , "2001-11-10");
		requestBody.put("exerciseSpending", 3);
		requestBody.put("footNote", "Testing Footnotes");
		
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(requestBody);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
			.andExpect(status().isCreated());

		MvcResult result = mockMvc.perform(post("/users/" + aEmail + "/metrics").contentType(MediaType.APPLICATION_JSON)
        	.content(requestJson))
			.andExpect(status().isOk())
			.andReturn();
		
		String s = result.getResponse().getContentAsString();
		JSONObject jsonObj = new JSONObject(s);
        String id = jsonObj.getString("id");
        
        requestBody = mapper.createObjectNode();
        requestBody.put("mealType" , "Breakfast");

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ow = mapper.writer().withDefaultPrettyPrinter();
		requestJson = ow.writeValueAsString(requestBody);
        
        mockMvc.perform(post("/users/" + aEmail + "/metrics/" + id + "/meal").contentType(MediaType.APPLICATION_JSON)
        	.content(requestJson))
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
	   
		ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("date" , "2001-11-10");
		requestBody.put("exerciseSpending", 3);
		requestBody.put("footNote", "Testing Footnotes");

		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(requestBody);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
			.andExpect(status().isCreated());

		MvcResult result = mockMvc.perform(post("/users/" + aEmail + "/metrics").contentType(MediaType.APPLICATION_JSON)
        	.content(requestJson))
			.andExpect(status().isOk())
			.andReturn();
		
		String s = result.getResponse().getContentAsString();
		JSONObject jsonObj = new JSONObject(s);
        String id = jsonObj.getString("id");
        
        requestBody = mapper.createObjectNode();
        requestBody.put("mealType" , "Breakfast");

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ow = mapper.writer().withDefaultPrettyPrinter();
		requestJson = ow.writeValueAsString(requestBody);
        
        MvcResult result2 = mockMvc.perform(post("/users/" + aEmail + "/metrics/" + id + "/meal").contentType(MediaType.APPLICATION_JSON)
        	.content(requestJson))
			.andExpect(status().isCreated())
			.andReturn();

		String s2 = result2.getResponse().getContentAsString();
		JSONObject jsonObj2 = new JSONObject(s2);
		String id2 = jsonObj2.getString("id");
			
		mockMvc.perform(delete("/users/" + aEmail + "/metrics/" + id + "/meal/" + id2)).andExpect(status().isOk());

	}
}