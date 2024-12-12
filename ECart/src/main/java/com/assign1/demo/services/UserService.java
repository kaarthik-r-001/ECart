package com.assign1.demo.services;

import com.assign1.demo.entity.User;
import com.assign1.demo.entity.UserRequestDTO;
import com.assign1.demo.entity.UserResponseDTO;
import com.assign1.demo.repository.UserRepository;
import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Service
@RequiredArgsConstructor
@Validated
public class UserService{

    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${spring.profiles.active}")
    private String envName;


    /**
     * Retrieves a list of users based on optional filters.
     *
     * @param id         the ID of the user to retrieve (optional).
     * @param firstName  the first name of the user to retrieve (optional).
     * @return a list of users matching the specified filters.
     *         If an ID is provided, returns the user with that ID if found;
     *         if a first name is provided, returns users whose first name starts with that string;
     *         if neither is provided, returns all users.
     */
    public List<User> getUsers(Integer id, String firstName) {
        if (id != null) {
            Optional<User> userOptional = userRepository.findById(id);
            return userOptional.isPresent() ?
                    Collections.singletonList(userOptional.get()) :
                    Collections.emptyList();
        } else if (firstName != null && !firstName.isEmpty()) {
            return userRepository.findByfirstNameStartingWith(firstName);
        }
        return userRepository.findAll();
    }

    /**
     * Creates a new user with the provided user details.
     *
     * @param userRequestDTO the details of the user to create.
     * @return the created user's data transfer object (DTO).
     * @throws Exception if the username is null or empty.
     */
    @Transactional
    public UserResponseDTO createUser(@Valid UserRequestDTO userRequestDTO) throws Exception{

        User user = modelMapper.map(userRequestDTO, User.class);
        user.setEnv_name(envName);

        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        User savedUser = userRepository.save(user);
        savedUser.setPassword(encodedPassword);

        UserResponseDTO savedUserDTO = modelMapper.map(savedUser, UserResponseDTO.class);

        return savedUserDTO;
    }


    /**
     * Updates an existing user with the provided user details.
     *
     * @param userRequestDTO the details of the user to update.
     * @return the updated user's data transfer object (DTO).
     * @throws Exception if the user ID is invalid or if the username is empty.
     */
    @Transactional
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO)  throws  Exception{

        Optional<User> existingUser = userRepository.findById(userRequestDTO.getId());

        User user = existingUser.orElseGet(User::new);

        modelMapper.map(userRequestDTO, user);
        user.setEnv_name(envName);

        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        User savedUser = userRepository.save(user);
        savedUser.setPassword(encodedPassword);

        UserResponseDTO updatedUserDTO = modelMapper.map(savedUser, UserResponseDTO.class);

        return updatedUserDTO;


    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     * @throws RuntimeException if the user with the specified ID does not exist.
     */


    @Transactional
    public void deleteUser(int id) throws Exception {

        if(!userRepository.findById(id).isPresent()){
            throw new Exception("Number Exceeds the existing members");

        }
        userRepository.deleteById(id);


    }



    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to retrieve.
     * @return an Optional containing the user if found, or empty if not found.
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.getByusername(username);
    }


}
