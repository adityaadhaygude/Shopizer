Feature: Shopizer product attributes-options management resources

#Pass-22 Fail - 2

Background:
	* url 'http://localhost:8080/'
	* def helper = Java.type('karatemaven.JavaFunctions')
	* def ID = 4

# Create product attribute
@postcall
Scenario: Create a product attribute with valid authentication
		Given path 'api/v1/private/product/'+ ID +'/attribute'
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		When request {"sortOrder":0,"attributeDefault":false,"attributeDisplayOnly":false,"productAttributeWeight":"0","productAttributePrice":"0","productAttributeUnformattedPrice":"0","option":{"id":362,"code":"75","type":"radio","readOnly":false,"order":0,"description":{"id":11,"language":"en","name":"sling","description":null,"friendlyUrl":null,"keyWords":null,"highlights":null,"metaDescription":null,"title":null}},"optionValue":{"id":369,"code":"90","name":null,"defaultValue":false,"sortOrder":0,"image":null,"order":0,"price":null,"description":{"id":3,"language":"en","name":"ograc","description":null,"friendlyUrl":null,"keyWords":null,"highlights":null,"metaDescription":null,"title":null}}}
		When method POST
		Then status 201
		Then print response
		* def pid = response.id
		* def setID = helper.setId(pid);
    
@postcall
Scenario: Create a product attribute with no access
    Given path 'api/v1/private/product/'+ ID +'/attribute'
    * header Authorization = call read('basic-auth.js') { username: 'ruthvikandi@gmail.com', password: '12345' }
		And header Content-Type = 'application/json'
		When request {"sortOrder":0,"attributeDefault":false,"attributeDisplayOnly":false,"productAttributeWeight":"0","productAttributePrice":null,"productAttributeUnformattedPrice":null,"option":{"id":316,"code":"30","type":"radio","readOnly":false,"order":0,"description":{"id":7,"language":"en","name":"beach","description":null,"friendlyUrl":null,"keyWords":null,"highlights":null,"metaDescription":null,"title":null}},"optionValue":{"id":1,"code":"30","name":null,"defaultValue":false,"sortOrder":0,"image":"http:\/\/localhost:8080\/static\/files\/DEFAULT\/PROPERTY\/Picture4.png","order":0,"price":null,"description":{"id":1,"language":"en","name":"vintage","description":null,"friendlyUrl":null,"keyWords":null,"highlights":null,"metaDescription":null,"title":null}}}
    When method POST
    Then status 401
    Then print response

@postcall			#NoChange
Scenario: Create a product attribute  with authorization but incorrect attribute path
    Given path 'api/v1/private/product/'+ ID +'/Attribute'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		When request {"sortOrder":0,"attributeDefault":false,"attributeDisplayOnly":false,"productAttributeWeight":"0","productAttributePrice":null,"productAttributeUnformattedPrice":null,"option":{"id":316,"code":"30","type":"radio","readOnly":false,"order":0,"description":{"id":7,"language":"en","name":"beach","description":null,"friendlyUrl":null,"keyWords":null,"highlights":null,"metaDescription":null,"title":null}},"optionValue":{"id":1,"code":"30","name":null,"defaultValue":false,"sortOrder":0,"image":"http:\/\/localhost:8080\/static\/files\/DEFAULT\/PROPERTY\/Picture4.png","order":0,"price":null,"description":{"id":1,"language":"en","name":"vintage","description":null,"friendlyUrl":null,"keyWords":null,"highlights":null,"metaDescription":null,"title":null}}}
    When method POST
    Then status 404
    Then print response

# Get Product Attribute
@getcall
Scenario: Get a product attribute with valid authentication
		* def PID = helper.getId();
		Given path 'api/v1/private/product/'+ ID +'/attribute/' + PID
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 200
    Then print response

