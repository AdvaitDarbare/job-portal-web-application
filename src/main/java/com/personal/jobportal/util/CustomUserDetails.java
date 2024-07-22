package com.personal.jobportal.util;

import com.personal.jobportal.entity.Users;
import com.personal.jobportal.entity.UsersType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// This class provides a custom implementation of the UserDetails interface to handle authentication details.
public class CustomUserDetails implements UserDetails {

    private Users user; // Holds the Users entity associated with this UserDetails.

    // Constructor to initialize the CustomUserDetails with a Users entity.
    public CustomUserDetails(Users user) {
        this.user = user;
    }

    @Override // Provides the authorities (roles or privileges) granted to the user.
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UsersType usersType = user.getUserTypeId(); // Retrieves the type of user.
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(); // Creates a list to hold authorities.
        authorities.add(new SimpleGrantedAuthority(usersType.getUserTypeName())); // Adds the user type as an authority.
        return authorities; // Returns the list of granted authorities.
    }

    @Override // Returns the password used for authenticating the user.
    public String getPassword() {
        return user.getPassword();
    }

    @Override // Returns the username used in user authentication; here it's the user's email.
    public String getUsername() {
        return user.getEmail();
    }

    @Override // Indicates whether the user's account has expired. An expired account cannot be authenticated.
    public boolean isAccountNonExpired() {
        return true; // Always returns true, meaning the account is never considered expired.
    }

    @Override // Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
    public boolean isAccountNonLocked() {
        return true; // Always returns true, indicating the account is not locked.
    }

    @Override // Indicates whether the user's credentials (password) have expired. Expired credentials prevent authentication.
    public boolean isCredentialsNonExpired() {
        return true; // Always returns true, meaning the credentials are never considered expired.
    }

    @Override // Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
    public boolean isEnabled() {
        return true; // Always returns true, indicating the user is enabled.
    }
}
