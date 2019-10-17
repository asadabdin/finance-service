# Finance Application
This project has 2 responsibility 
 - Validate the vat number by url `/v1/api/vat/validate` to validate the `vat-code`
 - to convert amount into different currency by url `/v1/api/currency/convert` 


## Prerequisite
This Project can be build as docker image or using Gradle, 
to build using docker you need `docker` installed in your build environment
and to build by gradle you need `gradle` and `JDK-8` installed. 


## Build Information
### Docker
#### Build Docker image
To build the project please execute 
````dockerfile
docker image build -t finance-service:latest .
````
This will create an docker image with name `finance-service` and tag `latest`

#### Run the docker image
To run the image please execute
````dockerfile
docker run -d --restart=always -p 8081:8081 finance-service:latest
````

### Gradle
#### Build Gradle
To build the project please execute 
````
clean build jacocoTestReport
````

#### Run the docker image
To run the image please execute
````
java -jar build/libs/finance-service-1.0.0-SNAPSHOT.jar
````

## Test
Application is configured to pen on port 8081 
so, open below url on any browser and you can test the api's using swagger 

[Test Api](http://localhost:8081/swagger-ui.html)

## Reports
Test report are available on 
- Code coverage can be seen by clicking -> [Jacoco Report](http://localhost:63342/finance-service/org.asad.finance-service/build/coverageReportDir/test/html/index.html)
- Test Results can be seen by clicking  -> [Test Result](http://localhost:63342/finance-service/org.asad.finance-service/build/reports/tests/test/index.html)
