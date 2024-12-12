package com.assign1.demo.controller;

import com.assign1.demo.entity.User;
import com.assign1.demo.entity.UserRequestDTO;
import com.assign1.demo.entity.UserResponseDTO;
import com.assign1.demo.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);




    /**
     * Retrieves a list of users based on optional filters.
     *
     * @param id         the ID of the user to retrieve (optional).
     * @param firstName  the first name of the user to retrieve (optional).
     * @return a list of users matching the specified filters.
     */
    @GetMapping("/users")
    public List<User> getUsers(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String firstName) {

            return userService.getUsers(id,firstName);

    }

    /**
     * Creates a new user with the provided user details.
     *
     * @param userRequestDTO the details of the user to create.
     * @return the created user.
     * @throws Exception if an error occurs during user creation.
     */
    @PostMapping("/users")
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws Exception {

            logger.info("just checking if error is thrown here {}", userRequestDTO);
//        logger.info("size of username is {}",userRequestDTO.getUsername().length());
            return userService.createUser(userRequestDTO);

    }
    /**
     * Updates an existing user with the provided user details.
     *
     * @param userRequestDTO the details of the user to update.
     * @return the updated user.
     * @throws Exception if an error occurs during user update.
     */

    @PutMapping("/users")
    public UserResponseDTO  updateUser( @RequestBody @Valid UserRequestDTO userRequestDTO) throws Exception {
            return userService.updateUser(userRequestDTO);

    }
    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
            throws Exception {
            userService.deleteUser(id);

    }
}
