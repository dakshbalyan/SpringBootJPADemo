package com.personal.SpringBootJPADemo.Repositories;

import com.personal.SpringBootJPADemo.Entities.Author;
import com.personal.SpringBootJPADemo.Entities.Book;
import com.personal.SpringBootJPADemo.Entities.Publisher;
import com.personal.SpringBootJPADemo.Entities.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReaderRepository readerRepository;

    @Test
    public void saveBook(){
        Date date = new Date(2006-1900,6,10);
        System.out.println(date);
        Author author = authorRepository.findByAuthorName("Stieg Larsson");
//        Publisher publisher = publisherRepository.findByPublisherName("Penguin Random House India");

//        Book book = Book.builder()
//                .bookName("The Girl Who Played with Fire")
//                .bookRegistrationDate(date)
//                .author(author)
//                .publisher(publisher)
//                .build();

//        bookRepository.save(book);
    }

    @Test
    public void printAllBooks(){
        List<Book> bookList = bookRepository.findAll();

        bookList.forEach(book -> System.out.println(book));
    }

    @Test
    public void printBookByName(){
        List<Book> bookList = bookRepository.findByBookName("I Feel Bad About My Neck");

        bookList.forEach(book -> System.out.println(book));
    }

    @Test
    public void printBooksContainingName(){
        List<Book> bookList = bookRepository.findByBookNameContaining("The");

        bookList.forEach(book -> System.out.println(book));
    }

    @Test
    public void printByID(){
        Book book = bookRepository.findByBookID(2);
        System.out.println(book);
    }

    @Test
    public void printBooksByDate(){
        Date startDate = new Date(1950-1900,1,1);
        Date endDate = new Date(1990-1900,1,1);
        System.out.println(startDate+" "+endDate);

//        List<Book> bookList = bookRepository.findByBookRegistrationDateBetween(
//               startDate,endDate
//        );

//        List<Book> bookList = bookRepository.findByBookRegistrationDateAfter(startDate);
        List<Book> bookList = bookRepository.findByBookRegistrationDateBefore(endDate);
        bookList.forEach(book -> System.out.println(book));
    }

    @Test
    public void printBooksByID(){
//        Book book = bookRepository.getBookByID(2);
//        String bookName = bookRepository.getBookNameByID(2);
//        Book book = bookRepository.getBookByIdNative(2);
        String bookName = bookRepository.getBookNameByIdNative(2);

        System.out.println(bookName);
    }

    @Test
    public void updateBookNameByID(){
        int status = bookRepository.updateBookNameByIDNative("Chronicles: Volume One",6);
        System.out.println(status);
    }

    @Test
    public void updatePubIdByBookId(){
        int status = bookRepository.updatePublisherIdByIDNative(3,6);
        System.out.println(status);
    }

    @Test
    public void updateAuthIdbyBookId(){
        System.out.println(bookRepository.updateAuthorIdByIDNative(6,5));
    }

    @Test
    public void saveBookWithReaders(){
        Reader reader = readerRepository.findByReaderName("Daksh");

        Book book = bookRepository.findByBookID(5);

        book.addReader(reader);

        bookRepository.save(book);
    }

    @Test
    public void findByPublisherName(){
        int pubId = publisherRepository.findByPublisherName("Westland Publications").getPublisherID();
        List<Book> bookList = bookRepository.findByPublisher(pubId);
        bookList.forEach(book -> System.out.println(book));
    }



}