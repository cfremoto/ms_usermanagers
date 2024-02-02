# MS_UserManagers

MS_UserManagers is a robust and scalable Spring Boot microservice designed to manage users. It provides a comprehensive set of features for user management, including creating, updating, and retrieving user information.

## Features

- User Registration: Allows new users to register with their email and password.
- User Authentication: Validates user credentials and generates a unique token for authenticated sessions.
- User Management: Allows for the retrieval, update, and deletion of user profiles.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 11+
- Maven

### Installing

1. Clone the repository
```bash
git clone https://github.com/yourusername/ms_usermanagers.git
```

2. Navigate to the project directory
```bash
cd ms_usermanagers
```

3. Build the project using Maven
```bash
mvn clean install
```

4. Run the Spring Boot application
```bash
mvn spring-boot:run
```

The server will start on port 8080.

## API Endpoints

The service exposes the following RESTful endpoints:

- `POST /api/v1/users`: Register a new user.
- `GET /api/v1/users/{id}`: Retrieve a user's information.
- `GET /api/v1/users`: Retrieve a list of all users.
- `POST /api/v1/authenticate`: Authenticate a user.
- `PUT /api/v1/users/{id}`: Update a user's information.
- `DELETE /api/v1/users/{id}`: Delete a user.

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
- [Maven](https://maven.apache.org/) - Dependency Management
- [H2]() - Used to generate RSS Feeds

## Authors

- **Ing. Cesar Fernandez**

## License

This project is licensed under the MIT License

## Acknowledgments

- Hat tip to anyone whose code was used
- Inspiration
- etc