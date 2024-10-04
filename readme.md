# Steps to run application and test APIs 

1. Make sure you have the following installed on your system:

~~~
Java 17: Check by running java -version in the terminal.

Maven: Check by running mvn -version in the terminal.
~~~

2. git clone https://github.com/nirbhay-codes/library.git

3. cd library

4. mvn clean package

5. Run the application
~~~
mvn spring-boot:run
or
java -jar target/library-0.0.1-SNAPSHOT.jar
~~~

6. Use postman or similar app

~~~
// ADD
Get all Books:
Endpoint: GET http://localhost:8080/api/books

Get a Book by ID:
GET http://localhost:8080/api/books/{id}

Add a New Book:
Endpoint: POST http://localhost:8080/api/books

    Body:
    {
    "title": "Sample Book",
    "author": "Sample Author",
    "isbn": "ISBN00001",
    "publishedDate": "2023-01-01"
    }

// UPDATE
Update a Book:
Endpoint: PUT http://localhost:8080/api/books/{id}

    Body:
    {
      "title": "Updated Book Title",
      "author": "Updated Author",
      "isbn": "ISBN00001",
      "publishedDate": "2024-01-01"
    }

// DELETE
Endpoint: DELETE http://localhost:8080/api/books/{id}

~~~