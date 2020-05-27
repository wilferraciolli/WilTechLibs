package com.wiltech.security.users;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(final Long userId) {
        super("UserId: " + userId + " not found.");
    }

}
