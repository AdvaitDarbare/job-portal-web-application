package com.personal.jobportal.services;

import com.personal.jobportal.entity.Users;
import com.personal.jobportal.repository.UsersRepository;
import com.personal.jobportal.util.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Marks this class as a Spring Service, indicating it's a component used for service-level operations.
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository; // Injects the UsersRepository to interact with the Users data.

    @Autowired // Autowires the UsersRepository, allowing Spring to inject the necessary bean automatically.
    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override // Overrides the loadUserByUsername method of UserDetailsService interface.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Attempts to find a user by their username (email in this case) using the UsersRepository.
        Users user = usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not found user")); // Throws UsernameNotFoundException if the user is not found.

        // Returns a new instance of CustomUserDetails with the found user. CustomUserDetails must implement UserDetails.
        return new CustomUserDetails(user);
    }
}
