package com.wiltech.security.users;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wiltech.EventPublisher;
import com.wiltech.security.events.UserCreatedEvent;
import com.wiltech.security.user.User;
import com.wiltech.security.user.UserRepository;

/**
 * The type User app service.
 */
@Service
public class UserAppService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsViewRepository userDetailsViewRepository;

    @Autowired
    private UserResourceAssembler assembler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EventPublisher eventPublisher;

    /**
     * Find users list.
     * @return the list
     */
    public List<UserResource> findUsers() {

        return userDetailsViewRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Create user resource.
     * @param userResourceCreate the user resource create
     * @return the user resource
     */
    public UserResource create(@Valid final UserResource userResourceCreate) {
        final User user = User.builder()
                .username(userResourceCreate.getUsername())
                .password(this.passwordEncoder.encode(userResourceCreate.getPassword()))
                .active(userResourceCreate.getActive())
                .roles(userResourceCreate.getRoleIds())
                .build();

        userRepository.save(user);
        publishUserCreatedEventWithPersonDetails(user.getId(), userResourceCreate);

        return transpose(user);
    }

    /**
     * Find by id user resource.
     * @param id the id
     * @return the user resource
     */
    public UserResource findById(final Long id) {

        final UserDetailsView user = userDetailsViewRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        return convertToDTO(user);
    }

    /**
     * Update user resource.
     * @param id the id
     * @param userResourcePayload the user resource payload
     * @return the user resource
     */
    public UserResource update(final Long id, final UserResource userResourcePayload) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        user.updateUser(userResourcePayload.getUsername(), this.passwordEncoder.encode(userResourcePayload.getPassword()),
                userResourcePayload.getRoleIds());
        userRepository.save(user);

        return transpose(user);
    }

    /**
     * Delete user buy id.
     * @param id the id
     */
    public void deleteById(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        userRepository.delete(user);
    }

    private UserResource transpose(final User user) {
        final UserResource userResource = UserResource.builder()
                .id(user.getId())
                .username(user.getUsername())
                //                .email(user.getUsername())
                .password(user.getPassword())
                .active(user.getActive())
                .roleIds(user.getRoles())
                .build();

        return userResource;
    }

    private void publishUserCreatedEventWithPersonDetails(final Long userId, final UserResource userResourceCreated) {

        eventPublisher.publishEvent(new UserCreatedEvent(this, userId, userResourceCreated.getFirstName(), userResourceCreated.getLastName(),
                userResourceCreated.getUsername(), userResourceCreated.getDateOfBirth()));
    }

    private UserResource convertToDTO(UserDetailsView userDetailsView) {

        return assembler.convertToDTO(userDetailsView, resolveUserRoles(userDetailsView));
    }

    private List<String> resolveUserRoles(UserDetailsView userDetailsView) {

        return userDetailsService.loadUserByUsername(userDetailsView.getUsername()).getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
    }
}
