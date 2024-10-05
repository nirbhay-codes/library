# Steps to run application and test APIs 

1. Make sure you have the following installed on your system:

  ~~~
  Java 17
  Maven
  ~~~

2. git clone https://github.com/nirbhay-codes/library.git
3. cd library
4. mvn clean package
5. Run app
  ~~~
  mvn spring-boot:run
  or
  java -jar target/library-0.0.1-SNAPSHOT.jar
  ~~~

5. Use postman or similar app to use below REST APIs:

**Author**
- add a new author: POST http://localhost:8080/api/authors
    ~~~
    BODY: { "name": "sample author", "bio": "this is a sample bio of the author." }
    ~~~
- get all authors: GET http://localhost:8080/api/authors
- get an author by id: GET http://localhost:8080/api/authors/{id}
- update an author: PUT http://localhost:8080/api/authors/{id}
  ~~~
  BODY: { "name": "updated author name", "bio": "this is an updated bio of the author." }
  ~~~
- delete an author: DELETE http://localhost:8080/api/authors/{id}

**Book** 
- add a New Book: POST http://localhost:8080/api/books
  ~~~
  Body:
  {
      "title": "Sample Book",
      "author": "Sample Author",
      "isbn": "ISBN00001",
      "publishedDate": "2024-01-01"
  }
  ~~~
- get all Books: GET http://localhost:8080/api/books
- get a Book by ID: GET http://localhost:8080/api/books/{id}
- update a Book: PUT http://localhost:8080/api/books/{id}
  ~~~
  Body:
  {
        "title": "Updated Book Title",
        "author": "Updated Author",
        "isbn": "ISBN00001",
        "publishedDate": "2024-02-01"
  }
  ~~~
- delete a Book: DELETE http://localhost:8080/api/books/{id}
