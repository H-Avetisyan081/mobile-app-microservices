package com.example.mobileappapiusers.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.mobileappapiusers.model.UserRequest;
import com.example.mobileappapiusers.model.UserResponseModel;
import com.example.mobileappapiusers.model.UserRest;
import com.example.mobileappapiusers.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private Environment environment;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#status()}
     */
    @Test
    void testStatus() throws Exception {
        when(environment.getProperty((String) any())).thenReturn("Property");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/status/check");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Working on port Property"));
    }

    /**
     * Method under test: {@link UserController#status()}
     */
    @Test
    void testStatus2() throws Exception {
        when(environment.getProperty((String) any())).thenReturn("Property");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/status/check");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Working on port Property"));
    }

    /**
     * Method under test: {@link UserController#createUser(UserRequest)}
     */
    @Test
    void testCreateUser() throws Exception {
        UserRest userRest = new UserRest();
        userRest.setEmail("jane.doe@example.org");
        userRest.setEncryptedPassword("iloveyou");
        userRest.setFirstName("Jane");
        userRest.setLastName("Doe");
        userRest.setPassword("iloveyou");
        userRest.setUserId("42");
        when(userService.createUser((UserRest) any())).thenReturn(userRest);

        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("jane.doe@example.org");
        userRequest.setFirstName("Jane");
        userRequest.setLastName("Doe");
        userRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(userRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"userId\""
                                        + ":null,\"encryptedPassword\":null}"));
    }

    /**
     * Method under test: {@link UserController#createUser(UserRequest)}
     */
    @Test
    void testCreateUser2() throws Exception {
        UserRest userRest = new UserRest();
        userRest.setEmail("?");
        userRest.setEncryptedPassword("iloveyou");
        userRest.setFirstName("Jane");
        userRest.setLastName("Doe");
        userRest.setPassword("iloveyou");
        userRest.setUserId("42");
        when(userService.createUser((UserRest) any())).thenReturn(userRest);

        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("jane.doe@example.org");
        userRequest.setFirstName("Jane");
        userRequest.setLastName("Doe");
        userRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(userRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"userId\""
                                        + ":null,\"encryptedPassword\":null}"));
    }

    /**
     * Method under test: {@link UserController#createUser(UserRequest)}
     */
    @Test
    void testCreateUser3() throws Exception {
        UserRest userRest = new UserRest();
        userRest.setEmail("jane.doe@example.org");
        userRest.setEncryptedPassword("iloveyou");
        userRest.setFirstName("Jane");
        userRest.setLastName("Doe");
        userRest.setPassword("iloveyou");
        userRest.setUserId("42");
        when(userService.createUser((UserRest) any())).thenReturn(userRest);

        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("?");
        userRequest.setFirstName("Jane");
        userRequest.setLastName("Doe");
        userRequest.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(userRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link UserController#getUser(String)}
     */
    @Test
    void testGetUser() throws Exception {
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setAlbums(new ArrayList<>());
        userResponseModel.setEmail("jane.doe@example.org");
        userResponseModel.setFirstName("Jane");
        userResponseModel.setLastName("Doe");
        userResponseModel.setPassword("iloveyou");
        userResponseModel.setUserId("42");
        when(userService.getUserByUserId((String) any())).thenReturn(userResponseModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}", "42");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"userId\":"
                                        + "\"42\",\"albums\":[]}"));
    }
}

