@parallel=false
@CustMgmt
Feature: Shopizer Customer management resource

	Background:
	* url 'http://localhost:8080'									
	* def NonExistID = 1
	* def helper = Java.type('karatemaven.JavaFunctions')
	* def ID = helper.getId();
	* def customerPayload = read('Customer.json')
	

#Get list of Customers
@smoke
Scenario: TC_01:Get list of customers with valid authentication
    Given path '/api/v1/private/customers'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    When method GET
    Then status 200
    And print response
    And match response.customers == '#array'
    And assert responseTime < 1000

Scenario: TC_02:Get list of customers with invalid authentication
    Given path '/api/v1/private/customers'   
    * header Authorization = call read('basic-auth.js') { username: 'admin', password: 'password' }
    And header Content-Type = 'application/json'
    When method GET
    Then status 401
    Then print response
    And match response.error == 'Unauthorized'
    
Scenario: TC_03:Get list of customers with valid authentication but limited authorization
    Given path '/api/v1/private/customers'   
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
    And header Content-Type = 'application/json'
    When method GET
    Then status 403
    Then print response
    
#Create a Customer
@smoke
Scenario: TC_04:Create a customer with valid authentication
    Given path '/api/v1/private/customer'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And request customerPayload[0]
    When method POST
    Then status 200
    Then print response
    * helper.setId(response.id);
    
Scenario: TC_05:Create a customer with invalid authentication
    Given path '/api/v1/private/customer'   
    * header Authorization = call read('basic-auth.js') { username: 'admin', password: 'password' }
    And request customerPayload[0] 
    When method POST
    Then status 401
    Then print response
    And match response.error == 'Unauthorized'
    
Scenario: TC_06:Enter pre existing customer data with valid authentication
    Given path '/api/v1/private/customer'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And request customerPayload[0]
    When method POST
    Then status 400
    Then print response
    And match response.message == "User already exist"

Scenario: TC_07:Create a customer with valid authentication but limited authorization
    Given path '/api/v1/private/customer'   
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
    And request customerPayload[0] 
    When method POST
    Then status 403
    Then print response

#Get a Customer
@smoke
Scenario: TC_08:Get a customer using id with valid authentication
    Given path '/api/v1/private/customer/'+ID   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    When method GET
    Then status 200
    Then print response    
    
Scenario: TC_09:Get a non existing customer using id with valid authentication
    Given path '/api/v1/private/customer/'+NonExistID 
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    When method GET
    Then status 404
    Then print response
    
Scenario: TC_10:Get a customer using id with invalid authentication
    Given path '/api/v1/private/customer/'+ID   
    * header Authorization = call read('basic-auth.js') { username: 'admin', password: 'password' }
    When method GET
    Then status 401
    Then print response

Scenario: TC_11:Get a customer using id with valid authentication but limited authorization
    Given path '/api/v1/private/customer/'+ID   
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
    When method GET
    Then status 403
    Then print response

#Update a customer
@smoke
Scenario: TC_12:update a customer using id with valid authentication
    Given path '/api/v1/private/customer/'+ID   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And request customerPayload[1]
    When method PUT
    Then status 200
    Then print response

Scenario: TC_13:update a non existing customer using id with valid authentication
    Given path '/api/v1/private/customer/'+NonExistID   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And request customerPayload[1]
    When method PUT
    Then status 400
    Then print response
    
Scenario: TC_14:update a customer using id with invalid authentication
    Given path '/api/v1/private/customer/1302'   
    * header Authorization = call read('basic-auth.js') { username: 'admin', password: 'password' }
    And request customerPayload[1]
    When method PUT
    Then status 401
    Then print response

Scenario: TC_15:update a customer using id with valid authentication but limited authorization
    Given path '/api/v1/private/customer/1302'   
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
    And request customerPayload[1]
    When method PUT
    Then status 403
    Then print response

#Update a customers address 
@smoke
Scenario: TC_16:update a customers address using id with valid authentication
    Given path '/api/v1/private/customer/'+ID+'/address'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And request customerPayload[2]
    When method PATCH
    Then status 200
    Then print response

Scenario: TC_17:update a customers address using non existing id with valid authentication
    Given path '/api/v1/private/customer/'+NonExistID+'/address'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And request customerPayload[2]
    When method PATCH
    Then status 400
    Then print response
    
