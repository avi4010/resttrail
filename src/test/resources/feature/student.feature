Feature: Testing different request on the student application

#clean verify -Dcucumber.options="--tags @AVI" serenity:aggregate
#The above run command is not working

@AVI
Scenario: Check If student application can be accessed by users
When user sends a GET request to the list endpoint, they must get a valid status code 200


Scenario Outline: Create a new student & verify If it is added
When I create a new student with the following Information firstName<firstName> lastname<lastname> email<email> programme<programme> course<course>
Then I verify that the student with which email is created

Examples:
| firstName | lastname | email | programme | course |
| Avi | Seelam | avi92030@gmail.com | comp | java|
| Avi123 | Seelam0293 | avi92082920@gmail.com | comp | java|