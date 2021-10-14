package com.personal.SpringBootJPADemo.Repositories;

import com.personal.SpringBootJPADemo.Entities.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void saveAuthor(){
        Author author = Author.builder()
                .authorName("Hanya Yanagihara")
                .authorAddress("Hawaii")
                .build();

        authorRepository.save(author);
    }

    @Test
    public void printAllAuthor(){
        List<Author> authorList = authorRepository.findAll();

        authorList.forEach(author -> System.out.println(author));
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 2);
        Pageable secondPageWithThreeRecords = PageRequest.of(1, 3);

        List<Author> authorList = authorRepository.findAll(secondPageWithThreeRecords).getContent();
        long totalElements = authorRepository.findAll(secondPageWithThreeRecords).getTotalElements();
        long totalPages = authorRepository.findAll(secondPageWithThreeRecords).getTotalPages();

        System.out.println("Authors ==> "+authorList);
        System.out.println("Total Elements ==> "+totalElements);
        System.out.println("Total Pages ==> "+totalPages);
    }

    @Test
    public void findAllWithSorting(){
        Pageable sortByAuthorName = PageRequest.of(
                0,4, Sort.by("authorName")  // This will have the column name defined in the entity , i.e, the entity's attribute name corresponding to the column in the database
        );
        Pageable sortByAuthorAddressDescending = PageRequest.of(0,5,
                Sort.by("authorAddress").descending());

        Pageable sortByAuthorNameAndAddressDescending = PageRequest.of(
                0,6,Sort.by("authorName").ascending().and(Sort.by("authorAddress"))
        );

        List<Author> authorList1 = authorRepository.findAll(sortByAuthorName).getContent();
        List<Author> authorList2 = authorRepository.findAll(sortByAuthorAddressDescending).getContent();
        List<Author> authorList3 = authorRepository.findAll(sortByAuthorNameAndAddressDescending).getContent();

        System.out.println("Authors ==> "+authorList2);

    }

    @Test
    public void findByAuthorNameContaining(){
        Pageable pageWithSixRecords = PageRequest.of(
                0, 6
        );

        List<Author> authorList = authorRepository.findByAuthorNameContaining("A",
                pageWithSixRecords).getContent();

        System.out.println("Authors ==> "+authorList);
    }
}