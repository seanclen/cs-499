# CS-499: Core Management Services Prototype

## Project Overview

This project, originally developed for CS 320: Software Testing, Automation, and QA, serves as a proof-of-concept for foundational business logic required in a management application. It implements three core, highly-tested services: Contact Management, Task Management, and Appointment Scheduling.

The primary original focus was on validating internal logic through rigorous black-box testing using JUnit 5. Testing utilized techniques such as equivalence partitioning and boundary value analysis to ensure logic integrity around object property requirements (e.g., ID length, date validation).

## Current Architectural Deficiency

As an academic prototype, the current structure is a monolithic, in-memory Java application. Data is non-persistent (stored in ArrayLists), all business logic is tightly coupled, and the project lacks a formal build configuration (e.g., Maven or Gradle) to ensure reliable compilation and deployment outside of an IDE environment. The project is currently composed of raw source files that are not configured to run or compile easily

## CS 499 Enhancement Roadmap: Distributed Architecture

The CS 499 enhancement roadmap transforms this project into a distributed system orchestrated by Docker Compose, ensuring enterprise standards for deployment, performance, and security.

1. Software Design & Engineering
    * Maturity and Operationalization: The enhancement will incorporate Maven or Gradle for formal build management and Dockerize the application into a reusable service image.
    * Decoupling: The architecture will transition to a distributed model, with initial steps taken to decouple the monolithic application into distinct services, starting with the ContactService as a RESTful microservice.
2. Algorithms and Data Structures
    * Temporal Optimization: The Appointment Service will be refactored to replace its linear search array with a Self-Balancing Binary Search Tree (TreeMap). This specialized data structure reduces the time complexity of critical temporal range queries (e.g., checking for scheduling conflicts) from O(n) to O(log n).
3. Databases and Persistence
    * Polyglot Persistence via Docker Compose: A robust NoSQL database service (MongoDB) will be integrated into the Docker Compose environment to provide persistence for all application services.
    * Security & Distributed ID Generation: The sequential idCounter will be replaced with secure, collision-resistant UUIDs to ensure unique IDs in the distributed environment and mitigate ID enumeration vulnerabilities.

## Quick Start

To run the enhanced distributed system:

1. Clone the repository and navigate to the project directory
2. Start all services:
    ```bash
    docker compose up
    ```
3. Access the application at [http://localhost:8080](http://localhost:8080)

The Docker Compose orchestration will automatically start all required services including the application server and MongoDB database.

## Hot-Reload Development

The development environment includes hot-reloading capabilities to accelerate the development workflow. When changes are made to Java source files, the application is automatically recompiled and redeployed without requiring container restarts.

This is achieved through:
* Volume mounting of source directories
* Spring Boot DevTools integration for automatic restart detection
* Maven/Gradle watch tasks that monitor file changes
* Dockerized build processes that preserve running container state

Simply modify your Java files and the changes will be reflected in the running application within seconds, maintaining database connections and service state.