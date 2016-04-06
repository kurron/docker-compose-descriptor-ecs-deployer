# Overview
This project can pull a [Docker Compose](https://docs.docker.com/compose/) descriptor and deploy it to
[Amazon EC2 Container Service](https://aws.amazon.com/ecs/).  It is intended to be used by automated
deployment pipelines.

# Prerequisites
* [JDK 8](http://www.oracle.com/technetwork/java/index.html) installed and working
* Building under [Ubuntu Linux](http://www.ubuntu.com/) is supported and recommended 

# Building
Type `./gradlew` to build and assemble the service.

# Installation
TODO

# Tips and Tricks

## Verifying The Setup
TODO

## Running Integration Tests From Gradle
TODO

## Running Acceptance Tests From Gradle
TODO

## Running Acceptance Tests From IDEA
TODO

## Operations Endpoints
The service supports a variety of endpoints useful to an Operations engineer.  The easiest way to explore
the resources is via the [HAL Browser](https://github.com/mikekelly/hal-browser).  Just point your browser
to `localhost:8080/operations/` and you'll be able to explore the different endpoints.  Endpoints include:

* /operations - Provides a hypermedia-based “discovery page” for the other endpoints.
* /operations/autoconfig - Displays an auto-configuration report showing all auto-configuration candidates and the reason why they ‘were’ or ‘were not’ applied.
* /operations/beans - Displays a complete list of all the Spring beans in your application.
* /operations/configprops - Displays a collated list of all `@ConfigurationProperties`.
* /operations/dump - Performs a thread dump.
* /operations/env - Exposes properties from Spring’s `ConfigurableEnvironment`.
* /operations/flyway - Shows any `Flyway` database migrations that have been applied.
* /operations/health - Shows application health information.
* /operations/info - Displays arbitrary application info.
* /operations/liquibase - Shows any `Liquibase` database migrations that have been applied.
* /operations/logfile - Returns the contents of the logfile (if logging.file or logging.path properties have been set).
* /operations/metrics - Shows ‘metrics’ information for the current application.
* /operations/mappings - Displays a collated list of all `@RequestMapping` paths.
* /operations/shutdown - Allows the application to be gracefully shutdown (not enabled by default).
* /operations/trace - Displays trace information (by default the last few HTTP requests).

## REST API
The easiest way to explore the available resources is via the [HAL Browser](https://github.com/mikekelly/hal-browser).
Just point your browser to `localhost:8080/` and you'll be able to explore the different endpoints.

## REST API Documentation
You can find the current API documentation at `/docs/index.hml`.

# Troubleshooting

TODO

# License and Credits
This project is licensed under the [Apache License Version 2.0, January 2004](http://www.apache.org/licenses/).

