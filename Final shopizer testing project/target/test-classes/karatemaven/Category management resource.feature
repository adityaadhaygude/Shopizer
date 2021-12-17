Feature: Shopizer category management resources

Background:
		* url 'http://localhost:8080/'
		* def codeId = "backpack4"

@getcall1
#Get category
Scenario: Get all categories
    Given path 'api/v1/category'   
    And header Content-Type = 'application/json'
    When method GET
    Then status 200
    Then print response
    
@getcall2
Scenario: Get a category with id 50
    Given path 'api/v1/category/50'   
    And header Content-Type = 'application/json'
    When method GET
    Then status 200
    Then print response
 
#Check category code is already existing
@getcall3
Scenario: Check category code is already existing
    Given path 'api/v1/private/category/unique'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And param code = 'handbags'
    When method GET
    Then status 200
    And match response.exists == true
    Then print response  
    
#create a category   (Increment "code" in json)
@postcall1
Scenario: Create a new category with valid authentication
    Given path 'api/v1/private/category'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {"children": [],"code":"backpacks11" ,"depth": 0,"descriptions": [{"description": "","friendlyUrl": "backpacks","highlights": "","id": 704, "keyWords": "","language": "en","metaDescription": "","name": "Backpacks","title": "Vintage Bags - Backpacks"}],"featured": false,"lineage": "/5","parent": {"code": null},"sortOrder": 40,"visible": true}
    When method POST
    Then status 201
    Then print response
    
@postcall2
Scenario: Create a new category with invalid authentication
    Given path 'api/v1/private/category'
    * header Authorization = call read('basic-auth.js') { username: 'admin.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {"children": [],"code": "backpacks","depth": 0,"descriptions": [{"description": "","friendlyUrl": "backpacks","highlights": "","id": 704, "keyWords": "","language": "en","metaDescription": "","name": "Backpacks","title": "Vintage Bags - Backpacks"}],"featured": false,"id": 50,"lineage": "/50","parent": {"code": null},"sortOrder": 30,"visible": true}
    When method POST
    Then status 401
    Then print response
    
#update a category using put
@putcall1
Scenario: Update a category with valid authentication
    Given path 'api/v1/private/category/50'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {  "children": [],  "code": "backpackbags",  "depth": 0,  "descriptions": [    {      "description": "",      "friendlyUrl": "backpackbags",      "highlights": "",      "id": 703,      "keyWords": "",      "language": "en",      "metaDescription": "",      "name": "Backpack Bags",      "title": "Vintage Bags - Backpack Bags"    }  ],  "featured": false,  "id": 0,  "lineage": "/4",  "parent": {    "code": "string"  },  "sortOrder": 30,  "visible": true}
    When method PUT
    Then status 200
    Then print response
    
@putcall2
Scenario: Update a category with invalid authentication
    Given path 'api/v1/private/category/50'
    * header Authorization = call read('basic-auth.js') { username: 'admin.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {  "children": [],  "code": "backpackbags",  "depth": 0,  "descriptions": [    {      "description": "",      "friendlyUrl": "backpackbags",      "highlights": "",      "id": 703,      "keyWords": "",      "language": "en",      "metaDescription": "",      "name": "Backpack Bags",      "title": "Vintage Bags - Backpack Bags"    }  ],  "featured": false,  "id": 0,  "lineage": "/4",  "parent": {    "code": "string"  },  "sortOrder": 30,  "visible": true}
    When method PUT
    Then status 401
    Then print response
    
@putcall3
Scenario: Update a category with wrong path and valid authentication
    Given path 'api/v1/private/Category/50'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {  "children": [],  "code": "backpackbags",  "depth": 0,  "descriptions": [    {      "description": "",      "friendlyUrl": "backpackbags",      "highlights": "",      "id": 703,      "keyWords": "",      "language": "en",      "metaDescription": "",      "name": "Backpack Bags",      "title": "Vintage Bags - Backpack Bags"    }  ],  "featured": false,  "id": 0,  "lineage": "/4",  "parent": {    "code": "string"  },  "sortOrder": 30,  "visible": true}
    When method PUT
    Then status 404
    Then print response
    
@putcall4    
Scenario: Update a category with wrong Id and valid authentication
    Given path 'api/v1/private/category/7'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
		And request {  "children": [],  "code": "backpackbags",  "depth": 0,  "descriptions": [    {      "description": "",      "friendlyUrl": "backpackbags",      "highlights": "",      "id": 703,      "keyWords": "",      "language": "en",      "metaDescription": "",      "name": "Backpack Bags",      "title": "Vintage Bags - Backpack Bags"    }  ],  "featured": false,  "id": 0,  "lineage": "/4",  "parent": {    "code": "string"  },  "sortOrder": 30,  "visible": true}
    When method PUT
    Then status 404
    Then print response
    
#Update a category using PATCH
@patchcall1
Scenario: Update a category with id 50
    Given path 'api/v1/private/category/50/visible'   
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
    And header Content-Type = 'application/json'
    And request {"code": "bags"}
    When method PATCH
    Then status 200
    Then print response 

#Delete a category
Scenario: delete a category of given id with valid authentication
    Given path 'api/v1/private/category/107'
    * header Authorization = call read('basic-auth.js') { username: 'admin@shopizer.com', password: 'password' }
		And header Content-Type = 'application/json'
    When method DELETE
    Then status 200
    Then print response
    
#Delete a category
Scenario: delete a category of given id with invalid authentication
    Given path 'api/v1/private/category/105'
    * header Authorization = call read('basic-auth.js') { username: 'admin.com', password: 'password' }
		And header Content-Type = 'application/json'
    When method DELETE
    Then status 401
    Then print response
