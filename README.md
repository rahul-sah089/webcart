# Spring Boot WebCart API with Client React UI
 
This app has backend Basket Implementation using Spring Boot and Front end Client using React JS. The user can product to cart, delete from the cart and update it.

**Prerequisites:** [Java 8], [Spring Boot], [Maven Dependecy],[Node.js]

# Features
* It has Authentication and User Management APIs 
* Rest API to add, update and remove items from the Basket
* Rest API to get all the Current items in Basket for a user
* In memory H2 database
* Spring Security
* JWT based API authentation
* Web inerface using React
* Sign In/ Sign Up page
* UI section to show all the Product and option to Add or delete them from the cart
* UI section to see all the current items the cart
* Dependency Management using Maven build tool

## Getting Started

To install this example application, run the following commands:

```bash
https://github.com/rahul-sah089/webcart.git
cd webcart
```

This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow the instructions below.

To run the server, cd into the `server` folder and run:
 
```bash
./mvnw spring-boot:run
```

To run the client, cd into the `client` folder and run:
 
```bash
npm install && npm start
```
## License

Apache 2.0, see [LICENSE](LICENSE).
