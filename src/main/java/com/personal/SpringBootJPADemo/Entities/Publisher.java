package com.personal.SpringBootJPADemo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_publisher",
        uniqueConstraints = @UniqueConstraint(
                name = "publisherName_unique",
                columnNames = "name"
        ))
public class Publisher {
    @Id
    @SequenceGenerator(
            name = "publisher_sequence",
            sequenceName = "publisher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publisher_sequence"
    )
    @Column(name = "id")
    private int publisherID;

    @Column(
            name = "name",
            nullable = false
    )
    private String publisherName;

    @Column(name = "address")
    private String publisherAddress;

    @OneToMany(
            mappedBy = "publisher"
    )
    private List<Book> book;

}
