@GetBookByID
Feature: Get Book Details By ID

  @Smoke
  Scenario Outline: Verify user get a Book Details for a valid ID
    Given User create a request for geting book details for sevice <service>
    And User pass value as <value> for QueryParametr as <queryparamater>
    When User sends a request for getting book details to <endpoint>
    Then Verify book details in respponse

    Examples: 
      | service      | value       | queryparamater | endpoint |
      | Liabrary_API | Test_123123 | ID             | Get_Book |

  @Regression
  Scenario Outline: Verify user don't get a Book Details for invalid author name
    Given User create a request for geting book details for sevice <service>
    And User pass value as <value> for QueryParametr as <queryparamater>
    When User sends a request for getting book details to <endpoint>
    Then Verify author not found message in respponse

    Examples: 
      | service      | value           | queryparamater | endpoint |
      | Liabrary_API | Test_invalid_id | ID             | Get_Book |
