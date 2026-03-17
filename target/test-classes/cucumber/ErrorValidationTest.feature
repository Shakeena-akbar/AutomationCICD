@tag
Feature: Error Validation

@Smoke
Scenario Outline: Validating the Error when giving invalid login credentials
Given  I  landed on e-commerce page
And Logged in with valid username <name> and password <pass>
Then "Incorrect email or password." error message is displayed

Examples:

| name					| pass				|
| syha@gmail.com		| unknown			|