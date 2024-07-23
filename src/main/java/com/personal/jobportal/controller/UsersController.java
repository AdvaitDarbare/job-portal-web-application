package com.personal.jobportal.controller;

// Import statements
import com.personal.jobportal.entity.Users;
import com.personal.jobportal.entity.UsersType;
import com.personal.jobportal.services.UsersService;
import com.personal.jobportal.services.UsersTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller // Marks this class as a Spring MVC controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired // Automatically injects the required services
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register") // Handles GET requests to the "/register" URL
    public String register(Model model) {
        List<UsersType> usersTypes = usersTypeService.getAll(); // Fetches all user types
        model.addAttribute("getAllTypes", usersTypes); // Adds the list of user types to the model
        model.addAttribute("user", new Users()); // Adds a new Users object to the model
        return "register"; // Returns the "register" view
    }

    @PostMapping("/register/new") // Handles POST requests to the "/register/new" URL
    public String userRegistration(@Valid Users users, Model model) {
        Optional<Users> optionalUsers = usersService.getUserByEmail(users.getEmail()); // Checks if the user already exists by email
        if (optionalUsers.isPresent()) {
            // Checks if an Optional containing a Users object is present, meaning a user with the provided email already exists.
            model.addAttribute("error", "Email already registered,try to login or register with other email."); // Adds an error message to the model
            List<UsersType> usersTypes = usersTypeService.getAll(); // Fetches all user types
            model.addAttribute("getAllTypes", usersTypes); // Adds the list of user types to the model
            model.addAttribute("user", new Users()); // Adds a new Users object to the model
            return "register"; // Returns the "register" view
        }
        usersService.addNew(users); // Adds the new user to the database
        return "redirect:/dashboard/"; // Redirects to the dashboard
    }

    @GetMapping("/login") // Handles GET requests to the "/login" URL
    public String login() {
        return "login"; // Returns the "login" view
    }

    @GetMapping("/logout") // Handles GET requests to the "/logout" URL
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Gets the current authentication

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication); // Logs out the user
        }

        return "redirect:/"; // Redirects to the home page
    }
}
