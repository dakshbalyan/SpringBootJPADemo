package com.personal.SpringBootJPADemo.Repositories;

import com.personal.SpringBootJPADemo.Entities.Book;
import com.personal.SpringBootJPADemo.Entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    @Query(value = "delete from tbl_publisher p where p.id = :publisherID", nativeQuery = true)
    public void deleteByID(@Param("publisherID") int id);


    public Publisher findByPublisherName(String publisherName);

//    List<Book> findByPublisherName(String publisherName);
}
