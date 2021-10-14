package com.personal.SpringBootJPADemo.Repositories;

import com.personal.SpringBootJPADemo.Entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    public Author findByAuthorName(String authorName);

    Page<Author> findByAuthorNameContaining(String name,Pageable pageable);
}
