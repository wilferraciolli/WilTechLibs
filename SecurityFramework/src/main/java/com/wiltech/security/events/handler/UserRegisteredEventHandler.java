package com.wiltech.security.events.handler;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.wiltech.security.authentication.RegistrationRequest;
import com.wiltech.security.authentication.events.UserRegisteredEvent;
import com.wiltech.security.users.UserAppService;
import com.wiltech.security.users.UserResource;
import com.wiltech.security.users.UserRoleType;

/**
 * The type User registered event handler.
 */
@Service
public class UserRegisteredEventHandler implements ApplicationListener<UserRegisteredEvent> {

    @Autowired
    private UserAppService userAppService;

    @Override
    public void onApplicationEvent(final UserRegisteredEvent event) {

        final RegistrationRequest userRegisteredDetails = event.getRegistrationRequest();

        createUser(userRegisteredDetails);

    }

    private void createUser(final RegistrationRequest user) {

        userAppService.create(UserResource.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getEmail())
                .password(user.getPassword())
                .dateOfBirth(user.getDateOfBirth())
                .active(true)
                .roleIds(asList(UserRoleType.ROLE_USER.name()))
                .build());
    }
}
