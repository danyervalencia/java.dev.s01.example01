package com.java.dev.s01.example01.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "personas")
@Setter @Getter
@NoArgsConstructor
//public class Persona implements Serializable {
public class Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pers_id")
    private int persId;

    @Column(name = "pers_paternal")
    private String persPaternal;

    @Column(name = "pers_maternal")
    private String persMaternal;

    @Column(name = "pers_name")
    private String persName;
}