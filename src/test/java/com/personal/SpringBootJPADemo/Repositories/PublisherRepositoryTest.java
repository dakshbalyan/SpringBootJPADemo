package com.personal.SpringBootJPADemo.Repositories;

import com.personal.SpringBootJPADemo.Entities.Book;
import com.personal.SpringBootJPADemo.Entities.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublisherRepositoryTest {
    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void savePublisher(){
        Publisher publisher = Publisher.builder()
                .publisherName("Penguin Random House India")
                .publisherAddress("Gurgaon")
                .build();

        publisherRepository.save(publisher);
    }

    @Test
    public void deletePublisher(){
//        Publisher publisher = Publisher.builder()
//                .publisherID(7)
//                .publisherName("Jaico Publishing House")
//                .publisherAddress("Mumbai")
//                .build();
//
//        publisherRepository.delete(publisher);
        publisherRepository.deleteByID(1);
    }

    @Test
    public void printAllPublishers(){
        List<Publisher> pubList = publisherRepository.findAll();

        pubList.forEach(pub -> System.out.println(pub));
    }

    @Test
    public void getAllBooks(){
        Publisher publisher = publisherRepository.findByPublisherName("Penguin Random House India");
        publisher.getBook().forEach(e -> System.out.println(e));

    }

}