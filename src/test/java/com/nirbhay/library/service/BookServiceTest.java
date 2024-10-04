package com.nirbhay.library.service;

import com.nirbhay.library.entity.Book;
import com.nirbhay.library.repository.BookRepository;
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

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks // this will inject bookRepository mocks into BookService
    private BookService bookService;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks

        book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Sample Book 1");
        book1.setIsbn("ISBN0001");

        book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Sample Book 2");
        book2.setIsbn("ISBN0002");
    }

    @Test
    void createBook_ShouldReturnSavedBook() {
        when(bookRepository.save(book1)).thenReturn(book1);
        Book savedBook = bookService.createBook(book1);
        assertNotNull(savedBook);
        assertEquals(book1.getId(), savedBook.getId());
        verify(bookRepository, times(1)).save(book1);

    }

    @Test
    void getBookById_ShouldReturnBook_WhenExists() {

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));

        // Test
        Book retrievedBook = bookService.getBookById(1L);
        assertNotNull(retrievedBook);

        assertEquals(book1.getTitle(), retrievedBook.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBookById_ShouldReturnNull_WhenNotExists() {
        // Below does not exist
        when(bookRepository.findById(3L)).thenReturn(Optional.empty());

        Book retrievedBook = bookService.getBookById(3L);
        assertNull(retrievedBook);

        verify(bookRepository, times(1)).findById(3L);
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();

        assertEquals(2, books.size());

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void updateBook_ShouldReturnUpdatedBook_WhenBookExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.save(book1)).thenReturn(book1);

        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Book");
        updatedBook.setIsbn("ISBN0010");

        Book result = bookService.updateBook(1L, updatedBook);
        assertEquals("Updated Book", result.getTitle());
        assertEquals("ISBN0010", result.getIsbn());

        verify(bookRepository, times(1)).save(book1);
    }

    @Test
    void updateBook_ShouldReturnNull_WhenBookNotExists() {
        // THis does not exist
        when(bookRepository.findById(3L)).thenReturn(Optional.empty());

        Book result = bookService.updateBook(3L, book1);
        assertNull(result);

        // This is never called as it does not find the book
        verify(bookRepository, never()).save(book1);
    }

    @Test
    void deleteBook_ShouldCallDeleteById() {
        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
