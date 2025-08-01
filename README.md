﻿About the Project
 
This project is a simple, robust Payment Processing System built using modern Java technologies. It demonstrates how to efficiently handle payment transactions with reliability and scalability, leveraging:

Spring Boot for building a clean, modular backend REST API.

MySQL as a reliable relational database to store payment data securely.

RabbitMQ as a messaging broker to enable asynchronous processing and improve system performance.


Prerequisites for Testing
Make sure these are running:

How to Verify

Spring Boot	http://localhost:8080/ should respond
MySQL	Database paymentdb is created & running
RabbitMQ	UI: http://localhost:15672
login: guest/guest

Example Test Cases (Use Postman )

1. Create a Payment (Send to RabbitMQ Queue)
   POST /api/payments

POST http://localhost:8080/api/payments
Content-Type: application/json

{
"amount": 199.99,
"currency": "USD",
"payer": "john@example.com"
}

RabbitMQ queue receives the message

Payment gets processed and saved to MySQL

Get All Payments

GET http://localhost:8080/api/payments


json


[
{
"id": 1,
"amount": 199.99,
"currency": "USD",
"payer": "john@example.com"
},
...
]
Get a Payment by ID

GET http://localhost:8080/api/payments/1

Delete a Payment

E
DELETE http://localhost:8080/api/payments/1

Payment is deleted
