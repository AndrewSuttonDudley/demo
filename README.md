# CRS Technical Task Solution

This repository contains the solution for the CRS technical task. The objective of this task was to develop a Spring Boot application with a simple architecture that integrates with CRS APIs (Equifax, Experian, or TransUnion credit profile products). The application should process backend requests asynchronously, implement logging functionality, a caching mechanisms, and utilize MySQL as the database for storing application data.

## Table of Contents
- [Solution Overview](#solution-overview)
- [Architecture](#architecture)
- [Logging](#logging)
- [Caching](#caching)
- [Database](#database)
- [API Endpoints](#api-endpoints)
- [Request Interceptor](#request-interceptor)
- [Deployment](#deployment)
- [Testing](#testing)

## Solution Overview

This solution repository contains the source code, documentation, and additional resources required to run and test the Spring Boot application. The solution (mostly) adheres to the specified requirements and implements the necessary functionalities such as CRS API integration, logging, caching, and database storage.

## Architecture

The CRS Engineer solution follows a layered architecture design. It leverages the Spring Boot framework to build a scalable and efficient application. The key components of the architecture include:

- **Controller**: Handles incoming API requests and delegates the processing to the appropriate services.
- **Service**: Implements the business logic and interacts with external CRS APIs and the database.
- **CRS API Connector**: Communicates with the CRS APIs to fetch credit profile data.
- **Cache**: Caching of responses within a specific time frame to improve performance.
- **AOP Request Interceptor**: Captures and logs various information related to requests, responses, and payloads.
- **Repository**: Provides an interface for accessing and persisting data in the MySQL database.
- **Database**: Stores application data, including request logs and cached responses.

## Logging

Aside from application logs to the console/log file, The logging solution of record that I chose was to intercept requests/responses/headers and store them in MongoDB. MongoDB allows for unstructured data to be stored into document collections so any request/response/header data can be easily stored without knowing the structure in advance. Then, those documents can be queried using the Criteria framework provided by Spring Data at any point in the future.

## Caching

An actual caching solution like EhCache or Spring's caching abstraction could have been used, but considering the purpose of the cache, I felt that, based on requirements, 24 hour persistence was preferable to the negligible performance gained by in in-memory cache on the app server. I'm looking forward to discussing this decision in the solution review.

## Database

The solution utilizes MySQL as the underlying databases for storing application data. However, for the credit response cache and request/response/header log solution, I chose MongoDB. It was a 'two birds with one stone' solution that worked well given time constraints.

## API Endpoints

There are three endpoints available in the solution:

POST /credit/requests
- This endpoint takes an EquifaxRequestDto json object in the response body and initiates an asyncronous request for equifax credit profile with the CRS API. The endpoint returns metadata related to the credit request immediately that can be used to poll/pull the credit profile data later.

GET /credit/requests/{id}
- This endpoint is dual purpose. Until the initial credit request is complete, this endpoint will return the same metadata as the POST request. However, when the credit pull is complete, the the status in the response will change to COMPLETED, and the response will contain the credit data.

GET /api-requests
- This endpoint returns a list of the resquest/response/header logs for all API requests. This endpoint was intended to accept extended search options, including vendor, request ID, request type, and start/end dates, but I ran out of time. Incidentally, I did implement a solution using Spring Data's CriteriaBuilder but found out that there is a different solution for building complex requests with MongoDB called simply, Criteria. I'm happy to review the intended and unfinished solution in the review meeting.

Unavailable:
GET /crs-requests
- This endpoint was not completed due to time constraints. It was partially implemented, and details exist in the request interceptor section.

## Request Interceptor

This was a tough one that took over half the time spent on the project. I knew all along that I could use AOP to grab request headers, the request body, and the response body from the controller methods, but I wanted to get the response headers as well. Also, I knew that there was a problem getting the request and response bodies in http filters because they're stored as streams and grabbing them will break the system's main functionality. I went on a wild goose chase across the interwebs chasing down fake solutions and wonder if the creators of this assignment knew that would happen. Anyway, I ended up with the imperfect AOP solution. Also, the CRS request/response was implemented, but I didn't have enough time to complete it in the end.

## Deployment

The solution is deployed in Digital Ocean, and I can demo this in the review meeting.

## Testing

Unit tests and integration tests (Using Testcontainers!!!) was implemented. However, because of Digital Ocean App Platform builds and time constraints, I had to comment them out. Overall, Testcontainers are super cool, and I look forward to discussing them with anyone that hasn't used them before.
