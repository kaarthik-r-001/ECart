package com.assign1.demo.config;

import com.assign1.demo.repository.UserRepository;
import com.assign1.demo.services.UserService;
//import com.assign1.demo.services.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {
    @Bean
    @Profile("dev")
    @Primary
    public UserService userServicedev(UserRepository userRepository){
        return new UserService(userRepository);

    }
    @Bean
    @Profile("test")
    public UserService userServiceTest(UserRepository userRepository){

        return new UserService(userRepository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
