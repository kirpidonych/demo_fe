@Demo_with_issue
Feature: Demo with screenshot

  Scenario Outline: I get to the page and navigating back and forth, trying to submit empty form
    When I get to the resource
    Then I see Home Page
    And I can Select "Design & Build" item from the menu
    When I press "Connect To Engage"
    Then I fill out the form with "<name>", "<email>", "<company>", "<job_title>", "<message>"
    And I press "Get in touch"
    And I expect form to be submitted
    Examples:
      |name        | email        | company | job_title             | message       |
      |            |              |         |                       | Make me happy |
