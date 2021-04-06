Feature: Sign up a new user to Mailchimp

  Background:
    Given Open web browser and go to Mailchimp

  @signUp
  Scenario Outline: Sign up to Mailchimp
    Given I accept cookies
    Given I enter an email address as "<email>"
    And I enter a new username as "<username>"
    And I also enter a new password as "<password>"
    When I press the sign up button
    Then I am registered as a new user
    Examples:
      | email                     | username                                                                                                  | password       |
      | carrot13575@gmail.com     | carrot13575                                                                                               | 3GreenCarrots# |
      | strawberry13571@gmail.com | strawberry13571strawberry13571strawberry13571strawberry13571strawberry13571strawberry13571strawberry13571 | 3GreenCarrots# |
      | potato13579@gmail.com     | potato                                                                                                    | 3GreenCarrots# |
      |                           | lemon975346                                                                                               | 3GreenCarrots# |
