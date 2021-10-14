package com.personal.SpringBootJPADemo.Repositories;

import com.personal.SpringBootJPADemo.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    public List<Book> findByBookName(String bookName);

    public List<Book> findByBookNameContaining(String name);

    public Book findByBookID(int id);

    public List<Book> findByBookRegistrationDateBetween(Date startDate, Date endDate);
    public List<Book> findByBookRegistrationDateAfter(Date startDate);
    public List<Book> findByBookRegistrationDateBefore(Date endDate);

    // JPQL
    /*@Query("select b from Book b where b.bookID = ?1")
    public Book getBookByID(int id);

    @Query("select b.bookName from Book b where b.bookID = ?1")
    public String getBookNameByID(int id);*/
    // JPQL Named Parameter
    @Query("select b from Book b where b.bookID = :id")
    public Book getBookByID(@Param("id") int id);

    @Query("select b.bookName from Book b where b.bookID = :bookID")
    public String getBookNameByID(@Param("bookID") int id);

    // Native Query
    /*@Query(
            value = "SELECT * FROM tbl_book b where b.id = ?1",
            nativeQuery = true
    )
    public Book getBookByIdNative(int id);

    @Query(
            value = "SELECT b.name FROM tbl_book b where b.id = ?1",
            nativeQuery = true
    )
    public String getBookNameByIdNative(int id);*/

    // Native Query Named Params
    @Query(
            value = "SELECT * FROM tbl_book b where b.id = :bookID",
            nativeQuery = true
    )
    public Book getBookByIdNative(@Param("bookID") int id);

    @Query(
            value = "SELECT b.name FROM tbl_book b where b.id = :bookID",
            nativeQuery = true
    )
    public String getBookNameByIdNative(@Param("bookID") int id);

    @Transactional
    @Modifying
    @Query(
            value = "update tbl_book set name = ?1 where id = ?2",
            nativeQuery = true
    )
    public int updateBookNameByIDNative(String bookName,int id);

    @Transactional
    @Modifying
    @Query(
            value = "update tbl_book b set b.publisher_id = ?1 where id = ?2",
            nativeQuery = true
    )
    public int updatePublisherIdByIDNative(int pubId,int bookId);
    @Transactional
    @Modifying
    @Query(
            value = "update tbl_book b set b.author_id = ?1 where id = ?2",
            nativeQuery = true
    )
    public int updateAuthorIdByIDNative(int authId,int bookId);


    List<Book> findByPublisher(int pubId);
}
