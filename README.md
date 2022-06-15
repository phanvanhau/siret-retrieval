This is home assignment for enterprise SIRET retrieval
# Build instructions
## Buid and run with Docker
At the root of the project
```
chmod +x ./docker/build.sh
./docker/build.sh
docker run -it --rm --name siret-retrieval -p 9000:9000 siret-retrievel:0.0.1
```
## Local development
```
./mvnw clean package

./mvnw spring-boot:run
OR
java -jar target/siret-retrieval-0.0.1-SNAPSHOT.jar
```

# How to improve the project
- Generate the microservice client API and server API with swagger
    - The client API could be imported by other microservices without the need for the caller to add more code to build the HTTP request
    - The server API would be deployed independently
- The process updating enterprise data would be heavy if the number of registered enterprise is important.
The current implementation is done with non-blocking mechasnim but without the need for the caller to know the process status, also detailed failure if any.
    - Use Spring Batch could be more relevant for this kind of job
    - Return the process id and provide another endpoint for the caller to check the job status. 
