package com.dsclients.Clients.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter private String cpf;
    @Setter private String name;
    @Setter private Double income;
    @Setter private Instant birthDate;
    @Setter private Integer children;


}
