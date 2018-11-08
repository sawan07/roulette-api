# roulette-api
## Technologies
Language used for developmment: **Kotlin**

Project testing technology: **JUnit 5**

Load tesing technology: **JMeter**

## Endpoints

This Spring Boot application is built as an API endpoint for roulette applicaiton. There are 4 endpoints in total. They are as following:

`/roulette/setVersion`

**Functionality**: Used for setting a roulette version inside the session

**Parameters**: version(String): _US_VERSION or EU_VERSION_

**Returns**: Current value for saved version in session

`/roulette/getVersion`

**Functionality**: Used for getting the current roulette version saved in session. If no saved value found then, **EU_VERSION** will be set as default

**Returns**: Current value for saved version in session

`/roulette/outsideBet`

**Functionality**: Used for running a outside bet

**Parameters**: 

amount (Double) : _e.g. 100.00_, 

outsideBets(String): type of outside bet. Any of the following: 

* FIRST_DOZEN
* SECOND_DOZEN
* THIRD_DOZEN
* LOW
* HIGH
* EVEN
* ODD
* BLACK
* RED
* FIRST_COLUMN
* SECOND_COLUMN
* THIRD_COLUMN

**Returns**: A Winning object: e.g: ```{
                                      "winner": true,
                                      "winningAmount": 0
                                    }```
 
 `/roulette/insideBet`
 
 **Functionality**: Used for running a inside bet
 
 **Parameters**: 
 
 amount (Double) : _e.g. 100.00_, 
 
 insideBets(String): type of inside bet. Any of the following: 
 * SINGLE
 * SPLIT
 * STREET
 * SQUARE
 * DOUBLE_STREET
 * TRIO
 * BASKET
                                                                                                                 	
numbers(Array of String): [5,8]                                                                                                                 	
 
 **Returns**: A Winning object: e.g: ```{
                                       "winner": true,
                                       "winningAmount": 0
                                     }```
 
>The bets for current version are considered to be running in single mood. If multiple bates are being made, the front end can call the endpoints multiple time. But in that case the winning amount will have to be calculated in the front end, which should not be desired. This is a scope of improvement. 

## Linux commands and explanations
#### Running the application
```gradle bootRun```

This command will run the application on _http://localhost:8181_

#### Running the test
```gradle test```

This command will run the JUnit test and a xml test log will be generated inside _build/test-results/test/_ directory

#### Running the Load Testing
```gradle jmRun```

This command will run the JMeter load test. The load test runs an inside bet and an outside bet through the system. A JMeter log gets generated in _build/jmeter-report/_ directory

There are two jmx files available inside _src/test/jmeter/_ directory. The preset JMeter files have a longer assertion time set. If the load testing is done from the JMeter source application, it takes less time than doing that through command line. To test using short assertion time, the value needs to be changed inside the gradle build file. SImply change the following line:```jmTestFiles = [file("src/test/jmeter/roulette_test_plan.jmx")]``` to ```jmTestFiles = [file("src/test/jmeter/roulette_test_plan_short_time.jmx")]``` 

 
                                     
 