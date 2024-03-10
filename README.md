# Spring Technical Interview: spaceships management

This project is a technical interview test solution developed using Java, Spring Boot, and Maven. It implements a RESTful API for managing spaceships from series and movies.

## Features

- **CRUD Operations**: Allows CRUD operations for managing spaceships.
- **Pagination**: Supports pagination for fetching all spaceships.
- **Search by Name**: Enables searching for spaceships by name, returning matching results.
- **Exception Handling**: Implements centralized exception handling for robust error management.
- **Logging Aspect**: Includes an Aspect for logging requests for spaceships with negative IDs.
- **Caching**: Utilizes caching to improve API performance.
- **Unit Tests**: Includes unit tests for at least one class.
- **Database Storage**: Persists spaceships data in a database (e.g., H2 in-memory).
- **Git Repository**: The project is hosted in a Git repository, containing all source code and configurations.

## Optional Enhancements

- **DDL Script Management**: Utilizes a library for managing database schema DDL scripts.
- **Integration Tests**: Adds integration tests for testing API endpoints.
- **Dockerization**: Dockerizes the application for easier deployment and portability.
- **API Documentation**: Provides documentation for the API endpoints (e.g., using Swagger).
- **API Security**: Implements security measures (e.g., JWT authentication) to secure the API.
- **Message Queue Integration**: Integrates with a message broker (e.g., RabbitMQ, Kafka) for asynchronous communication.

## Setup Instructions

1. Clone the repository to your local machine.
2. Make sure you have Java, Maven, and a suitable IDE (e.g., IntelliJ IDEA, Eclipse) installed.
3. Configure the database connection properties in the `application.properties` file.
4. Build the project using Maven (`mvn clean package`).
5. Run the application using the generated JAR file (`java -jar target/application.jar`).
6. Access the API endpoints using a tool like Postman or a web browser.

## Folder Structure

```
├── src
│ ├── main
│ │ ├── java/com/in2/ahmed
│ │ │ ├── controllers # REST controller classes
│ │ │ ├── dto # DTO (Data Transfer Object) classes
│ │ │ ├── exceptions # Custom exception classes
│ │ │ ├── mappers # Mapper classes for DTO and entity conversion
│ │ │ ├── model # Entity classes
│ │ │ ├── repository # Repository interfaces
│ │ │ └── services # Service classes
│ │ └── resources # Application properties and static resources
│ └── test # Unit and integration test classes
```

## Author

Ahmed El Moukhtari Koubaa

## License

This project is licensed under the [MIT License](LICENSE).