@getcall
Scenario: Get a product attribute with limited access to admin controls
		* def PID = helper.getId();
		Given path 'api/v1/private/product/'+ ID +'/attribute/' + PID
    * header Authorization = call read('basic-auth.js') { username: 'test', password: 'test@123' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 403
    Then print response

@getcall			#NoChange
Scenario: Get a product attribute with authorization but incorrect attribute path
    Given path 'api/v1/private/product/'+ ID +'/attribute/253des'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 500
    Then print response
    
# Update the existing attribute
@putcall
Scenario: Update the product attributes with valid authentication
		* def PID = helper.getId();
		Given path 'api/v1/private/product/'+ ID +'/attribute/' + PID
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		When request {"sortOrder":0,"attributeDefault":false,"attributeDisplayOnly":false,"productAttributeWeight":"0","productAttributePrice":"0","productAttributeUnformattedPrice":"0","option":{"id":362,"code":"75","type":"checkbox","readOnly":false,"order":0,"description":{"id":11,"language":"en","name":"sling","description":null,"friendlyUrl":null,"keyWords":null,"highlights":null,"metaDescription":null,"title":null}},"optionValue":{"id":369,"code":"90","name":null,"defaultValue":false,"sortOrder":0,"image":null,"order":0,"price":null,"description":{"id":3,"language":"en","name":"ograc","description":null,"friendlyUrl":null,"keyWords":null,"highlights":null,"metaDescription":null,"title":null}}}
		When method PUT
		Then status 200
		Then print response

# Delete Attribute
@deletecall
Scenario: Delete the product attributes with valid authentication
		* def PID = helper.getId();
		Given path 'api/v1/private/product/'+ ID +'/attribute/' + PID
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		When method DELETE
    Then status 200
    Then print response

# Create product options
@postcall
Scenario: Create a Product Option with valid authentication
    Given path 'api/v1/private/product/option'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		When request { "code":"34" }
		When method POST
    Then status 201
    Then print response
    * def oid = response.id
    * def setID = helper.setId(oid);

# Get Product Option
@getcall
Scenario: Get a product option with valid authentication
		* def OID = helper.getId();
		Given path 'api/v1/private/product/option/' + OID
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 200
    Then print response
    
# Update product options
@putcall
Scenario: Update the product options with valid authentication
		* def OID = helper.getId();
		Given path 'api/v1/private/product/option/' + OID
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		When request { "code":"102" }
		When method PUT
    Then status 200
    Then print response
    
# Delete Product Option
@deletecall
Scenario: Delete the product options with valid authentication
		* def OID = helper.getId();
		Given path 'api/v1/private/product/option/' + OID
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		When method DELETE
    Then status 200
    Then print response
    
# Check unique options
@getcall
Scenario: Check unique product option with valid authentication
		Given path 'api/v1/private/product/option/unique'
		And param code = 2
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 200
    Then print response
		
# Create Option Values
@postcall
Scenario: Create a product Option Values with valid authentication
    Given path 'api/v1/private/product/option/value'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		When request { "code": "102" }
		When method POST
    Then status 201
    Then print response
		* def vid = response.id
    * def setID = helper.setId(vid);
    
# Get Option Value
@getcall
Scenario: Get product option value with valid authentication
		* def VID = helper.getId();
		Given path 'api/v1/private/product/option/value/' + VID
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 200
    Then print response

# Update product Option value
@putcall
Scenario: Update the product options value with valid authentication
		* def VID = helper.getId();
		Given path 'api/v1/private/product/option/value/' + VID
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		When request { "code": "21" }
		When method PUT
    Then status 200
    Then print response
    
# Delete product option value
@deletecall
Scenario: Delete the product option values with valid authentication
		* def VID = helper.getId();
		Given path 'api/v1/private/product/option/value/' + VID
		* header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		When method DELETE
    Then status 200
    Then print response
 
# Check if option value code already exists
@getcall
Scenario: Check product option value exist with valid authentication
		Given path 'api/v1/private/product/option/value/unique'
		And param code = 31
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 200
    Then print response

# Get Options
@getcall
Scenario: Check options with valid authentication
		Given path 'api/v1/private/product/options'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 200
    Then print response

@getcall
Scenario: Check options with authorization but incorrect options path
		Given path 'api/v1/private/product/optionS'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 405
    Then print response
    
#Get Option Values
@getcall
Scenario: Check option values with valid authentication
		Given path 'api/v1/private/product/options/values'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 200
    Then print response
    
@getcall  #Fail
Scenario: Check option values with authorization but incorrect option values path
		Given path 'api/v1/private/product/options//valueS1'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
	 	When method GET
    Then status 500
    Then print response