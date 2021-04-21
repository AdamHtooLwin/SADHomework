# Homework 7: Spring Session

This homework demonstrates the utilization of Spring's built-in session functionality to keep track of user sessions and attributes.

By default, Spring stores session information in the memory but we can config it to store in the database in the `application.properties` by adding this line.

`spring.session.store-type=jdbc`

## Testing

Spring can store custom attributes in the session as well. In this case our attribute is called `MY_MESSAGES` and is an array of strings. We can add strings to the attribute in the form below.

![data](docs/hw7-add-message.png)

![data](docs/hw7-add-message-h2.png)

As can be seen above, an attribute called `MY_MESSAGES` is created as a row in the `SPRING_SESSION_ATTRIBUTES` table. The actual array is not visible, only the bytes.
