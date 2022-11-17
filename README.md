# Knack Authenticator for Spring Boot

This is a library that extends Spring Boot functionality and introduces an Authenticator that integrates with Knack API.

## Adding the library to your build
To add the library as a dependency using Maven, use the following:

```xml
<dependency>
    <groupId>com.agileactors</groupId>
    <artifactId>agile-actors-spring-boot-knack-authenticator</artifactId>
    <version>1.0.0-xxx</version>
</dependency>
```

To add it as dependency using Gradle:

```gradle
dependencies {
  api("com.agileactors:agile-actors-spring-boot-knack-authenticator:1.0.0+")
}
```

For more information on when to use `api` and when to use `implementation`,
consult the
[Gradle documentation on API and implementation separation](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_separation).

## Configuration Properties

After adding the library to your dependencies, edit either `application.properties` or `application.yaml` and add the 
following properties

`application.properties`<br>
``` properties
application.auth.knack.url=http://path_to_knack_api_auth_url
application.auth.knack.whitelistedProfiles=comma separated list of knack profiles that access will be allowed
```

or

`application.yaml`<br>
``` yaml
application:
 auth:
  knack:
   url: http://path_to_knack_api_auth_url
   whitelistedProfiles: comma separated list of knack profiles that access will be allowed
```
