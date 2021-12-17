Feature: Shopizer Product Display and Management Resource

Background:
    * url 'http://localhost:8080/'



#Get a Product 
	
	Scenario: Get A product by valid Id and valid authentication
		Given path '/api/v1/products/10'
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
 		When method GET
 		Then status 200
 		Then print response
 	

 		Scenario: Get A product by invalid Id and valid authentication
		Given path 'api/v1/products/15'
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
 		When method GET
 		Then status 404
 		Then print response
 		

 		Scenario: Get A product by valid Id and without authorization
 		* header Authorization = call read('basic-auth.js') {}
		Given path 'api/v1/private/products/10'
		And header Content-Type = 'application/json'
 		When method GET
 		Then status 401
 		Then print response
 		

#Get Product By Friendly URL

Scenario: Get A product by valid Friendly URL
		Given path 'api/products/friendly/multi-use-hand-bag'
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
 		When method GET
 		Then status 200
 		Then print response
 		
#Add product to product category 

Scenario: Add product with Id 8 to Category with Id 1
		Given path 'api/v1/private/product/8/category/1'
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
 		When method POST
 		Then status 201
 		Then print response
 		
 		Scenario: Add product with Id 8 to Category with Id 1 with limited access to admin
		Given path 'api/v1/private/product/8/category/1'
		* header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
		And header Content-Type = 'application/json'
 		When method POST
 		Then status 403
 		Then print response
 	
 

#Delete product From product category 

Scenario: Delete product with Id 10 from Category with Id 1
		Given path 'api/v1/private/product/10/category/1'
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
 		When method DELETE
 		Then status 200
 		Then print response
 

#Update Product Inventory

	Scenario: Update quantity of product with product Id 10
		Given path 'api/v1/private/product/10'
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {"available": true,"price": "60.00","productShipeable": true,"quantity": 30}
 		When method PATCH
 		Then status 200
 		Then print response
	

 	
#Check Product Code Already Exists
 	
 	Scenario: Check Product code already exist
		Given path 'api/v1/private/product/unique'
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		 And param code = 'NK022'
		
 		When method GET
 		Then status 200
 		And match response.exists == true
 		Then print response
 		
 	
 		
#Get List Of all products

 		Scenario: Get List Of all products
		Given path 'api/v1/products'
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	
 		When method GET
 		Then status 200
 		Then print response
 		

 