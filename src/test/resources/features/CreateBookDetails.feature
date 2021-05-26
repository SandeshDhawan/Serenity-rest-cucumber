@CreateBook
Feature: Create Book Details

  @Smoke
  Scenario Outline: Verify user is able to Create Book Details
    Given User create a request for creating book details
    When User sends a request for creating book details to <service> with <endpoint>
    Then Verify books details are created successfully

    Examples: 
      | service      | endpoint             |
      | Liabrary_API | Create_Book_Endpoint |

  @Regression
  Scenario Outline: Verify Success Message in response for created book details
    Given User create a request for creating book details
    When User sends a request for creating book details to <service> with <endpoint>
    Then Verify books details are created successfully
    And Verify Success Message in Response

    Examples: 
      | service      | endpoint             |
      | Liabrary_API | Create_Book_Endpoint |

  @Regression
  Scenario Outline: Verify Book already added message for existing book
    Given User create a request for existing book
    When User sends a request for creating book details to <service> with <endpoint>
    Then Verify already created book message in response

    Examples: 
      | service      | endpoint             |
      | Liabrary_API | Create_Book_Endpoint |
