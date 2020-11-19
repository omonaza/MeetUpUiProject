Feature: Basic Validation
  #verify page title equals to "Meetup - We are what we do"
  # page url

  #buttons - by Wednesday
  Scenario: Title verification
    Given the user is on the meetup homepage
    Then verify the title contains "Amazon"

    Scenario: Join Meetup button verififcation
      Given the user is on the meetup homepage
      Then verify join meetup button is displayed

      #implement sign up and log in