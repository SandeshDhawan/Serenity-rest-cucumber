@DeleteBook
Feature: Delete Book Record

  @Smoke
  Scenario Outline: Verify user is able to deletea book record successfully
    Given User create a request for deleting book record
    When User sends a request for deleting book details to <service> with <endpoint>
    Then Verify message book not found for invalid ID

    Examples: 
      | service      | endpoint    |
      | Liabrary_API | Delete_Book |
