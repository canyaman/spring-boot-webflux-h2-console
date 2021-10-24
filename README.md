# Spring Boot WebFlux H2 Console

This library is for spring-boot application which is based on webflux. It provides features of [Spring MVC h2-console](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.sql.h2-web-console) for reactive spring boot app.

H2 console is not only used for h2 database server browsing and database management. It can be used for all rdbms databases based on JDBC connection.
H2 console application is a servlet. If the application not using servlet engine like webflux application, h2-console can not be used. 
Because of that restriction this autoconfiguration uses h2-console as an additional server.  
H2 console is based on JDBC drivers so the driver has to in the runtime classpath. 
While h2-console is running as a separate serve, the spring boot application can use any r2dbc drivers, not only h2 but also any other r2dbc database drivers without running a servlet engine.

## Add webflux-h2-console dependency
Add "me.yaman.can.spring-boot:webflux-h2-console" dependency
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-webflux'
    implementation 'me.yaman.can:spring-boot-webflux-h2-console:0.0.1-SNAPSHOT'
    
    // Other dependencies ...
    
    // JDBC driver of the database
    runtimeOnly 'org.postgresql:postgresql'
}
```

## Activate h2-console and configure 
Enable autoconfiguration or import *me.yaman.can.webflux.h2console.H2ConsoleAutoConfiguration* auto configuration.
```yaml
spring:
  h2.console:
    # Required for h2-console server
    enabled: true
    # Optional variable /h2-console forward from the path to session created h2 console server uri
    path: /h2-console
    # Optional h2 console port if not set or set 0, available port is given
    port: 8082
```
## Define datasource and JDBC driver (Optional)
If datasource is defined, web console can be redirected to logged in h2 console session. 
```yaml
spring:
  datasource:
    # Even if only r2dbc connection can be used, jdbc url should to be given
    url: 'jdbc:h2:mem:temp'
    driverClassName: org.h2.Driver
    username: 'sa'
    password: ''
```
## Configure actuator endpoint
If actuator endpoint is configured, path and port don't need to be configured. Urls can be accessed form actuator endpoints.

```yaml
spring:
  h2.console:
    enabled: true
# Actuator Service
management:
  server.port: 9090
  endpoints:
    web:
      exposure.include: info, health, h2console
```

*http://localhost:9090/actuator/h2console* is for h2-console server info
*http://localhost:9090/actuator/h2console/redirect* is redirect to logged in h2-console web session



