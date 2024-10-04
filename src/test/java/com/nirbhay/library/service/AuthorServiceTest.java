package com.nirbhay.library.service;

import com.nirbhay.library.entity.Author;
import com.nirbhay.library.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks // this will inject authorRepository mocks into AuthorService
    private AuthorService authorService;

    private Author author1;
    private Author author2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        author1 = new Author();
        author1.setId(1L);
        author1.setName("Author 1");
        author1.setBio("Author 1 Bio");

        author2 = new Author();
        author2.setId(2L);
        author2.setName("Author 2");
        author2.setBio("Author 2 Bio");
    }

    @Test
    void createAuthor_ShouldReturnSavedAuthor() {
        when(authorRepository.save(author1)).thenReturn(author1);

        Author savedAuthor = authorService.createAuthor(author1);
        assertNotNull(savedAuthor);

        assertEquals(author1.getId(), savedAuthor.getId());

        verify(authorRepository, times(1)).save(author1);
    }

    @Test
    void getAuthorById_ShouldReturnAuthor_WhenExists() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author1));

        Author retrievedAuthor = authorService.getAuthorById(1L);

        assertNotNull(retrievedAuthor);
        assertEquals(author1.getName(), retrievedAuthor.getName());

        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void getAuthorById_ShouldReturnNull_WhenNotExists() {
        when(authorRepository.findById(3L)).thenReturn(Optional.empty());
        Author retrievedAuthor = authorService.getAuthorById(3L);

        assertNull(retrievedAuthor);

        verify(authorRepository, times(1)).findById(3L);
    }

    @Test
    void getAllAuthors_ShouldReturnListOfAuthors() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author1, author2));
        List<Author> authors = authorService.getAllAuthors();

        assertEquals(2, authors.size());

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void updateAuthor_ShouldReturnUpdatedAuthor_WhenAuthorExists() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author1));
        when(authorRepository.save(author1)).thenReturn(author1);

        Author updatedAuthor = new Author();
        updatedAuthor.setName("Updated Author");
        updatedAuthor.setBio("Updated Bio");

        Author result = authorService.updateAuthor(1L, updatedAuthor);

        assertEquals("Updated Author", result.getName());

        assertEquals("Updated Bio", result.getBio());

        verify(authorRepository, times(1)).save(author1);
    }

    @Test
    void updateAuthor_ShouldReturnNull_WhenAuthorNotExists() {
        when(authorRepository.findById(3L)).thenReturn(Optional.empty());
        Author result = authorService.updateAuthor(3L, author1);

        assertNull(result);

        verify(authorRepository, never()).save(author1);
    }

    @Test
    void deleteAuthor_ShouldCallDeleteById() {
        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1)).deleteById(1L);
    }
}
