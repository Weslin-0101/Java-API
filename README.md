# RESTful API - SpringBoot

On this page, you can find the documentation for the RESTful API developed in SpringBoot.

If you want, you can to read this ReadMe in Portuguese PT-BR [Here](aboutProject/PT_BR_ReadME.md)

### Author:

[Wesley Lira - Linkedin](https://www.linkedin.com/in/wesley-lira-9204b8205/)

## About the project:

RESTful API developed in SpringBoot for my project to put into practice everything I needed to learn about SpringBoot. The project consists of an API for an account management system, where you can:

- **Create an account**
- **List all accounts by ID, name, or email**
- **Update an account**
- **Delete an account**
- **Login system generating JWT Token with a duration of 1 hour**
- **Route access system through JWT Token*8

In total, the project has practical knowledge in areas such as:

- **SpringBoot**
- **Spring Security**
- **Spring Data JPA**
- **Spring Validation**
- **Unit Testing with Junit and Mockito**
- **Swagger UI**
- **PostgreSQL database**

## Technologies used:

- **Java 11**
- **SpringBoot 3.1.1**
- **Spring Security**
- **PostgreSQL**
- **Swagger UI**
- **Docker**

## How to run the project:

**Prerequisites: Java 11, PostgreSQL, Docker**

After cloning the project, you should run the following command in your terminal to start
the database locally:

```
    docker compose up
```

After that, simply run the project in SpringBoot and access the API documentation by opening
your web browser and navigating to the following address:

```
    http://localhost:8080/swagger-ui.html
```

If you want to import the API documentation into Postman, simply access the link above
for documentation and click on the following link:

```
    http://localhost:8080/v3/api-docs
```

Now you're all set to test the API :)