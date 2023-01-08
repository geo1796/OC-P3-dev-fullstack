# RentalApp

## Prerequisites

The following must be installed in order to run the app.

* JDK 17 
* MySql 8.0.xx 
* MySql Workbench (Optional)
* Node.js 18 & Npm 8 for the local file server

## Installation

1. Install JDK 17 : https://adoptium.net/temurin/releases/?version=17
2. Install MySql : https://dev.mysql.com/downloads/installer/
3. Install MySql Workbench : https://www.mysql.com/products/workbench/
4. Install Node.js & Npm: https://nodejs.org/en/download/ 

Once MySql is installed, you will be 
asked to configure the password for the default root account. This code 
uses the default root account to connect and the password can be set 
as rootroot. If you add another user/credentials make sure to change the 
same in the application.properties file of the Spring Boot project.

## Database configuration

1. Start MySql
2. Start MySql Workbench and use it to connect to the local instance of MySql you just started.
3. Use MySql Workbench to run the script available at ./database/schema.sql from the root of this project.

## Pictures server configuration

1. Open a terminal & run ````npm install -g http-server````
2. In the terminal, go to the folder pictures at the root of this project
3. Run ```http-server ./```

## Run the app

Once MySql is running and the rental database is initialized, you are ready to run the App :   
```mvn spring-boot:run```

## Swagger

Once the app is running, the API docs is available at http://localhost:3001/api/swagger-ui/
