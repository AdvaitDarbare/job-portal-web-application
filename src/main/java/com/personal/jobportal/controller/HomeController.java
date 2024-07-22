package com.personal.jobportal.controller;

import org.springframework.stereotype.Controller;

@Controller // Marks this class as a Spring MVC controller.
public class HomeController {

    // Method that handles requests to the root URL ("/").
    public String home() {
        // Returns the name of the view to be rendered.
        // In this case, "index" refers to the index.html Thymeleaf template in the /src/main/resources/templates directory.
        return "index";
    }
}
