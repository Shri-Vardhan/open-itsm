# Open ITSM

Open ITSM is a modular IT Service Management (ITSM) platform built using Spring Boot and Java 21. The platform provides a foundation for implementing standardized service management capabilities while maintaining a clean, scalable, and maintainable architecture suitable for enterprise environments.

---

## Technology Stack

* Java 21
* Spring Boot 3.3.5
* Spring Security
* Spring Data JPA
* Spring JDBC
* Apache Log4j
* Gradle
* JUnit 5

---

## Key Capabilities

* Secure application framework using Spring Security
* Session-based authentication and authorization
* Modular service-oriented architecture
* JPA-based persistence layer
* Environment-specific configuration management
* Centralized logging and diagnostics
* Enterprise-ready development standards

---

## Environment Profiles

The application supports the following runtime profiles:

| Profile | Purpose                       |
| ------- | ----------------------------- |
| default | Default runtime configuration |
| dev     | Local development and testing |
| prod    | Production deployment         |

---

## Prerequisites

* Java 21 or later
* Git
* Gradle (or Gradle Wrapper)

Verify installation:

```bash
java --version
./gradlew --version
```

---

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/Shri-Vardhan/open-itsm.git
cd open-itsm/open-itsm-parent
```

### Build the Application

```bash
./gradlew :application:build
```

### Build All Modules

```bash
./gradlew clean build
```

### Run the Application

```bash
./gradlew :application:bootRun
```

### Run with a Specific Profile

```bash
./gradlew :application:bootRun --args='--spring.profiles.active=dev'
```

```bash
./gradlew :application:bootRun --args='--spring.profiles.active=prod'
```

---

## Logging

Application logging is implemented using Apache Log4j and supports operational monitoring, troubleshooting, security auditing, and application diagnostics.

---

## Security

Security is implemented using Spring Security with session-based authentication and authorization mechanisms. The security layer is designed to support enterprise access-control requirements and future extensibility.

---

## Testing

Execute all tests:

```bash
./gradlew test
```

Run a full verification build:

```bash
./gradlew clean build
```

---

## Contributing

Contributions, enhancements, and issue reports are welcome. Please submit changes through a pull request and ensure all tests pass before submission.

---

## License

This project is licensed under the applicable project license.

---

## Maintainer
Chinthalapudi Shri Vardhan
