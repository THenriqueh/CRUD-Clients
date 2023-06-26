package com.dsclients.Clients.repositories;

import com.dsclients.Clients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRpository extends JpaRepository<Client, Long> {
}
