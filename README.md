# Spring Boot WebCart API with Client React UI
 
This app has backend Basket Implementation using Spring Boot and Front end Client using React JS

**Prerequisites:** [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Node.js](https://nodejs.org/).

# Features
* It has Authentication and User Management APIs 
* API to add, update and remove items from the Basket
* API to get all the Current items in Basket for a user
* JWT based API authentation
* Sign In/ Sign Up page
* UI section to show all the Product and option to Add or delete them from the cart
* UI section to see all the current items the cart

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/oktadeveloper/spring-boot-react-example.git
cd spring-boot-react-example
```

This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow the instructions below.

To run the server, cd into the `server` folder and run:
 
```bash
./mvnw spring-boot:run
```

To run the client, cd into the `client` folder and run:
 
```bash
yarn && yarn start
```

The primary example (without authentication) is in the `master` branch, while the Okta integration is in the `okta` branch. To check out the Okta branch on your local machine, run the following command.

```bash
git checkout okta
```

### Create an Application in Okta

You will need to [create an OpenID Connect Application in Okta](https://developer.okta.com/blog/2017/12/06/bootiful-development-with-spring-boot-and-react#create-an-oidc-app-in-okta) to get your values to perform authentication. 

Log in to your Okta Developer account (or [sign up](https://developer.okta.com/signup/) if you don’t have an account) and navigate to **Applications** > **Add Application**. Click **Single-Page App**, click **Next**, and give the app a name you’ll remember. Change all instances of `localhost:8080` to `localhost:3000` and click **Done**.

#### Server Configuration

Set the `issuer` and copy the `clientId` into `server/src/main/resources/application.properties`. 

**NOTE:** The value of `{yourOktaDomain}` should be something like `dev-123456.oktapreview.com`. Make sure you don't include `-admin` in the value!

```properties
okta.oauth2.issuer=https://{yourOktaDomain}/oauth2/default
okta.oauth2.clientId={clientId}
```

#### Client Configuration

Set the `issuer` and copy the `clientId` into `client/src/App.tsx`.

```typescript
const config = {
  issuer: 'https://{yourOktaDomain}/oauth2/default',
  redirectUri: window.location.origin + '/implicit/callback',
  clientId: '{clientId}'
};
```

## Links

This example uses the following libraries provided by Okta:

* [Okta Spring Boot Starter](https://github.com/okta/okta-spring-boot)
* [Okta React SDK](https://github.com/okta/okta-oidc-js/tree/master/packages/okta-react)

## Help

Please post any questions as comments on the [blog post](https://developer.okta.com/blog/2017/12/06/bootiful-development-with-spring-boot-and-react), or visit our [Okta Developer Forums](https://devforum.okta.com/). You can also email developers@okta.com if you would like to create a support ticket.

## License

Apache 2.0, see [LICENSE](LICENSE).
