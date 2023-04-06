## Technologies/Tools Used

Java 11,
Intellij Idea for Development,
Maven Build Tool ,
JAXB,Swagger Libraries,Springboot with REST API's and 
Lombok Libraries

## Setup & Run Instructions

mvn clean install - it will build application

mvn spring-boot:run - it will run the application

## Swagger Documentation

http://localhost:9090/atg/swagger-ui.html#/harry-kart-controller

## Future Code Improvements

Can use JDK 17 and other versions and its features to replace if conditions using Optional.ofNullable().ifPresentOrElse() etc

# Harry-Kart
## Description
Harry-Kart in a special kind of horse racing.

The horses participating have a base speed, they can run with that speed indefinitely.
The track is a 1000 meters loop and is divided in lanes, each horse runs on a lane and every lane has the same length.   
The horses run the first loop at their base speed but at the end of each loop they find a power-up or power-down.

The power-ups/downs are numbers, negative or positive, representing how much the horse speeds up or slows down.

Your task is to compute the top 3 ranking.

## Example 1

### Input

**Number of loops:** 3

**Start List:**

| Lane | Horse name     | Base speed |
|------|----------------|------------|
| 1    | TIMETOBELUCKY  | 10         |
| 2    | CARGO DOOR     | 10         |
| 3    | HERCULES BOKO  | 10         |
| 4    | WAIKIKI SILVIO | 10         |

**Power-Ups/Downs:**

| Loop | Lane 1 | Lane 2 | Lane 3 | Lane 4 |
|------|--------|--------|--------|--------|
| 1    | 1      | 1      | 0      | -2     |
| 2    | 1      | -1     | 2      | -2     |

### Result

| Position | Horse Name    |
|----------|---------------|
| 1st      | TIMETOBELUCKY |
| 2nd      | HERCULES BOKO |
| 3rd      | CARGO DOOR    |


## Example 2

### Input

**Number of loops:** 3

**Start List:**

| Lane | Horse name     | Base speed |
|------|----------------|------------|
| 1    | TIMETOBELUCKY  | 10         |
| 2    | CARGO DOOR     | 10         |
| 3    | HERCULES BOKO  | 10         |
| 4    | WAIKIKI SILVIO | 10         |

**Power-Ups/Downs:**

| Loop | Lane 1 | Lane 2 | Lane 3 | Lane 4 |
|------|--------|--------|--------|--------|
| 1    | 0      | 0      | 1      | 3      |
| 2    | 10     | 0      | 0      | 1      |

### Result

| Position | Horse Name    |
|----------|---------------|
| 1st      | WAIKIKI SILVIO|
| 2nd      | TIMETOBELUCKY |
| 3rd      | HERCULES BOKO |


## Example 3

### Input

**Number of loops:** 3

**Start List:**

| Lane | Horse name     | Base speed |
|------|----------------|------------|
| 1    | TIMETOBELUCKY  | 10         |
| 2    | CARGO DOOR     | 10         |
| 3    | HERCULES BOKO  | 10         |
| 4    | WAIKIKI SILVIO | 10         |

**Power-Ups/Downs:**

| Loop | Lane 1 | Lane 2 | Lane 3 | Lane 4 |
|------|--------|--------|--------|--------|
| 1    | 6      | 10     | 4      | 0      |
| 2    | 0      | -10    | 5      | 15     |

### Result

| Position | Horse Name    |
|----------|---------------|
| 1st      | HERCULES BOKO |
| 2nd      | TIMETOBELUCKY |
| 3rd      | WAIKIKI SILVIO|

## Implementation
The assignment has to be implemented as a spring boot application.

The input is provided as an XML document (see examples ```/src/main/resources/input_0.xml```, ```/src/main/resources/input_1.xml``` and ```/src/main/resources/input_2.xml```),
in case you need it we provide the XML schema for it (```/src/main/resources/input.xsd```)

The output must be a json document of this form:
```json
{
   "ranking": [
      {"position": 1, "horse": "TIMETOBELUCKY"},
      {"position": 2, "horse": "HERCULES BOKO"},
      {"position": 3, "horse": "CARGO DOOR"}
   ]
}
```

## Code Review Comments in Progress 

The code produces the expected result in each given scenario. It has some unit tests for code coverage. But, there are few things which could be improved in this code.
      1. One of the main point was taking input XML as a string and then parsing it manually. Springboot provides means to convert to an Object.
      2. The code test was provided with an input.xsd file which includes some information about validations that could be performed on the requests. Right       now only validation is for null or empty for the input string.
      3. Also, input.xsd could be used to generate the POJO’s using a maven plugin instead of creating them manually.
      4. When a request is invalid then usually a bad request is sent back as a response, instead of client exception.
      5. Stream API’s since Java 8 are nicer and makes code more readable. Would suggest to use them instead of for loops.
      6. Once a request object HarryKart is available, it would be better to use that for calculation and store the outcome in a separate POJO instead of         creating another HarryKartVO. It would reduce lot of complexity in the code.
      7. Did not understand the use of MockitoAnnotations.openMocks in HarryKartServiceTest. I don’t think it is required and also it is a good practice to       close them when the tests are finished or use try-with-resources statement.
      HarryKartService is injected using Field injection which is not recommended.



