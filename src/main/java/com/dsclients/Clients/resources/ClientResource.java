package com.dsclients.Clients.resources;

import com.dsclients.Clients.dto.ClientDto;
import com.dsclients.Clients.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "linesPage", defaultValue = "12") Integer linesPage,
                                                   @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                   @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ClientDto> list = service.findAll(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        ClientDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);

    }

    @PostMapping
    public ResponseEntity<ClientDto> insert(@RequestBody ClientDto clientDto) {
        ClientDto newDto = service.insert(clientDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> update (@PathVariable Long id, @RequestBody ClientDto clientDto) {
        clientDto = service.update(id, clientDto);
        return ResponseEntity.ok().body(clientDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>delete (@PathVariable  Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}