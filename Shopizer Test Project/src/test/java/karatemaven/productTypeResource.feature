Feature: Product Type Resource

  Background: 
    * url 'http://localhost:8080/'

  #Create product type
  @postcall1
  Scenario: Create a product type with valid administrator account
    Given path 'api/v1/private/products/type'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And request { "id": 1,"code": "GENERAL","visible": false,"allowAddToCart": false,"description": null}
    When method POST
    Then status 200
    Then print response

  #Get product type
  @getcall1
  Scenario: Get list of product type with valid administrator account
    Given path 'api/v1/private/products/type/1'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    When method GET
    Then status 200
    Then print response

  #Update product type
  @putcall1
  Scenario: Update a product type with valid administrator account
    Given path 'api/v1/private/products/type/204'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And request { "code": "102"}
    When method PUT
    Then status 200
    Then print response

  #Delete product type
  @deletecall1
  Scenario: Delete a product type with valid administrator account
    Given path 'api/v1/private/products/type/204'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    When method DELETE
    Then status 200
    Then print response

  #Verify if product type is unique
  @getcall2
  Scenario: Verify if product type is unique with valid administrator account
    Given path 'api/v1/private/products/type/unique'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And param code = 10
    When method GET
    Then status 200
    And match response.exists == true
    Then print response

  #Get product  list
  @getcall3
  Scenario: Get product type list with valid administrator account
    Given path 'api/v1/private/products/types'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    When method GET
    Then status 200
    Then print response

	#Get product list with invalid username
  @getcall4
  Scenario: Get product type list with invalid administrator account
    Given path 'api/v1/private/products/types'
    * header Authorization = call read('basic-auth.js') { username: 'test@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    When method GET
    Then status 401
    Then print response

  #Update product type
  @putcall2
  Scenario: Update product type id with valid authentication but invalid type
    Given path 'api/v1/private/products/typE/56'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And request { "code": "102"}
    When method PUT
    Then status 404
    Then print response

# Get product list with invalid URL
  @getcall5
  Scenario:  Get product types list with  valid authentication but incorrect URL
    Given path 'api/v1/private/products/type/kk'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    When method GET
    Then status 500
    Then print response
