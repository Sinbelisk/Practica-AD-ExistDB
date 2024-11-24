# XML File creation and Querying in eXist-db with Java and XQuery
This project demonstrates how to create XML documents in Java using the DOM API and how to execute queries against an eXist-db database using the [XML:DB API](https://exist-db.org/exist/apps/doc/devguide_xmldb).

## Index
- [Description](#description)
- [Features](#features)
- [Requirements](#requirements)
- [Usage](#usage)
- [Project Structure](#project-structure)

## Description
This project combines Java and XML database to show:
- **Creation and manipulation** of XML documents using the DOM API.
- **Querying XML data** stored in an [eXist-db](https://exist-db.org/) database with XQuery.


  Key components of the project include:
- **DAO Pattern**: Separates the data access logic from the business logic, improving maintainability and scalability. This pattern ensures a clean and consistent way to interact with the database, abstracting the complexity of data operations.
- **ODM(Object-Document-Mapping)**: Maps XML documents to Java objects for seamless manipulation and querying. This allows developers to work with Java objects directly while storing and retrieving data in XML format.

## Features
- **Create XML documents** dynamically using Java's DOM API. You can generate XML data representing complex structures like books, poems, or essays.
- **Interact with an eXist-db database** for retrieving XML data. eXist-db is a native XML database that supports XML data manipulation and querying.
- **Execute XQuery scripts**to query XML data stored in eXist-db. XQuery provides a powerful way to query and manipulate XML data.
- A simple and reusable code structure for learning and extending, making it easy to adapt this project for various types of XML data and use cases.
  
- **Note:** Ensure that eXist-db is correctly configured and running on your system before executing any queries. You may need to configure the eXist-db server or adjust connection settings in the ExistDatabaseConnection.java file.

## Requirements
- **Java Development Kit (JDK)** 8 or higher.
- **eXist-db** installed and running.
- Any IDE with Java support.
- Basic knowledge of XML, XQuery, and Java programming.

## Usage
1. Clone the repository and open it in your IDE: `git clone https://github.com/Sinbelisk/Practica-AD-ExistDB.git`
2. Navigate to the `examples` package to explore the available demonstrations.
    - [CollectionCreationExample.java](https://github.com/Sinbelisk/Practica-AD-ExistDB/blob/main/src/main/java/examples/CollectionCreationExample.java); Examples for creating XML files with elements using the model classes. This demonstrates how to generate XML data for books, poems.
    - [CollectionCreationExample2.java](https://github.com/Sinbelisk/Practica-AD-ExistDB/blob/main/src/main/java/examples/CollectionCreationExample2.java), Another example for creating a XML file.
    - [CollectionQueryExample.java](https://github.com/Sinbelisk/Practica-AD-ExistDB/blob/main/src/main/java/examples/CollectionQueryExample.java); Demonstrates how to execute queries against the eXist-db database to retrieve XML database.

**⚠️ Note:**
Ensure that the folder containing the project does not include special characters `(e.g., á, ñ, %, $)` in its name. This may cause issues when creating files.

## Project Structure

The project is divided into the following main components and packages:

### **`library` Package**
This package contains the core logic and models for managing XML data and interacting with the database:
- **`model`**: Defines the data models used in the library.
   - **`Book.java`**, **`Poem.java`**, **`Essay.java`**: Represent different types of documents (books, poems, essays) with their respective properties. Each class follows the structure of the data being modeled and can be used to create XML documents with the same structure.
   - **`XMLCollection.java`**: Encapsulates a collection of model classes (eg: Book) for creating XML documents.
   - **`DatabaseModel.java`**: An empty interface, which defines a Data Object, necessary for mapping the query results into data objects. It is a placeholder for any class that should be used for data mapping.
- **`service`**: Provides services for database connectivity.
   - **`ExistDatabaseConnection.java`**: Handles the connection setup and management with the eXist-db database. This singleton class ensures that the Java application can interact with the eXist-db server.
- **`Dao`**: Implements the data access layer for interacting with XML documents stored in the database.
   - **`LibraryDao.java`**: Defines the interface for database query operations. This interface abstracts the database interaction and can be extended to add additional query methods.
   - **`LibraryDaoIMP.java`**: Implements the methods declared in `LibraryDao`. It contains the logic for interacting with the database, such as executing queries and returning results.
   - **`QueryManager.java`**: Manages XQuery statements and their execution. The class maps the query results into `DatabaseModel` object instances. Note that inherited classes are not supported in this implementation, ensuring that only direct classes are used for data mapping.
   
### **`util` Package**
Contains utility classes to support the project:
- **`XMLFileCreator.java`**: This utility class can convert a list of data objects into XML format using generics and reflection, making it easy to store data in XML documents.

### **`examples` Package**
This package contains example classes demonstrating how to use the project:
- **`CollectionCreationExample.java`**: A basic example of creating XML documents programmatically.  It shows how to generate XML data using the model classes and save it to a file
- **`CollectionCreationExample2.java`**: Another XML file creation example.
- **`CollectionQueryExample.java`**: Demonstrates how to execute XQuery statements against the eXist-db database to retrieve XML data. This example is useful for understanding how to query the database and work with the results in Java.
