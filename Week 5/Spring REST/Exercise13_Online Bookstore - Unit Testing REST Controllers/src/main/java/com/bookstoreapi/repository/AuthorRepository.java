package com.example.bookstore.repository;

import com.example.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByLastName(String lastName);

    List<Author> findByFirstName(String firstName);

    List<Author> findByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findByBooks_Title(String title);
}
