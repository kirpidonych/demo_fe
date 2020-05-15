@Demo
Feature: Demo of basic web app testing solution with automation of bug filing in Bugzilla.

  Scenario Outline: I get to the page and navigating back and forth
    When I get to the resource
    Then I see Home Page
    And I can Select "Design & Build" item from the menu
    When I press "Connect To Engage"
    Then I fill out the form with "<name>", "<email>", "<company>", "<job_title>", "<message>"
    Examples:
      |name        | email        | company | job_title             | message       |
      | John Smith | js@gmail.com |   Me    |   Head of Everything  | Make me happy |

  Scenario Outline: I get to the page and navigating back and forth and apply to a job
    When I get to the resource
    Then I see Home Page
    And I can Select "Who We Are" item from the menu
    Then I apply to one of the positions

    Then I fill out the form with "<name>", "<email>", "<phone>", "<job_title>"
    Examples:
      |name        | email        | phone           | job_title             |
      | John Smith | js@gmail.com |   650-123-34-45 |   Head of Everything  |