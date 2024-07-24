package com.personal.jobportal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration // Marks this class as a configuration source for Spring context.
public class MvcConfig implements WebMvcConfigurer { // Implements WebMvcConfigurer to customize Spring MVC configuration.

    private static final String UPLOAD_DIR = "photos"; // Directory name for file uploads.

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Adds resource handlers for serving static resources.
        exposeDirectory(UPLOAD_DIR, registry);
    }

    // Method to expose the specified directory as a web-accessible resource.
    private void exposeDirectory(String uploadDir, ResourceHandlerRegistry registry) {
        Path path = Paths.get(uploadDir); // Converts the directory name to a Path object.
        registry.addResourceHandler("/" + uploadDir + "/**") // Maps the directory to a URL pattern.
                .addResourceLocations("file:" + path.toAbsolutePath() + "/"); // Specifies the file system location.
    }
}
