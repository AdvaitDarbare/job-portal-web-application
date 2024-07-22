
# Spring Boot 3 Job Portal Web Application

## Overview
This project is an end-to-end implementation of a Job Portal Web Application using Spring Boot 3. The application demonstrates the use of several Spring components and technologies to build a comprehensive web application.

## Features
- **Spring Boot 3**: Core framework to build the application.
- **Spring MVC**: Model-View-Controller framework to handle web requests.
- **Thymeleaf**: Template engine for rendering dynamic web pages.
- **Spring Security**: Security framework to handle authentication and authorization.
- **Spring Data JPA**: Abstraction over JPA to interact with the database.
- **Hibernate**: ORM framework to manage database operations.
- **MySQL**: Relational database to store application data.

## Getting Started
### Prerequisites
- JDK 17 or higher
- Maven 3.6+
- MySQL database

### Installation
1. Clone the repository:
   \```bash
   git clone https://github.com/AdvaitDarbare/job-portal-web-application.git
   \```

2. Navigate to the project directory:
   \```bash
   cd job-portal-web-application
   \```

3. Configure the database:
    - Update the `application.properties` file with your MySQL database details:
      \```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/job_portal
      spring.datasource.username=root
      spring.datasource.password=yourpassword
      \```

4. Build the project:
   \```bash
   mvn clean install
   \```

5. Run the application:
   \```bash
   mvn spring-boot:run
   \```

## Usage
- Access the application at http://localhost:8080.
- Register as a new user or log in with existing credentials.
- Browse and apply for job listings.
