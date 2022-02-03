# CAPSTONE PROJECT QA-DFESW7

>Bank REST API written in JAVA with SpringBoot framework

![Java](https://img.shields.io/badge/-java-blue)
![mysql](https://img.shields.io/badge/-MySQL-005C84)
![bash](https://img.shields.io/badge/Spring_Boot-orange)
![GitHub issues](https://img.shields.io/github/issues/vinaypandat/qa-final-project)
![GitHub repo size](https://img.shields.io/github/repo-size/vinaypandat/qa-final-project)
![GitHub last commit](https://img.shields.io/github/last-commit/vinaypandat/qa-final-project)

---

## Why are we doing this?

This is the final project(Bank API) of QA Software Development Bootcamp where we are implementing our learnings during
the 9 weeks training. The project requires us to create a working Spring Boot API. With this we will be able
to test our understanding in the following:
- Agile & Project Management (Git, Jira)
- Databases & Cloud Fundamentals (H2, MySQL)
- Programming Fundamentals (Java)
- API Development (Spring Boot)
- Automated Testing (JUnit, Mockito)
- API Calls using Postman

<details>
<summary>Project MVP Requirements</summary>

> -	Code fully integrated into a Version Control System using the feature-branch model: main/dev/multiple features.
> -	A project management board with full expansion on user stories, acceptance criteria and tasks needed to complete the project.
> -	A risk assessment which outlines the issues and risks faced during the project timeframe.
> -	A relational database, locally or within the Cloud, which is used to persist data for the project.
> -	A functional application ‘back-end’, written in a suitable framework of the language covered in training (Java/Spring Boot), which meets the requirements set on your Scrum Kanban board.
> -	A build (.jar) of your application, including any dependencies it might need, produced using an integrated build tool (Maven).
> -	A series of API calls designed with postman, used for CRUD functionality. (Create, Read, Update, Delete)
> -	Fully designed test suites for the application you are creating, including both unit and integration tests.
</details>

<details>
<summary> Tech Stack </summary>

- Version Control System: **Git**
- Source Code Management: **Github**
- Kanban Board: **Jira(Scrum Board)**
- Database Management System: **H2(Development) and MySQL(Production)**
- Core Language: **Java**
- API Dev platform: **Spring**
- Build Tool: **Maven**
- Unit & Integration Testing: **JUnit**
</details>

### How did I expect this challenge to go?
I started with the planning of structure and design of the challenge. I had laid out the initial structure
of the project on Jira boards. I have expected this challenge to be easy, but I faced a few difficulties in
writing the system integration test and tests for exceptions for which I had to use the QA community tutorials,
recorded lectures and other online resources.

### What went well? / What didn't go as planned?
I did find writing the whole API easy. I had finished MVP in just two days, and then I did stretch goals i.e.
custom exception, custom queries, testing with Mockito and got over 90% of coverage. I was expecting to finish
other stretch goals like one-to-many and many-to-one entity relationships but writing tests and documentation 
took more time than I was expecting.

### Possible improvements for future revisions?
The future improvement for this Bank API that I think of will be to create an entity Account and one-to-many and
many-to-one relationship, then add functionality like balance retrieval and fetching other banking details.

---

## Usage

### Field Constraints

- **id:** auto-generated.
- **username:** Must be unique and  not null.
- **password:** not null.
- **firstName:** not null and must be between 2 and 15 characters.
- **lastName:** not null and must be between 2 and 15 characters.
- **age:** integer and between 18 and 120.

### To use Bank API you need to take the following steps:

1. Clone the GitHub repository using `git clone https://github.com/vinaypandat/qa-final-project.git`.
2. You can either run the JAR package within the cloned folder using `java -jar Bank-0.0.1-SNAPSHOT.jar` or you can open the project
in Eclipse and run it as a Spring Boot project.
3. The application runs on port 8080 for development and port 80 for production on `http://localhost/`.
4. To use API you can use either Postman or Swagger.
5. Link for swagger UI is `http://localhost:8080/swagger-ui/index.html#/`


#### Following methods can be used with this API:

<details>  
<summary> CREATE method </summary> 

- Creates user in database. To register a user, username field must be unique. If username already exist, 
it will give and error.

- Create function requires `POST` method on `http://localhost:8080/user/register/` and `JSON` data
in the body in the form below.
- `username` field must be unique.
    
    ```json
    {
        "username": "jackwz",
        "password": "pass6",
        "firstName": "Jim",
        "lastName": "Rice",
        "age": 23
    }
    ```
- On successful creation of user in database, it will return `JSON` body.
  
    ```json
    {
        "id": 4,
        "username": "jackwz",
        "password": "pass6",
        "firstName": "Jim",
        "lastName": "Rice",
        "age": 23
   }
   ```
- If `username` already exists in database, the following error will be returned:

    ```json
    {
        "httpStatus": "CONFLICT",
        "error": "User with this username already exists"
    }
    ```
</details>

<details>
<summary>READ methods</summary>

- There are two methods to READ the data. `getUsers` returns all users from the database and `getUserByUsername` returns
user with the `username` given.
- To use READ all users, use `GET` method on `http://localhost:8080/user`.
- To use READ user by `username`, use `GET` method on `http://localhost:8080/user/{username}`.

</details>

<details>
<summary>UPDATE method</summary>

- Update requires `id` which can be passed to `http://localhost:8080/user/update/{id}` and body in `JSON` 
format. 

    ```json
    {
        "username": "jackwz",
        "password": "pass6",
        "firstName": "Jim",
        "lastName": "Rice",
        "age": 23
    }
    ```
- On successful update, the update user will be returned.
- If `id` doesn't exists in database, the following error will be returned:

    ```json
    {
        "httpStatus": "NOT_FOUND",
        "error": "User with ID 4 doesn't exist"
    }
    ```
</details>

<details>
<summary>DELETE</summary>

- Delete requires only `id` which can be passed to `http://localhost:8080/user/delete/{id}`.
- On successful deletion, `JSON` body will be returned.

  ```json
  {
      "id": null,
      "username": "jackwz",
      "password": "pass6",
      "firstName": "Jim",
      "lastName": "Rice",
      "age": 23
  }
  ```

- If `id` doesn't exists in database, the following error will be returned:

    ```json
    {
        "httpStatus": "NOT_FOUND",
        "error": "User with ID 4 doesn't exist"
    }
    ```

</details>

---

## Screenshots
### Postman

<details>
<summary>CREATE</summary>

> Create user if username doesn't exist in the database.

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Postman/beforeDTO/READ_all.png?raw=true")

</details>

<details>
<summary>READ</summary>

> Read all users from the database.

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Postman/beforeDTO/READ_all.png?raw=true)

> Read user by username passed from the database.

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Postman/beforeDTO/READ_ByUsername.png?raw=true)

</details>

<details>
<summary>UPDATE</summary>

> Updates existing user in the database.

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Postman/beforeDTO/UPDATE_User.png?raw=true)

</details>

<details>
<summary>DELETE</summary>

> Deletes existing user from the database.

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Postman/beforeDTO/DELETE_User.png?raw=true)

</details>

### Database

<details>
<summary>Table creation on first run of application</summary>

> Empty user table in the database after first run of the application.

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Database/EmptyDatabaseOnStart.png?raw=true)

</details>

<details>
<summary>Populated database</summary>

> Populated user table in the database using CREATE operations.

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Database/PopulatedDB_UsingCreate.png?raw=true)

</details>

### Test Results
<details>
<summary>Controller Tests</summary>

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Tests/UserControllerIntegrationTest.png?raw=true)
![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Tests/UserControllerSystemIntegrationTest.png?raw=true)

</details>
<details>
<summary>Service Tests</summary>

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Tests/UserServiceIntegrationTest.png?raw=true)
![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/Tests/UserServiceUnitTest.png?raw=true)

</details>

### ERD & UML

<details>
<summary>ERD</summary>

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/ERD/User_ERD.png?raw=true)

</details>

<details>
<summary>UML</summary>

![](https://github.com/vinaypandat/qa-final-project/blob/dev/Documents/Screenshots/UML/UML.png?raw=true)

</details>

---

## Links

- [Jira boards](https://vinaypandat.atlassian.net/jira/software/projects/QFP/boards/4) 
- [JAVA-docs](https://vinaypandat.github.io/QA-Final-Project-Java-docs/)               
- [Coverage Report](https://vinaypandat.github.io/Coverage-Report-QA-Project/)

---

## Licence

Distributed under the MIT Licence.

---

## Contact
Developer: Vinay Pandey - engg.vinaypandey@gmail.com
