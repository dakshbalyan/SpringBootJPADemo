package com.personal.SpringBootJPADemo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reader")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reader {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "reader_sequence",
            sequenceName = "reader_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reader_sequence"
    )
    private int readerID;


    @Column(name = "name", nullable = false, unique = true)
    private String readerName;

    @Column(name = "address")
    private String readerAddress;

    @ManyToMany(
            mappedBy = "readers"
    )
    private List<Book> bookList;
}
