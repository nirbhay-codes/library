package com.nirbhay.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nirbhay.library.entity.Book;
import com.nirbhay.library.entity.Author;
import com.nirbhay.library.repository.BookRepository;
import com.nirbhay.library.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Author author;
    private Book book;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();

        author = new Author();
        author.setName("Author 1");
        author.setBio("This is a bio");
//        author.setId(1L);
        author = authorRepository.save(author);


        book = new Book();
        book.setTitle("Sample Title 1");
        book.setIsbn("ISBN001");
        book.setPublishedDate(LocalDate.of(2023, 1, 1));
        book.setAuthor(author);
        book = bookRepository.save(book);
    }

    @Test
    void testCreateBook() throws Exception {
//        Book newBook = new Book();
//        newBook.setTitle("Sample Title 2");
//        newBook.setIsbn("ISBN002");
//        newBook.setPublishedDate(LocalDate.of(2023, 2, 1));
//        newBook.setAuthor(author);

        String jsonRequestBody = "{"
                + "\"id\": null,"
                + "\"title\": \"Sample Title 2\","
                + "\"isbn\": \"ISBN002\","
                + "\"publishedDate\": \"2023-02-01\","
                + "\"author\": {"
                + "\"id\": " + author.getId() + ","
                + "\"name\": \"" + author.getName() + "\","
                + "\"bio\": \"" + author.getBio() + "\""
                + "}"
                + "}";

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newBook)))
                        .content(jsonRequestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Title 2"))
                .andExpect(jsonPath("$.isbn").value("ISBN002"));
    }

    @Test
    void testGetBookById() throws Exception {
        mockMvc.perform(get("/api/books/{id}", book.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Title 1"))
                .andExpect(jsonPath("$.isbn").value("ISBN001"));
    }

//    @Test
//    void testUpdateBook() throws Exception {
//        Book updatedBook = new Book();
//        updatedBook.setTitle("Updated Title");
//        updatedBook.setIsbn("Updated ISBN");
//        updatedBook.setPublishedDate(LocalDate.of(2023, 1, 15));
//        updatedBook.setAuthor(author);
//
//        mockMvc.perform(put("/api/books/{id}", book.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedBook)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Updated Title"))
//                .andExpect(jsonPath("$.isbn").value("Updated ISBN"));
//    }

    @Test
    void testUpdateBook() throws Exception {
        // Retrieve the existing book for the test
        Book existingBook = bookRepository.findById(book.getId()).orElse(null);

        // Assume the author has already been created and saved
        Author author = new Author();
        author.setId(1L); // Use an existing author ID
        author.setName("Updated Author");
        author.setBio("This is an updated author bio");

        // Build the JSON request body with author information included
        String jsonRequestBody = "{"
                + "\"id\": " + existingBook.getId() + ","
                + "\"title\": \"Updated Title\","
                + "\"isbn\": \"Updated ISBN\","
                + "\"publishedDate\": \"2023-01-15\","
                + "\"author\": {"
                + "\"id\": " + author.getId() + ","
                + "\"name\": \"" + author.getName() + "\","
                + "\"bio\": \"" + author.getBio() + "\""
                + "}"
                + "}";

        // Perform the PUT request
        mockMvc.perform(put("/api/books/{id}", existingBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.isbn").value("Updated ISBN"))
                .andExpect(jsonPath("$.author.name").value("Updated Author")); // Check if the author is included
    }



    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/books/{id}", book.getId()))
                .andExpect(status().isNoContent());
    }
}
