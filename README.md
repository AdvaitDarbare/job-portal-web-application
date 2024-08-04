
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



## AWS Deployment

### Prerequisites
- AWS Management Console access

### Deployment Steps

1. **Create an Elastic Beanstalk Environment**:
    - Go to the AWS Management Console and navigate to Elastic Beanstalk.
    - Create a new environment and select the platform as "Java".

2. **Deploy the Application to Elastic Beanstalk**:
    - In the Elastic Beanstalk environment, upload your application’s WAR file.
    - Follow the prompts to deploy the application.

3. **Set Up RDS**:
    - Navigate to RDS in the AWS Management Console.
    - Create a new MySQL database instance.
    - Note the endpoint, username, and password for the database.

4. **Update Application Properties**:
    - Update the `application.properties` file with your AWS RDS database details:
      ```properties
      spring.datasource.url=jdbc:mysql://<aws-rds-endpoint>:3306/job_portal
      spring.datasource.username=admin
      spring.datasource.password=yourpassword
      ```

5. **Configure Parameter Store and Other Services**:
    - In the AWS Management Console, navigate to Systems Manager and configure the Parameter Store for any necessary environment variables.
    - Update your Elastic Beanstalk environment settings to include these parameters.

6. **Set Up Alarms (Free Tier)**:
    - Go to the AWS Management Console and navigate to CloudWatch.
    - Create an alarm to monitor the health and performance of your Elastic Beanstalk environment.
    - For example, you can create an alarm to monitor the CPU usage and set it to notify you if the usage exceeds a certain threshold.
    - Use the AWS Free Tier to set up these alarms without incurring additional costs.

By following these steps, you can run and deploy your Spring Boot job portal web application both locally and on AWS using the AWS Management Console.


