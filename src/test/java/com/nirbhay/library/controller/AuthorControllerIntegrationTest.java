package com.nirbhay.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nirbhay.library.entity.Author;
import com.nirbhay.library.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Author author;

    @BeforeEach
    void setUp() {
        authorRepository.deleteAll();

        // Create and save an Author
        author = new Author();
        author.setName("Author 1");
        author.setBio("This is a bio");
        author = authorRepository.save(author);
    }

    @Test
    void testCreateAuthor() throws Exception {
        Author newAuthor = new Author();
        newAuthor.setName("Author 2");
        newAuthor.setBio("New bio");

        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAuthor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Author 2"))
                .andExpect(jsonPath("$.bio").value("New bio"));
    }

    @Test
    void testGetAuthorById() throws Exception {
        mockMvc.perform(get("/api/authors/{id}", author.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Author 1"))
                .andExpect(jsonPath("$.bio").value("This is a bio"));
    }

    @Test
    void testUpdateAuthor() throws Exception {
        Author updatedAuthor = new Author();
        updatedAuthor.setName("Updated Author");
        updatedAuthor.setBio("Updated bio");

        mockMvc.perform(put("/api/authors/{id}", author.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAuthor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Author"))
                .andExpect(jsonPath("$.bio").value("Updated bio"));
    }

    @Test
    void testDeleteAuthor() throws Exception {
        mockMvc.perform(delete("/api/authors/{id}", author.getId()))
                .andExpect(status().isNoContent());
    }
}
