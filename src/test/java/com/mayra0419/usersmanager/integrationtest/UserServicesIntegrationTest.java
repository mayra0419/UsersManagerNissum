package com.mayra0419.usersmanager.integrationtest;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Random;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserServicesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private static final Random RAND = new Random();

    @Test
    @Tag("Positive")
    void test_GivenOKRequest_WhenCallCreateUser_ShouldReturnInfoOfTheNewUser() throws Exception {
        String request = String.format(OK_REQUEST, RAND.nextInt(1000));

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isActive").value(true));
    }

    @Test
    @Tag("Negative")
    void test_GivenRepeatedEmail_WhenCallCreateUser_ShouldReturnBadRequest() throws Exception {
        String request = String.format(OK_REQUEST, RAND.nextInt(1000));

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Email already registered"));
    }

    //Disclaimer: this test depends on a successful execution of the createUser operation
    @Test
    @Tag("Positive")
    void test_GivenOKRequest_WhenCallGetUser_ShouldReturnInfoOfTheUser() throws Exception {
        String request = String.format(OK_REQUEST, RAND.nextInt(1000));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isActive").value(true));
    }

    @Test
    @Tag("Negative")
    void test_GivenNotValidUserId_WhenCallGetUser_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}", UUID.randomUUID().toString()))
                .andExpect(status().isNotFound());
    }

    private static final String OK_REQUEST = """
            {
                "name": "pepe",
                "email": "pepe%s@test.com",
                "password": "Pepe1035#",
                "phones":[
                    {
                        "number": "123",
                        "citycode": "34",
                        "countrycode": "57"
                    }
                ]
            }
            """;
}
