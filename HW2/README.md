# Homework 2

Sample application to setup a Spring Boot project following this [guide](https://spring.io/quickstart). This app serves a basic endpoint and supports a basic parameter.

## Prerequisites

1. JDK 11

2. IDE-based Spring Initalizr

## Usage

1. Switch to root directory of project.

 `cd /path/to/demo`

2. Run the app from the root directory. **Note**: some downloads will occur on first time startup.

`./mvnw spring-boot:run`

3. Go to `http://localhost:8080/hello`. You should be greeted by a friendly **Hello World!**. If the request is modified with a param to `http://localhost:8080/hello?name=Adam`, the text will change to **Hello Adam!**.
