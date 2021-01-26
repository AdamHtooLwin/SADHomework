# Homework 2

Sample application to setup a Spring Boot project following this [guide](https://spring.io/quickstart). This RESTful app serves a basic endpoint and supports a basic parameter.

## Prerequisites

1. JDK 11

Fresh Java installallation: `sudo apt install openjdk-11-jdk`

**Note**: Be sure to set the JAVA_HOME environment variable and add that to the path. If you have a prior installation, you can set the active Java using `update-java-alternatives` and manually update the JAVA_HOME variable. See [this](https://askubuntu.com/questions/121654/how-to-set-default-java-version).

2. IDE-based Spring Initalizr.

**Spring Version: 2.4.2**

The IDE used in this case is VSCode. The [Spring Initializr](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-spring-initializr) extension does the heavy lifting here. To generate a new project, open the Command Palette (`Ctrl+Shift+P`) and type Spring Initializr. See this [guide](https://code.visualstudio.com/docs/java/java-spring-boot).

**NOTE**: The extension requires the [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) extension as well. To see if VSCode recognizes your Java installation, bring up the Command Palette (`Ctrl+Shift+P`) and use the command **Java: Configure Java Runtime**. You can check the **Installed JDKs** here.

## Usage

1. Switch to root directory of project.

 `cd /path/to/demo`

2. Run the app from the root directory. **Note**: some downloads will occur on first time startup.

`./mvnw spring-boot:run`

3. Go to `http://localhost:8080/hello`. You should be greeted by a friendly **Hello World!** If the request is modified with a param to `http://localhost:8080/hello?name=Adam`, the text will change to **Hello Adam!**
