package com.assign1.demo.entity;

import jakarta.validation.constraints.NotEmpty;

public record UserRegistrationDto (
        @NotEmpty(message = "User Name must not be empty")
        String userName,
        String userMobileNo,
        @NotEmpty(message = "User email must not be empty") //Neither null nor 0 size

        String userId,

        @NotEmpty(message = "User password must not be empty")
        String userPassword,
        @NotEmpty(message = "User role must not be empty")
        String userRole
){ }