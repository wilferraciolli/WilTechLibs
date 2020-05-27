package com.wiltech.security.authentication;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wiltech.BaseApplicationService;
import com.wiltech.EventPublisher;
import com.wiltech.security.authentication.events.UserRegisteredEvent;
import com.wiltech.security.jwt.JwtTokenProvider;
import com.wiltech.security.user.User;
import com.wiltech.security.user.UserRepository;

/**
 * The type Authentication service.
 */
@Service
public class AuthenticationService extends BaseApplicationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository users;

    @Autowired
    private EventPublisher eventPublisher;

    /**
     * Authenticate authentication resource response.
     * @param data the data
     * @return the authentication resource response
     */
    public AuthenticationResourceResponse authenticate(final AuthenticationRequest data) {
        try {
            final String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));

            final User user = resolveUser(username);
            final String token = jwtTokenProvider.createToken(username, user.getId(), user.getRoles());

            final AuthenticationResourceResponse response = AuthenticationResourceResponse.builder()
                    .accessToken(token)
                    .tokenType("bearer")
                    .expiresIn(3600L)
                    .refreshToken("")
                    .scope("read")
                    .build();

            return response;
        } catch (final AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    private User resolveUser(final String username) {
        return this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
    }

    public void register(@Valid final RegistrationRequest data) {

        eventPublisher.publishEvent(new UserRegisteredEvent(this, data));
    }
}
