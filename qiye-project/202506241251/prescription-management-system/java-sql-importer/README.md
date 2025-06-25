# Java SQL Importer

This project is a simple Java application that imports SQL scripts into a database. It is designed to demonstrate how to connect to a database and execute SQL commands using Java.

## Project Structure

```
java-sql-importer
├── src
│   ├── Main.java
│   └── utils
│       └── DatabaseUtils.java
├── sql
│   └── schema.sql
├── pom.xml
└── README.md
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- Maven

## Setup Instructions

1. **Clone the repository:**
   ```
   git clone https://github.com/yourusername/java-sql-importer.git
   cd java-sql-importer
   ```

2. **Build the project:**
   Use Maven to build the project and download the necessary dependencies.
   ```
   mvn clean install
   ```

3. **Configure the Database:**
   Update the database connection settings in `src/utils/DatabaseUtils.java` to match your database configuration.

4. **Execute the Application:**
   Run the application using the following command:
   ```
   mvn exec:java -Dexec.mainClass="Main"
   ```

## Usage

The application will read the SQL script located in `sql/schema.sql` and execute it against the configured database. Ensure that the SQL script contains the necessary commands to create tables and insert initial data.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

 mvn dependency:tree | grep mybatis-plus


mvn dependency:copy-dependencies

rm -rf src/main/java/com/example/prescription/entity/*
rm -rf src/main/java/com/example/prescription/mapper/*
rm -rf src/main/resources/mapper/*

mvn mybatis-generator:generate


mvn exec:java -Dexec.mainClass="CodeGenerator"

mvn exec:java -Dexec.mainClass="Main"

mvn exec:java -Dexec.mainClass="JwtSecretMD5"