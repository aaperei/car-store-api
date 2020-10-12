# Welcome to Car Store API Project!

This project was implemented in order to demonstrate how to build rest APIs using Spring Boot.  The project structure was generated using [Spring Initilizr](https://start.spring.io/)

For this demonstration purpose, we are creating a collection of APIs to manage a car store. 

The project is using the memory database H2 in order to facilitate the demonstration. When the application starts the database is created and some cars are inserted into the database. 


I also implemented a front end project in order to show how to consume the APIs: [car-store-front-end](https://github.com/aaperei/car-store-front-end)


# Requirements
All project dependencies are managed by Maven. Besides maven dependencies, you will need the following:

 - JDK 11
 - Any Web Server compatible with Java 11. I recommend Tomcat 9. You can download Tomcat from this link: [https://tomcat.apache.org/download-90.cgi](https://tomcat.apache.org/download-90.cgi)

# Available APIs
All APIs use JSON format as the communication pattern to send and receive information

 - `GET /veiculos` - Return all cars
 - `GET /veiculos/find` - Return a list of cars according to the parameter q
 - `GET /veiculos/{id}` - Return the details of a specific car
 - `POST /veiculos` - Adds a new car
 - `PUT /veiculos` - Updates car attributes 
 - `PATH /veiculos/{id}` - Updates some car attributes 
 - `DELETE /veiculos/{id}`- Deletes a specific car