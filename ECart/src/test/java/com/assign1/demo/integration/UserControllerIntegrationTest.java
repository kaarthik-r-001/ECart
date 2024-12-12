package com.assign1.demo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.assign1.demo.entity.User;
import com.assign1.demo.entity.UserRequestDTO;
import com.assign1.demo.repository.UserRepository;
import com.assign1.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserControllerIntegrationTest {


    private MockMvc mockMvc;


    private UserRepository userRepository;


    private UserService userService;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreateUser() throws Exception {
        String dobString = "1990-01-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = dateFormat.parse(dobString);

        String userJson = "{\"firstName\":\"John\", \"dob\":\"" + dateFormat.format(dob) + "\", \"username\":\"john_doe\"}";

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john_doe"))
                .andExpect(jsonPath("$.firstName").value("John"));

        assertEquals(1, userRepository.count());
    }

    @Test
    public void testGetUserById() throws Exception {
        String dobString = "1992-02-02";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = dateFormat.parse(dobString);
        userService.createUser(new UserRequestDTO("jane_doe", "Jane", dob));

        User user = userRepository.getByusername("jane_doe").orElse(null);

        mockMvc.perform(get("/api/users?id=" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Jane"))
                .andExpect(jsonPath("$[0].username").value("jane_doe"));
    }

    @Test
    public void testGetUserByFirstName() throws Exception {
        String dobString = "1990-01-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = dateFormat.parse(dobString);
        userService.createUser(new UserRequestDTO("john_doe", "John", dob));

        mockMvc.perform(get("/api/users?firstName=John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].username").value("john_doe"));

    }

    @Test
    public void testUpdateUser() throws Exception {
        String dobString = "1990-01-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = dateFormat.parse(dobString);
        userService.createUser(new UserRequestDTO("john_doe", "John", dob));

        User user = userRepository.getByusername("john_doe").orElse(null);
        String updatedUserJson = "{\"id\":" + user.getId() + ", \"firstName\":\"John Smith\", \"dob\":\"" + dateFormat.format(dob) + "\", \"username\":\"john_doe\"}";

        mockMvc.perform(put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John Smith"));

        User updatedUser = userRepository.findById(user.getId()).orElse(null);
        assertNotNull(updatedUser);
        assertEquals("John Smith", updatedUser.getFirstName());
    }

    @Test
    public void testDeleteUser() throws Exception {
        String dobString = "1990-01-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = dateFormat.parse(dobString);
        userService.createUser(new UserRequestDTO("john_doe", "John", dob));

        User user = userRepository.getByusername("john_doe").orElse(null);

        mockMvc.perform(delete("/api/users/" + user.getId()))
                .andExpect(status().isOk());


        assertEquals(0, userRepository.count());
    }




}