package com.dsclients.Clients.dto;


import com.dsclients.Clients.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;
    @Setter
    private String cpf;
    @Setter
    private String name;
    @Setter
    private Double income;
    @Setter
    private Instant birthDate;
    @Setter
    private Integer children;

    public ClientDto(Client entity) {
        id = entity.getId();
        cpf = entity.getCpf();
        name = entity.getName();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }


}
