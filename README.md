# CAPSTONE PROJECT QA-DFESW7
>Bank REST API written in JAVA with SpringBoot framework

![Java](https://img.shields.io/badge/-java-blue)
![mysql](https://img.shields.io/badge/-MySQL-005C84)
![bash](https://img.shields.io/badge/Spring_Boot-orange)
![GitHub issues](https://img.shields.io/github/issues/vinaypandat/qa-final-project)
![GitHub repo size](https://img.shields.io/github/repo-size/vinaypandat/qa-final-project)
![GitHub last commit](https://img.shields.io/github/last-commit/vinaypandat/qa-final-project)


## Project Specifications

This is final Springboot API project of QA Software Development Bootcamp.

### MVP Requirements

> -	Code fully integrated into a Version Control System using the feature-branch model: main/dev/multiple features.
> -	A project management board with full expansion on user stories, acceptance criteria and tasks needed to complete the project.
> -	A risk assessment which outlines the issues and risks faced during the project timeframe.
> -	A relational database, locally or within the Cloud, which is used to persist data for the project.
> -	A functional application ‘back-end’, written in a suitable framework of the language covered in training (Java/Spring Boot), which meets the requirements set on your Scrum Kanban board.
> -	A build (.jar) of your application, including any dependencies it might need, produced using an integrated build tool (Maven).
> -	A series of API calls designed with postman, used for CRUD functionality. (Create, Read, Update, Delete)
> -	Fully designed test suites for the application you are creating, including both unit and integration tests.

### Tech Stack

- Version Control System: **Git**
- Source Code Management: **Github**
- Kanban Board: **Jira(Scrum Board)**
- Database Management System: **H2(Development) and MySQL(Production)**
- Core Language: **Java**
- API Dev platform: **Spring**
- Build Tool: **Maven**
- Unit & Integration Testing: **JUnit**

---

## Links
- [JAVA-docs](https://vinaypandat.github.io/qa-final-project/)
- [Jira boards](https://vinaypandat.atlassian.net/jira/software/projects/QFP/boards/4)

---
## Usage
### Field Constraints

- **id:** auto-generated.
- **username:** Must be unique and  not null.
- **password:** not null.
- **firstName:** not null and must be between 2 and 15 characters.
- **lastName:** not null and must be between 2 and 15 characters.
- **age:** integer and between 18 and 120.

### CRUD operations
#### CREATE

Creates user in database. To register a user, username field must be unique.
> http://localhost:8080/user/register/

#### READ

Read all users from database.

>http://localhost:8080/user/

#### UPDATE

Updates user by id. It will give user doesn't exit if ID is not in the database.

>http://localhost:8080/user/update/{id}

#### DELETE

Deletes user by id. It will give user doesn't exit if ID is not in the database.

>http://localhost:8080/user/delete/{id}

---

## Screenshots
### Postman

> Read all users from database

![](https://github.com/vinaypandat/qa-final-project/blob/documents/Documents/images/postman/READ_all.png)

### Database
> Will be posted soon
### Test Results
> Will be posted soon
### Coverage Report
> Will be posted soon

## Licence

Distributed under the MIT Licence.

## Contact
Developer: Vinay Pandey - engg.vinaypandey@gmail.com
# CAPSTONE PROJECT QA-DFESW7
>Bank REST API written in JAVA with SpringBoot framework

![Java](https://img.shields.io/badge/-java-blue)
![mysql](https://img.shields.io/badge/-MySQL-005C84)
![bash](https://img.shields.io/badge/Spring_Boot-orange)
![GitHub issues](https://img.shields.io/github/issues/vinaypandat/qa-final-project)
![GitHub repo size](https://img.shields.io/github/repo-size/vinaypandat/qa-final-project)
![GitHub last commit](https://img.shields.io/github/last-commit/vinaypandat/qa-final-project)


## Project Specifications

This is final Springboot API project of QA Software Development Bootcamp.

### MVP Requirements

> -	Code fully integrated into a Version Control System using the feature-branch model: main/dev/multiple features.
> -	A project management board with full expansion on user stories, acceptance criteria and tasks needed to complete the project.
> -	A risk assessment which outlines the issues and risks faced during the project timeframe.
> -	A relational database, locally or within the Cloud, which is used to persist data for the project.
> -	A functional application ‘back-end’, written in a suitable framework of the language covered in training (Java/Spring Boot), which meets the requirements set on your Scrum Kanban board.
> -	A build (.jar) of your application, including any dependencies it might need, produced using an integrated build tool (Maven).
> -	A series of API calls designed with postman, used for CRUD functionality. (Create, Read, Update, Delete)
> -	Fully designed test suites for the application you are creating, including both unit and integration tests.

### Tech Stack

- Version Control System: **Git**
- Source Code Management: **Github**
- Kanban Board: **Jira(Scrum Board)**
- Database Management System: **H2(Development) and MySQL(Production)**
- Core Language: **Java**
- API Dev platform: **Spring**
- Build Tool: **Maven**
- Unit & Integration Testing: **JUnit**

---

## Links
- [JAVA-docs](https://vinaypandat.github.io/qa-final-project/)
- [Jira boards](https://vinaypandat.atlassian.net/jira/software/projects/QFP/boards/4)

---
## Usage
### Field Constraints

- **id:** auto-generated.
- **username:** Must be unique and  not null.
- **password:** not null.
- **firstName:** not null and must be between 2 and 15 characters.
- **lastName:** not null and must be between 2 and 15 characters.
- **age:** integer and between 18 and 120.

### CRUD operations
#### CREATE

Creates user in database. To register a user, username field must be unique.
> http://localhost:8080/user/register/

#### READ

Read all users from database.

>http://localhost:8080/user/

#### UPDATE

Updates user by id. It will give user doesn't exit if ID is not in the database.

>http://localhost:8080/user/update/{id}

#### DELETE

Deletes user by id. It will give user doesn't exit if ID is not in the database.

>http://localhost:8080/user/delete/{id}

---

## Screenshots
### Postman

> Read all users from database

![](https://github.com/vinaypandat/qa-final-project/blob/documents/Documents/images/postman/READ_all.png)

### Database
> Will be posted soon
### Test Results
> Will be posted soon
### Coverage Report
> Will be posted soon

## Licence

Distributed under the MIT Licence.

## Contact
Developer: Vinay Pandey - engg.vinaypandey@gmail.com
