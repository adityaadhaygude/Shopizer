Feature: Shopizer Manufacturer / Brand management resource 

Background:
    * url 'http://localhost:8080/'
    * def one = read('KarateTestCases.csv')
		* def two = read('CreateTestCases.csv')
		* def data = karate.append(one,two)
		
    
    

@getcall1    
Scenario: Get all manufacturers for all items in a given category with category id 1
    Given path 'api/v1/category/1/manufacturers/'
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }

    When method GET
    Then status 200
    Then print response
    
    
@deletecall1  
Scenario: Delete a manufacturer item from the list of manufacturers with id 160
Scenario Outline: Delete a user from given details.
    Given url 'http://localhost:8080/api/v1/manufacturer/'
    And path '<id>'
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
And request {"id":<id>}
    When method DELETE
    Then status 200
    Then print response
    
Examples:
| read('karateTestCases.csv') |
    
    
    

@getcall2       #failing with path
Scenario: Get the List of manufacturers by store 
    Given url 'http://localhost:8080/api/v1/manufacturers/'
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    
    When method GET
    Then status 200
    Then print response
    
@getcall3    
Scenario: Get the List of manufacturers by store but with the incorrect URL
    Given path 'api/v1/manufacturers/kk'
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }

    When method GET
    Then status 500
    Then print response
    
    
    
@getcall4    
Scenario: Get a single manufacturer ID 222 by store
    Given path 'api/v1/manufacturers/222'
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
			And header Content-Type = 'application/json'
    When method GET
    Then status 200
    Then print response
    
@postcall1      
Scenario: Create a new manufacturer item
Scenario Outline: create a user from given details.
    Given url  'http://localhost:8080/api/v1/private/manufacturer'
    
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
						And header Content-Type = 'application/json'
						And request {"code":<code>, "name":<name>}
		
    When method POST
    Then status 201
    Then print response
    
Examples:
| read('CreateTestCases.csv') |
    

@putcall1            
Scenario: Update list of the items

    Given url 'http://localhost:8080/api/v1/private/manufacturer/221'
    And path '<id>'
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
						And header Content-Type = 'application/json'
							And request {"code": "ct","name": "bags", "visible": true}
							
						
		
    When method PUT
    Then status 200
    Then print response

    


@getcall5      
Scenario: Check if manufacturer code already exists with valid administrator account
    Given path 'api/v1/private/manufacturer/unique'   
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    			And header Content-Type = 'application/json'
   				And param code = 'chic'
    
    When method GET
    Then status 200
    And match response.exists == true
    Then print response 
    
    
@getcall6     
Scenario: Check if  manufacturer code already exists without valid administrator account
    Given path 'api/v1/private/manufacturer/unique'   
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shoper.com', password: 'paword' }
    			And header Content-Type = 'application/json'
   				And param code = 'chic'
    
    When method GET
    Then status 401
    Then print response 
    
@getcall7           
Scenario: Check if manufacturer code already exists but with incorrect name
    Given path 'api/v1/private/manufacturrer/unique'   
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    			And header Content-Type = 'application/json'
   				And param code = 'chic'
    
    When method GET
    Then status 404
    Then print response 
    
@getcall8   #fail testcase
Scenario: Check if manufacturer code already exists with limited access to the admin control
    Given path 'api/v1/private/manufacturer/unique'   
    
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
    			And header Content-Type = 'application/json'
   				And param code = 'chic'
    
    When method GET
    Then status 403
    Then print response 
    

@getcall9                      #failing with path   
Scenario: Get a list of manufacturers by store with valid administrator account
    Given url 'http://localhost:8080/api/v1/private/manufacturers/'   
    
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    		And header Content-Type = 'application/json'
   				
    
    When method GET
    Then status 200
    Then print response 
    

    
    
    