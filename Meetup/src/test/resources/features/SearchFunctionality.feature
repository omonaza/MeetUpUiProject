Feature: Search Functionality

  Scenario: Verify search results - title
    Given the user is on the meetup homepage
    When the user types "conference" in the search field
    And the user hits Search button
    Then verify all search results contain "conference" in the title


    #implement a scenario
  Scenario: Verify search results - date
    Given the user is on the meetup homepage
    When the user types "conference" in the search field
    And the user hits Search button
    And the user selects tomorrow from a dates dropdown
    Then verify all search results contain tomorrows date in the date fields