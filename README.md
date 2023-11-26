# buslines

This Spring Boot 3-based project utilizes the "SL Hållplatser och Linjer 2"-api to display the top 10 bus lines by stop count on a simple web page.

## Prerequisites

* Java 21
* Maven 3 
* SL Api key
  * Can be fetched from https://www.trafiklab.se/ for "SL Hållplatser och Linjer 2"

## RUN (on mac os)

1. Set your SL Api key

```
> export SL_API_KEY=<your-fetched-key> 
```
2. Run the project:

```
> mvn spring-boot:run
```

3. Open http://localhost:8080/ in your browser

## Run tests

``` 
> mvn test
```
