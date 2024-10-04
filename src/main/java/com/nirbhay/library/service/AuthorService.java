package com.nirbhay.library.service;

import com.nirbhay.library.entity.Author;
import com.nirbhay.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = getAuthorById(id);
        if (author != null) {
            author.setName(authorDetails.getName());
            author.setBio(authorDetails.getBio());
            return authorRepository.save(author);
        }
        return null;
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
