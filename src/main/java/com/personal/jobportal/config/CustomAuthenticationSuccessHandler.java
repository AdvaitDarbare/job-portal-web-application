package com.personal.jobportal.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component  // Marks this class as a Spring Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Retrieves the UserDetails object from the Authentication object
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // Gets the username from the UserDetails object
        String username = userDetails.getUsername();
        // Prints a log statement indicating the successful login
        System.out.println("The username " + username + " is logged in.");

        // Checks if the authenticated user has the "Job Seeker" role
        boolean hasJobSeekerRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("Job Seeker"));
        // Checks if the authenticated user has the "Recruiter" role
        boolean hasRecruiterRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("Recruiter"));

        // Redirects the user to the dashboard if they have either role
        if (hasRecruiterRole || hasJobSeekerRole) {
            response.sendRedirect("/dashboard/");
        }
    }
}
