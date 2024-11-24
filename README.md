# Handling XML Databases with eXist-db and XQuery
This project demonstrates how to create XML documents in Java using the DOM API and how to execute queries against an eXist-db database
using the [XML:DB API](https://exist-db.org/exist/apps/doc/devguide_xmldb).

## Index
- [Description](#description)
- [Features](#features)
- [Requirements](#requirements)
- [Usage](#usage)

## Description
This project combines Java and XML database technologies to show:
- The creation and manipulation of XML documents using the DOM API.
- Querying XML data stored in an [eXist-db](https://exist-db.org/) database with XQuery.

The purpose is to provide a foundational understanding of working with XML databases in applications.

## Features
- Creating XML documents dynamically using Java's DOM API.
- Interacting with an eXist-db database for storing and retrieving XML data.
- Executing XQuery scripts to query and manipulate XML data.
- Simple and reusable code structure for learning and extending.

## Requirements
- **Java Development Kit (JDK)** 8 or higher.
- **eXist-db** installed and running locally or on a server.
- Any IDE with Java support (e.g., IntelliJ IDEA, Eclipse, NetBeans).
- Basic knowledge of XML, XQuery, and Java programming.

## Usage
1. Clone the repository and open it in your IDE: ```git clone https://github.com/Sinbelisk/Practica-AD-ExistDB.git```
2. Navigate to the ```examples``` package to explore the available demonstrations.
    - [CollectionCreationExample.java](https://github.com/Sinbelisk/Practica-AD-ExistDB/blob/main/src/main/java/examples/CollectionCreationExample.java); Examples for creating XML files with elements.
    - [CollectionCreationExample2.java](), Another example for creating a XML file.
    - [CollectionQueryExample.java](https://github.com/Sinbelisk/Practica-AD-ExistDB/blob/main/src/main/java/examples/CollectionQueryExample.java); Examples for querying from the DB
3. Run the provided examples to:
   - Create XML documents.
   - Execute XQuery scripts against an eXist-db instance.