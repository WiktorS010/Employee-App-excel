#Employee Excel App

LoginThymeleafSB is a Spring Boot application designed as a learning project. It demonstrates how to manage data using Java and MySQL, export data to Excel files, and create a basic CRUD web application with Spring Boot and Thymeleaf. Additionally, the project includes basic user authentication using Spring Security.

Features

User Authentication:

Supports user creation with email and password.

Uses Spring Security to handle login and session management securely.

CRUD Operations:

Manage employee data (add, edit, delete, view).

Data is stored in a MySQL database.

Excel File Integration:

Export employee data from the database to Excel.

Import Excel files to the application for processing (basic functionality).

Frontend:

Uses Thymeleaf for server-side rendering.

Provides a user-friendly interface for data management and authentication.

Technologies Used

Backend

Spring Boot: For building the web application.

Spring Data JPA: For database interaction.

Spring Security: For authentication and authorization.

Database

MySQL: To store application data.

Frontend

Thymeleaf: For dynamic HTML templates.

Others

Apache POI: For reading and writing Excel files.

Lombok: For reducing boilerplate code in Java.

IntelliJ IDEA Ultimate: Used for development (requires specific plugins for proper execution).

Requirements

Java 17: The application uses Java 17.

MySQL: Ensure a running MySQL database with the necessary credentials.

IntelliJ IDEA Ultimate: Recommended for running the project due to specific plugin requirements.

Setup Instructions

Clone the Repository:

git clone <repository-url>
cd LoginThymeleafSB

Set Up the Database:

Create a new MySQL database.

Update the database credentials in the application.properties file located in the src/main/resources directory:

spring.datasource.url=jdbc:mysql://localhost:3306/<your-database-name>
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>

Build and Run the Application:

mvn spring-boot:run

Access the Application:

Open your browser and navigate to http://localhost:8080.

Usage

Authentication

Navigate to the login page to sign in.

Register a new user if needed.

Employee Management

Add new employees with their details.

Edit or delete existing employee records.

Excel Integration

Export all employee records to an Excel file.

Limitations

Exception Handling:

The project lacks proper exception handling mechanisms.

Plugins:

Developed using IntelliJ IDEA Ultimate. Some features may not work properly in other environments without additional configuration.

Testing:

There is no test included in this project.

Dependencies

Key Dependencies from pom.xml:

spring-boot-starter-data-jpa: For database interaction.

spring-boot-starter-thymeleaf: For the frontend.

spring-boot-starter-security: For authentication and authorization.

mysql-connector-java: For connecting to MySQL.

poi-ooxml: For working with Excel files.

spring-boot-starter-web: For REST and MVC.

lombok: For simplifying Java code.

License

This project is a personal learning project and is not licensed for production use. Feel free to use it as a reference.

Acknowledgments

Developed as a self-learning exercise to explore Spring Boot and Thymeleaf.

Thanks to various online tutorials and documentation that guided the development process.
