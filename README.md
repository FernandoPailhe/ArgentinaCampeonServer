# ArgentinaCampeonServer

Backend service built using MongoDB as the database and a three-layered architecture, which serves the 'Argentina Campeon del Mundo' Android application.

https://play.google.com/store/apps/details?id=com.ferpa.argentinacampeon

### Database
The database used in this project is MongoDB, which has six tables.

### Dependency Injection
The backend service uses dependency injection to manage dependencies between the layers.

## Architecture
The backend service follows a three-layered architecture consisting of:

`#0969DA` Routes Layer: This layer handles incoming requests and maps them to the appropriate controller function.
`#0969DA` Controller Layer: This layer implements the business logic for the API. It uses an ORM layer to interact with the database.
`#0969DA` ORM Layer: This layer contains an interface class and an implementation class for each table. It handles the interaction with the database.
Each table in the database has its own set of routes, controller, and ORM classes.

Implementation
One of the implementations has functions that create a mapped list by pulling data from several tables.

### Endpoints
There are several endpoints in the backend service, including endpoints for both regular users and admin users. 
The admin endpoints require a verification key for authentication.

As this backend service was developed in parallel with the Android application and evolved over time, there is a need to reduce the number of 
endpoints by implementing query parameters. However, this needs to be coordinated with all the users of the Android application once they have 
updated to the latest version.
