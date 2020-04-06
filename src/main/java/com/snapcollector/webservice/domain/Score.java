package com.snapcollector.webservice.domain;

import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Account account;
    private String photographer;
    private String location;
    private Integer score;
}
