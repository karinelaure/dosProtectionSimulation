Server
-------------------------------------------------------------------
##  Requirements:
For building and running the application you need:
JDK 8 and Maven 4

## Installing and running:
Steps:
1. Clone this project to your server. 
2. Open cmd, navigate to the project folder.
3. Run: mvn clean install
4. Wait till Maven completes the build.
5. Navigate to target and run : java -jar dosProtectionServer-0.0.1-SNAPSHOT.jar

## Running Test
- To run test, Open cmd, navigate to the project folder.
- Run: mvn test

--------------------------------------------
Used libraries in maven: 
Spring boot
cache2k - to build cache with time frame threshold
---------------------------------------------------
## Configuration
- configuration file (project folder "/src/main/resources/application.properties") used to configure max number of requests per time frame threshold and time threshold unit.
## How it works
By Running program starts listening for incoming HTTP requests. 

- For each incoming HTTP request, Spring boot will open separate thread/task and it will call getClient inside DosProtectionController class.
- getClient will call isMaxRequestPerTime in class DosProtectionService, and it will Check if this specific client reached the max number of requests per time frame threshold (no more than 5 requests per 5 secs). 
- If not will send status code 200 (OK) otherwise status code 503 (Service Unavailable). 
- The server will run until key press after which it will gracefully drain all the threads/tasks and will exit. this happens by using SpringApplication.exit and configuration "server.shutdown=graceful" inside application.properties
  
