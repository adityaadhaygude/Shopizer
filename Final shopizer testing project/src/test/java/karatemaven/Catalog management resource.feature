@parallel=false
Feature: Shopizer catalog management resources

Background:
    * url 'http://localhost:8080/'
    * def helper = Java.type('karatemaven.JavaFunctions')
    * def ID = helper.getId2();

#create a catelog
@smoke
Scenario: Create a new catalog with valid authentication
    Given path 'api/v1/private/catalog'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {"code": "Leno bags 1010","defaultCatalog": true,"visible": true}
    When method POST
    Then status 200
    * helper.setId2(response.id);
    Then print response

Scenario: Create a new catalog with valid authorization and already existed data
    Given path 'api/v1/private/catalog'      
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And request {"id": 14, "visible": false,"defaultCatalog": false,"code": "desktop desk 14"}
    When method POST
    Then status 400
    Then print response

Scenario: Create a new catalog with invalid authentication
    Given path 'api/v1/private/catalog'      
    * header Authorization = call read('basic-auth.js') { username: 'admin.com', password: 'password' }
    And header Content-Type = 'application/json'
    And request {"code": "desktop desk-2010"}
    When method POST
    Then status 401
    Then print response
  
Scenario: Create a catalog with limited access authentication
    Given path 'api/v1/private/catalog'    
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
    And header Content-Type = 'application/json'
    And request{"code": "desktop 1010"}
    When method POST
    Then status 403
    Then print response  
  
Scenario: Create a catalog with valid authentication but invalid end point
    Given path 'api/v1/private/cataloG'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And request {"code": "desktop desk-3010"}
    When method POST
    Then status 404
    Then print response 
    
Scenario: Create a new catalog with valid authentication but invalid url
    Given path 'api/v1/private/catalog/kk'      
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And request {"code": "desktop desk-6010"}
    When method POST
    Then status 500
    Then print response     
  
#Update a catalog
@smoke
Scenario: Update a catalog with id
    Given path 'api/v1/private/catalog/'+ID   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And request {"code": "desktop desk"}
    When method PATCH
    Then status 200
    Then print response 
      
#Get a catelog
@smoke
Scenario: Get a catalog using id with valid authentication
    Given path 'api/v1/private/catalog/'+ID   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    When method GET
    Then status 200
    Then print response
    
#Add catalog entry to catalog
Scenario: Add a catalog entry to catalog
    Given path 'api/v1/private/catalog/2'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {"catalog": "rolex555","categoryCode": "rolex123","id": 123,"productCode": "rolout123","visible": true}
    When method POST
    Then status 200
    Then print response
 
   
#Delete a catalog
@smoke
Scenario: delete a catalog of given id
    Given path 'api/v1/private/catalog/'+ID
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
    When method DELETE
    Then status 200
    Then print response
     

#Get catalog entry by catalog
Scenario: Get catalog entry by catalog
    Given path 'api/v1/private/catalog/2/entry'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    When method GET
    Then status 200
    And match response.totalPages != 0
    Then print response  
    
#Remove catalog entry from the catalog
Scenario: Remove catalog entry from the catalog
    Given path 'api/v1/private/catalog/1/entry/14'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
    When method DELETE
    Then status 200
    Then print response
    
#Check catalog code already exist
Scenario: Check catalog code already exist
    Given path 'api/v1/private/catalog/unique'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And param code = 'stringisking'
    When method GET
    Then status 200
    And match response.exists == true
    Then print response  

#Get catalog by merchant
Scenario: Check catalog code already exist
    Given path 'api/v1/private/catalogs'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    When method GET
    Then status 200
    And match response.totalPages == 1
    Then print response  