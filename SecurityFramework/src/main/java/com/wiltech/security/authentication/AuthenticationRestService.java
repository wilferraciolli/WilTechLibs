package com.wiltech.security.authentication;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiltech.BaseRestService;

/**
 * The type Auth controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationRestService extends BaseRestService {

    @Autowired
    private AuthenticationService authenticateService;

    /**
     * Signin response entity.
     * @param data the data
     * @return the response entity
     */
    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody final AuthenticationRequest data) {
        final AuthenticationResourceResponse authenticationDetails = authenticateService.authenticate(data);

        return ResponseEntity.status(OK).body(authenticationDetails);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody final RegistrationRequest data) {
        authenticateService.register(data);

        return ResponseEntity.ok().build();
    }

}
