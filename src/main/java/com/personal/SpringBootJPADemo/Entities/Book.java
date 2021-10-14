package com.personal.SpringBootJPADemo.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_book",
        uniqueConstraints = @UniqueConstraint(
                name = "bookName_unique",
                columnNames = "name")
)
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(name = "id")
    private int bookID;

    @Column(
            name = "name",
            nullable = false)
    private String bookName;

    @Column(name = "registration_date")
    private Date bookRegistrationDate;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "author_id",
            referencedColumnName = "id"
    )
    @ToString.Exclude
    private Author author;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "publisher_id",      // name of the column in the current table
            referencedColumnName = "id" // should be the table column name
    )
    @ToString.Exclude
    private Publisher publisher;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(     // This will create a new table for many to many mapping
            name = "reader_book_map",
            joinColumns = @JoinColumn(  // This is the reference or joins where the reader is getting mapped to books
                    name = "book_id",
                    referencedColumnName = "id"   // from the current entity that reader would be mapped to
            ),
            inverseJoinColumns = @JoinColumn(   // This is the reference or joins where the books will be mapped to readers
                    name = "reader_id", // name of the column for readerID
                    referencedColumnName = "id"   // from the reader entity
            )
    )
    private List<Reader> readers;

    public void addReader(Reader reader){
        if(readers == null) readers = new ArrayList<>();
        readers.add(reader);

    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", bookRegistrationDate=" + bookRegistrationDate +
                ", author=" + author.getAuthorID() +
                ", publisher=" + publisher.getPublisherID() +
                '}';
    }
}
