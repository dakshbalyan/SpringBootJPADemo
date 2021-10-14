package com.personal.SpringBootJPADemo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_author",
        uniqueConstraints = @UniqueConstraint(
                name = "authorName_unique",
                columnNames = "name"
        )
)
public class Author {
    @Id
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )    
    @Column(name = "id")
    private int authorID;

    @Column(
            name = "name",
            nullable = false
    )
    private String authorName;

    @Column(name = "address")
    private String authorAddress;

    @OneToMany(
            mappedBy = "author"
    )
    private List<Book> bookList;

    @Override
    public String toString() {
        return "Authors" +
                "{ authorID=" + authorID +
                ", authorName='" + authorName + '\'' +
                ", authorAddress='" + authorAddress + '\'' +
//                "\n BOOKS LIST :"+bookList+
                 "\n}" ;
    }
}
