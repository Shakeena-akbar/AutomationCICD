@tag
Feature: Purchase the order from e-commerce website


Background:
Given I  landed on e-commerce page

@Regression
Scenario Outline: Positive test of submitting the order
Given Logged in with valid username <name> and password <pass>
When I add the product <productName> to the cart
And checkout <productName> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed.

Examples:

|name 					|	pass					| productName	|
|syha@gmail.com			|	Syhafatim26@			| ZARA COAT 3	|