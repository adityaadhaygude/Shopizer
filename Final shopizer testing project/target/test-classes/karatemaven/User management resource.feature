Feature: Shopizer User management resources
@getcall1
    
#Create a user
Scenario: Create a user
    Given url 'http://localhost:8080/api/v1/private/user/'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {"active": true,"defaultLanguage": "en","emailAddress": "Group13343@gmail.com","firstName": "Group453","groups": [{"name": "ADMIN","type": "DEFAULT"}],"id": 1866,"lastName": "teams","password": "Tea@123","repeatPassword": "Tea@123","store": "DEFAULT","userName": "Group12366"}
		
    When method POST
    Then status 200
    Then print response
    

#Delete a user
Scenario: Remove a user
    Given url '/api/v1/private/user/200'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		
    When method DELETE
    Then status 200
    Then print response


#Get Authuser list
Scenario: Get Authuser list with valid authentication
    Given url 'http://localhost:8080/api/v1/private/user/profile'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		
    When method GET
    Then status 200
    Then print response
    
    

#Get Specific user 
Scenario: Get user list with valid authentication
    Given url 'http://localhost:8080/api/v1/private/users/150'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		
    When method GET
    Then status 200
    Then print response

Scenario: Get a user with id 150765764
    Given url 'http://localhost:8080/api/v1/private/users/150765764'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		
    When method GET
    Then status 404
    Then print response

Scenario: Get user with no authentication
    Given url 'http://localhost:8080/api/v1/private/users/150'
    * header Authorization = call read('basic-auth.js') { username: 'admin123@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		
    When method GET
    Then status 401
    Then print response
    
    

#Get user list
Scenario: Get user list with valid authentication
    Given url 'http://localhost:8080/api/v1/private/users'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		
    When method GET
    Then status 200
    Then print response
    
Scenario: Get user list with invalid authentication
		Given url 'http://localhost:8080/api/v1/private/users'
    * header Authorization = call read('basic-auth.js') { username: 'admin123@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		
    When method GET
    Then status 401
    Then print response
    
    
#Update user
Scenario: Update a user
    Given url 'http://localhost:8080/api/v1/private/user/200'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {"active": true,"defaultLanguage": "en","emailAddress": "jimin6745@gmail.com","firstName": "Jimin","groups": [{"name": "ADMIN","type": "DEFAULT"}],"id": 151,"lastName": "park","password": "Jimin123@","repeatPassword": "Jimin123@","store": "DEFAULT","userName": "Jimin123656"}
		
    When method PUT
    Then status 200
    Then print response
    
    
#Update user password
Scenario: Update a user
    Given url 'http://localhost:8080/api/v1/private/user/258/password'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {"changePassword": "Rajaram@123","password": "Sunil@123"}
		
    When method PATCH
    Then status 200
    Then print response

