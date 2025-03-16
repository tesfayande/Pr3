# Project Spring Boot And Jwt Backend Authentication

## Installation

1. Open a terminal or command prompt and navigate to the directory where you want to clone the repository.

Run the following command to clone the repository:

    git clone  https://github.com/tesfayande/Pr3.git

2.  Navigate to the cloned repository directory:
    `cd Pr3`


## Config Databse

1 Open: src/main/resources/application.properties
### Use existed one or create/update/create-update
      spring.jpa.hibernate.ddl-auto=update  
      spring.datasource.url=jdbc:mysql://localhost:3306/Pr3?createDatabaseIfNotExist=true   
      spring.datasource.username=
      spring.datasource.password=
      spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver

## Compile, Build & Run

Before you compile and build, make sure you are at the project directory SpringBootProjectDir of this repo. Take note also, I'm using Maven build tool here.

to package your program as an executable jar file:

    mvn clean package

to simply clean and compile:

    mvn clean compile
to just clean your project:

    mvn clean
to run the program:

    mvn spring-boot:run
to build and run the program:

    mvn clean install
