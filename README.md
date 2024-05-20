# UsersManagerNissum

Technical test for selection process in Nissum, RESTful API for users creation.

## Creation User

## Index

1. [Description](#description)
2. [Requirements](#requirements)
    1. [Password policy](#password-policy)
    2. [Email policy](#email-policy)
3. [Configuration](#configuration)
4. [Run from IntelliJ IDEA](#run-from-intellij-idea)
5. [Run from the Console](#run-from-the-console)
6. [H2 Database](#h2-database)
7. [Access to Swagger](#access-to-swagger)
8. [Test from Postman](#test-from-postman)
9. [DB Diagram](#db-diagram)
10. [Solution Diagram ](#Solution-Diagram)

### 1. Description

This API for user creation, allows us adding new users the form RESTFul, with the use of Design Patterns, 
good practices, principles of the REST architecture and JSON format.

##### Technologies:

- Java 17
- Spring boot 3.2.5
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Swagger v3
- Lombok
- H2 Database
- Junit 5
- Mockito
- GIT

### 2. Requirements

#### 2.1 Password policy

The current password validation consider the following rules:

- There must be at least one capital letter.
- There must be at least one lowercase letter.
- There must be at least one number.
- There must be at least one of the special characters listed ($@#%&!).
- There must be at least 8 characters long.

#### 2.2 Email policy

The email must have the next regular expression: aaaaaaa@dominio.cl

- The nickname can have letters, numbers and the signs: ._%+-
- The domain can have letters, numbers and the signs: .-
- The mail should not be too long

### 3. Configuration

1. Clone the repository in your local machine

   Note: You must have installed the Git service for repo cloning.

   In the git console:

        git clone  https://github.com/mayra0419/usersmanagernissum
2. In the option 'Project structure':

        SDK 17.0.10

### 4. Run from IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Select "File" from the menu bar and then "Open".
3. Navigate to the project directory and select the build.gradle file.
4. Wait for IntelliJ to configure the project.
5. Navigate to the main class UsersApplication.
6. Right-click on the main class and select "Run" to run the application.

### 5. Run from the Console

1. Run the following command to build the project and generate the JAR:
   ```bash
   ./gradlew bootJar

2. Once the construction is finished, navigate to the directory `build/libs`:
   ```bash
   cd build/libs

3. Run the JAR application using the following command:
   ```bash
   java -jar usersmanager-0.0.1-SNAPSHOT.jar

### 6. H2 Database

The application use in-memory database, his structure is created following the jpa convention in code through annotations in the model folder.

You can access the H2 database administration console while the application is running:

1. Navigate to the following URL in your browser:
   ```bash
   http://localhost:8080/h2-console

### 7. Access to Swagger

Once the application is running, you can access the Swagger documentation through the following URL in your browser:

 ```bash
  http://localhost:8080/swagger-ui/index.html
 ```

### 8. [Test from Postman](doc/usersmanager.postman_collection.json)

Import the Postman collection provided in the doc folder with the name `usersmanager.postman_collection.json` to test
the API endpoints.

### [9. DB Diagram](doc/UserManaganerDB.drawio.png)

### [10. Solution Diagram](doc/UsersManagerDiagramSolution.drawio.png)