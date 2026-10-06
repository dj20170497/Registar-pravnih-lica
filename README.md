# Registar pravnih lica

A Java client-server desktop application for managing legal entities and employees.

The project was developed as a university project and demonstrates the implementation of a client-server architecture, socket communication, database persistence, and common software design patterns.

## Features

The application provides functionality for:

- User login and logout
- Creating legal entities
- Viewing and searching legal entities
- Updating legal entity information
- Deleting legal entities
- Managing multiple addresses for a legal entity
- Selecting the registered office address
- Searching legal entities by location
- Creating and managing employees
- Managing locations
- Client-server communication

## Architecture

The application follows a client-server architecture and is divided into three projects:

```text
PROJEKAT_KLIJENT
PROJEKAT_SERVER
PROJEKAT_ZAJEDNICKI
```

### Client

The client application provides the graphical user interface built with Java Swing.

It communicates with the server using sockets and sends requests for operations such as creating, updating, deleting and retrieving data.

### Server

The server receives and processes client requests.

Business operations are implemented through system operation classes, while database communication is handled through a generic database broker.

### Shared

The shared project contains classes used by both the client and the server, including:

- Domain classes
- Client requests
- Server responses
- Operation definitions

## Technologies

- Java
- Java Swing
- MySQL
- JDBC
- TCP/IP Sockets
- NetBeans
- Git
- GitHub

## Design and Implementation

The project uses several software engineering concepts and design patterns:

- Client-Server Architecture
- MVC principles
- Singleton Pattern
- Template Method Pattern
- Generic Database Broker
- System Operations
- Domain Model

The `OpstaSistemskaOperacija` class defines the general execution flow for system operations, while concrete system operation classes implement specific application functionality.

Database access is handled through `DBBroker`, separating database logic from individual system operations.

## Domain Model

The main domain entity is `PravnoLice` (Legal Entity).

Legal entities can have multiple addresses, with one address representing the registered office.

Different types of legal entities are represented through specialization:

- DOO
- AD
- KomanditnoDrustvo
- OrtackoDrustvo

Other domain entities include:

- Zaposleni
- Mesto
- Adresa
- RegistracijaPravnihLica

## Project Structure

```text
Registar-pravnih-lica/
│
├── PROJEKAT_KLIJENT/
│   └── src/
│       ├── forme/
│       ├── komunikacija/
│       └── modeli/
│
├── PROJEKAT_SERVER/
│   └── src/
│       ├── baza/
│       ├── controller/
│       ├── forme/
│       ├── server/
│       └── so/
│
├── PROJEKAT_ZAJEDNICKI/
│   └── src/
│       ├── domeni/
│       ├── operacije/
│       └── transfer/
│
└── .gitignore
```

## Database

The application uses a MySQL database.

Database connection parameters are stored in a local `config.properties` file.

For security reasons, this file is excluded from the repository using `.gitignore`.

Example configuration:

```properties
url=jdbc:mysql://localhost:3306/projekat
username=YOUR_USERNAME
password=YOUR_PASSWORD
```

## Running the Application

1. Clone the repository.
2. Open the three projects in NetBeans.
3. Configure the MySQL database.
4. Create a `config.properties` file with your database credentials.
5. Start the server application.
6. Start the client application.
7. Log in through the client interface.

## Author

**Darko Jovančić**

Information Systems and Technologies  
Faculty of Organizational Sciences, University of Belgrade
