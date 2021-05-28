# Getting started with REST API testing with Serenity and Cucumber 6

This tutorial show you how to get started with REST-API testing using Serenity and Cucumber 6. 

## Get the code

Git:

    git clone https://github.com/SandeshDhawan/Serenity-rest-cucumber


### The project directory structure
The project has build scripts for both Maven and follows the standard directory structure used in most Serenity projects:
```Gherkin
src
  + main
  + test
    + java                                               Test runners and supporting code
        + com.api.petstore.application.test.pojo         Pojo classes for request
        + com.api.petstore.application.test.runner       Runner class for running Test Cases based on tag
        + com.api.petstore.application.test.steps        Created Steps for passing request and validating response
        + com.api.petstore.application.test.util         Common Method
    + resources
      + features                          Feature files 
        + CreateBookDetails.feature
        + DeleteBook.feature
      + config.properties                         Passed endpoint and path parameter                

```


## Living documentation

You can generate full Serenity reports by running `mvn clean verify`. 
For running Smoke Test Cases:- `mvn clean verify -Dcucumber.filter.tags=@Smoke`.
For running Regression Test Cases:- `mvn clean verify -Dcucumber.filter.tags=@Regression`.
Report will be generated in directory:- Serenity-rest-cucumber\target\site\serenity\index.html


