# InstantTech

Freelancing solution for Engineers

## Getting Started

### Prerequisites

- Sdkman `curl -s "https://get.sdkman.io" | bash`
- Java Development Kit (JDK) 17 or higher
- Gradle 7.6.1
- Docker (only if you want to run the project with docker)

### Running the program

1. Clone the repo: `git clone https://github.com/Ramo-11/registration.git`
2. Ensure you are using Gradle 7.6.1 and Java 17
    ```
   sdk install gradle 7.6.1
   sdk use gradle 7.6.1
   sdk install java 17.0.6-tem
   sdk use java 17.0.6-tem
    ```
3. Run `gradle wrapper`
4. Option 2 (Docker container):
    - Run `./gradlew clean build`
    - Build the Docker image: `docker build -t instanttech .`
    - Run the Docker image: `docker run -p 8080:8080 instanttech`
5. Option 3 (Jar file)
    - Run `./gradlew clean build`
    - Run the jar: `java -jar build/libs/instanttech-1.0.0.jar `
6. Access the application:
    - Open your web browser and visit `http://localhost:8080`

## Contact

For any inquiries or questions, contact:

- Omar Abdelalim
- Email: omarh5877@gmail.com
