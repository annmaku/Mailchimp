Feature: Sign up a new user to Mailchimp

  Background:
    Given Open web browser and go to Mailchimp

  @signUp
  Scenario Outline: Sign up to Mailchimp
    Given I accept cookies
    And I enter an email address as "<email>"
    And I enter a new username as "<username>"
    And I also enter a new password as "<password>"
    When I press the sign up button
    Then Registration is successful/unsuccessful, as displayed with "<status>"
    Examples:
      | email                        | username      | password | status  |
      | carrot38403868@gmail.com     | carrot        | Asdf123# | success |
      | strawberry38403868@gmail.com | longUserName  | Asdf123# | fail    |
      | potato38403868@gmail.com     | potato        | Asdf123# | fail    |
      |                              | lemon38403868 | Asdf123# | fail    |