Scenario: TC_18:update a customers address with invalid authentication
    Given path '/api/v1/private/customer/'+ID+'/address'  
    * header Authorization = call read('basic-auth.js') { username: 'admin', password: 'password' }
    And request customerPayload[2]
    When method PATCH
    Then status 401
    Then print response

Scenario: TC_19:update a customers address with valid authentication but limited authorization
    Given path '/api/v1/private/customer/'+ID+'/address'  
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
    And request customerPayload[2]
    When method DELETE
    Then status 403
    Then print response
    
#Delete a Customer
@smoke
Scenario: TC_20:Delete a customer using id with valid authentication
    Given path '/api/v1/private/customer/'+ID   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    When method DELETE
    Then status 200
    Then print response
    
Scenario: TC_21:Delete a non existing customer using id with valid authentication
    Given path '/api/v1/private/customer/'+NonExistID   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    When method DELETE
    Then status 404
    Then print response
    
Scenario: TC_22:Delete a customer using id with invalid authentication
    Given path '/api/v1/private/customer/'+ID    
    * header Authorization = call read('basic-auth.js') { username: 'admin', password: 'password' }
    When method DELETE
    Then status 401
    Then print response

Scenario: TC_23:Delete a customer using id with valid authentication but limited authorization
    Given path '/api/v1/private/customer/'+ID    
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
    When method DELETE
    Then status 403
    Then print response

#Get authorized user
Scenario: TC_24:Get authorised user with valid authentication
    Given path '/api/v1/auth/customer/profile'   
    * header Authorization = call read('basic-auth.js') { username: 'goresky@aol.com', password: 'Abcd@127' }
    When method GET
    Then status 200
    Then print response
    
Scenario: TC_25:Get authorised user with invalid authentication
    Given path '/api/v1/auth/customer/profile'   
    * header Authorization = call read('basic-auth.js') { username: 'admin', password: 'password' }
    When method GET
    Then status 401
    Then print response
    
#Update a logged in customer
Scenario: TC_26:Update a logged in customer with valid authentication
    Given url 'http://localhost:8080/api/v1/auth/customer/'   
    * header Authorization = call read('basic-auth.js') { username: 'goresky@aol.com', password: 'Abcd@127' }
    And header Content-Type = 'application/json'
    And request customerPayload[3] 
    When method PATCH
    Then status 200
    Then print response
    
Scenario: TC_27:Update a logged in customer with invalid authentication
    Given path '/api/v1/auth/customer/'   
    * header Authorization = call read('basic-auth.js') { username: 'admin', password: 'password' }
    And request customerPayload[3]
    When method PATCH
    Then status 401
    Then print response  

Scenario: TC_28:Update a logged in customer with valid authentication but limited authorization
    Given path '/api/v1/auth/customer/'   
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
    And request customerPayload[3]
    When method PATCH
    Then status 403
    Then print response
    
#Update a logged in customers address
Scenario: TC_29:Update a logged in customers address with valid authentication
    Given path '/api/v1/auth/customer/address'   
    * header Authorization = call read('basic-auth.js') { username: 'goresky@aol.com', password: 'Abcd@127' }
    And request customerPayload[2]
    When method PATCH
    Then status 200
    Then print response
    
Scenario: TC_30:Update a logged in customers address with invalid authentication
    Given path '/api/v1/auth/customer/address'   
    * header Authorization = call read('basic-auth.js') { username: 'admin', password: 'password' }
    And request customerPayload[2]
    When method PATCH
    Then status 401
    Then print response  

Scenario: TC_31:Update a logged in customers address with valid authentication but limited authorization
    Given path '/api/v1/auth/customer/address'   
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
    And header Content-Type = 'application/json'
    And request customerPayload[2]
    When method PATCH
    Then status 403
    Then print response
    
#Delete a logged in customer
@cleanup
Scenario Outline: TC_32:Delete a logged in customer with valid authentication
    Given url 'http://localhost:8080/api/v1/auth/customer/'   
    * header Authorization = call read('basic-auth.js') { username: <user>, password: <pass> }
    And header Content-Type = 'application/json'
    When method DELETE
    Then status 200
    Then print response
    Examples:
    |read('login.csv')|
    
Scenario: TC_33:Delete a logged in customer with invalid authentication
    Given path '/api/v1/auth/customer/'   
    * header Authorization = call read('basic-auth.js') { username: 'admin', password: 'password' }
    And header Content-Type = 'application/json'
    When method DELETE
    Then status 401
    Then print response 