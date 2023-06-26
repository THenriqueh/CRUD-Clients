package com.dsclients.Clients.services;

import com.dsclients.Clients.dto.ClientDto;
import com.dsclients.Clients.entities.Client;
import com.dsclients.Clients.repositories.ClientRpository;
import com.dsclients.Clients.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRpository repository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(PageRequest pageRequest){
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(ClientDto::new);
    }
    @Transactional(readOnly = true)
    public ClientDto findById(Long id){
        Optional<Client> optionalClient = repository.findById(id);
        Client entity = optionalClient.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
        return new ClientDto(entity);
    }

    @Transactional
    public ClientDto insert(ClientDto clientDto) {
        Client entity = new Client();
        copyDtoToEntity(clientDto,entity);
        entity = repository.save(entity);
        return new ClientDto(entity);

    }

    @Transactional
    public ClientDto update(Long id, ClientDto clientDto) {
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(clientDto, entity);
            entity = repository.save(entity);
            return new ClientDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id '" + id + "' não encontrado");

        }

    }
    private void copyDtoToEntity(ClientDto clientDto, Client entity) {
        entity.setCpf(clientDto.getCpf());
        entity.setName(clientDto.getName());
        entity.setIncome(clientDto.getIncome());
        entity.setBirthDate(clientDto.getBirthDate());
        entity.setChildren(clientDto.getChildren());


    }

    public void delete(Long id) {
        try{
         repository.deleteById(id);
    }catch (EmptyResultDataAccessException e){
        throw new ResourceNotFoundException("Id '" + id + "' não encontrado");
    }
}}
