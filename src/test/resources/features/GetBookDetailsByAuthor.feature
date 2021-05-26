@GetBookByAuthorName
Feature: Get Book Details By Author Name

  @Smoke
  Scenario Outline: Verify user get a Book Details for author name
    Given User create a request for geting book details for sevice <service>
    And User pass valid Author Name for QueryParametr as <queryparamater>
    When User sends a request for getting book details to <endpoint>
    Then Verify book details in respponse

    Examples: 
      | service      | queryparamater | endpoint           |
      | Liabrary_API | AuthorName     | Get_Book_By_Author |

  @Regression
  Scenario Outline: Verify user don't get a Book Details for invalid author name
    Given User create a request for geting book details for sevice <service>
    And User pass Invalid Author Name for QueryParametr as <queryparamater>
    When User sends a request for getting book details to <endpoint>
    Then Verify author not found message in respponse

    Examples: 
      | service      | queryparamater | endpoint           |
      | Liabrary_API | AuthorName     | Get_Book_By_Author |
