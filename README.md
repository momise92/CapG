# Cap-Engagement
Platform Capgemini for collaborator and association who can post events !
The front-end it's developpe with Vuejs.    
You can see it on Herebos's repository : [Front-end](https://github.com/Herebos/capgFront).  
I deploy the platform on the cloud Pivotal : [Capengagment](https://capg.cfapps.io/)

----------------------

## Requirements

# CapG  

You need to create the file Application.properties on the path : src/main/resources.  

`The file must contain :`

>spring.jpa.hibernate.ddl-auto=create  
>spring.datasource.url=jdbc:mysql://localhost:8889/NameDatabase  
>spring.datasource.username=  
>spring.datasource.password=  
>spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect    
>jwt.secret=SecretCode    
>jwt.expiration=604800 #milliseconds  
>server.port=8181   

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.capg.capGApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Documentation API

You can show the documentation generate by Swagger
http://localhost:8181/swagger-ui.html

