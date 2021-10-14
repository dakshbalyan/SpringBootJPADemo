package com.personal.SpringBootJPADemo.Repositories;

import com.personal.SpringBootJPADemo.Entities.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReaderRepositoryTest {

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    public void printReader(){
        List<Reader> readers = readerRepository.findAll();

        readers.forEach(System.out::println);
    }
}