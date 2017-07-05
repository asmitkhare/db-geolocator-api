# db-geolocator-api
===================================
This rest web service allows you to
- Add a shop in application memory array with its location
- Find the nearest shop if we supply the location as longitude and latitude

Platform:
===================================
Gradle 4.0
Java 8

Steps to Make a Build
===================================
Since it is gradle based java application use the following to package it in a jar.
gradle build
To build without running tests
gradle build -x test
On successful build the jar can be found at build/libs/ path.
Or One can Use Eclipse/ STS Plugin gor building and running

Steps to Run
===================================
Running the application is as easy as building and a one step process. Once you have the jar it can be run with the following command.

java -jar path/to/jar/db-geolocator-api-1.0.0.jar

During development the following command will be useful to run without jar.
gradle run
You will see the following message after a successful start up.
:: Spring Boot ::        (v1.4.0.RELEASE)

>>>> Application has started successfully at http://localhost:8080/api/


Steps to Test:
===================================

gradle test


Geocoding Google Server side API
===================================
This application uses Google's GeoCoding API to locate a shop and find longitude and latitude providing it to rest APIs to add a shop and get the nearest shop. Here are they -
User Can Use POSTMAN Client To Test the Service

POST: Service Request to Add Shop
===================================
This api adds a shop to the in-memory database.

URI		: 	/shop/addShop
REQUEST BODY	:
{
	"shopName": "Reliance Digital",
	"shopAddress": {
		"number": "Drishti Plaza",
		"postCode": "462042"
	}
}
HTTP METHOD	:POST
HTTP RESPONSE : 201 OK
RESPONSE BODY  :
{"runningStatus":true}
e.g - http://localhost:8080/api/shop/addShop


GET: Service request to Get Nearest Shop
===================================
To find the nearest shop to the location (latitude, longitude) passed in the request parameter customerLatitude, customerLongitude

URI            - /shop/getshop
REQUEST PARAMS - customerLatitude, customerLongitude
HTTP METHOD    - GET
HTTP RESPONSE  - 200 OK
RESPONSE BODY  -
{"shopName":"Jumbo","shopAddress":{"number":"Groenhof","postCode":"1186"},"shopLongitude":4.870502699999999,"shopLattitude":52.288631}
