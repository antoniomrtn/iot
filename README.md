# Test Antonio Martín

## Prerequisites

JDK 17 installed 
Maven installed 
Oracle DB installed 

## Execution steps

- Clone the reposirtoy
 - Execute SQL script. You can find it in iot/src/main/resources/data-oracle.sql
 - Open iot/src/main/resources/application.properties and change the properties regarding the datasource connection if needed according to your DB configuration. The default port is 8084

## API

- In the root folder execute the following command in order to run the application:
 ```
mvn spring-boot:run
```

Once it is running you can make requests to the API endpoints:

- Get devices waiting for activation:
 ```
GET http://localhost:8084/api/v1/devices?status=WAITING FOR ACTIVATION
```
- Update configuration status:
 ```
POST http://localhost:8084/api/v1/devices/1
body request:{
{
	"id": 1,// SIM ID
	"status": "ACTIVE" // SIM STATUS
}
```
- Get devices available for sale
 ```
GET http://localhost:8084/api/v1/ready
```

## Tests

- In the root folder execute the following command in order to run integration and unit tests:
 ```
mvn test
```

