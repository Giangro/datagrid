/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.hazelcast.datagrid.controller;

import it.poste.hazelcast.datagrid.model.Storable;
import it.poste.hazelcast.datagrid.service.DistributedStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alex
 */
@RestController
@Slf4j
public class DataGridController {

    private final DistributedStore distributedStore;

    public DataGridController(DistributedStore ds) {
        this.distributedStore = ds;
    }

    @GetMapping("/echo")
    public String echo(@RequestParam(value = "message") String message) {
        log.debug("calling rest controller for echo: message = \"{}\"", message);
        return message;
    }

    @PostMapping("/put")
    public Storable put(@RequestBody Storable storable) {        
        this.distributedStore.put(storable);
        log.debug("calling rest controller for put: key = \"{}\":value = \"{}\""
                , storable.getKey()
                , storable.getValue());
        return storable;
    }

    @GetMapping("/get/{key}")
    public Storable get(@PathVariable String key) {
        Storable storable = this.distributedStore.get(key);
        log.debug("calling rest controller for get: key = \"{}\":value = \"{}\""
                , storable.getKey()
                , storable.getValue());
        return storable;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            NullPointerException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

}

