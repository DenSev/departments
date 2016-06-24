The project was designed for management of departments and corresponding employees. It consists of two separate web applications: a client and a REST service. REST service is used to work with database and client provides a ui for user to interact with. 

Requirements:
	-Maven
	-MySql server
	-Tomcat

To install the application you need:
1. In the root directory there is a config.properties file. Please open the file and configure your database connection.
2. On your MySql server create two schemas: {db.schema} and {db.liquibase.schema} as configured in config.properties.
3. Enter department-rest directory and execute update-db.bat file or open command line and run following command 'mvn -P liquibase clean initialize liquibase:update' (without quotes). This should create database tables required by the application.
4. Return to root directory and open command line. From command line run following command: 'mvn clean install' (without quotes). This should create two war files in 'department/target' and 'department-rest/target' directories
5. Copy war files from 'department/target' and 'department-rest/target' directories to 'webapps' directory of your Tomcat.
6. Start tomcat

The client app will be available at: http://localhost:8080/departments
The REST service will be available at http://localhost:8080/departments-rest
Important! Note that client application requires that REST service is available at http://localhost:8080/departments-rest address, otherwise jsp version of client will not work.
