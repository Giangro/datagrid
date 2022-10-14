/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.hazelcast.datagrid.controller;

import it.poste.hazelcast.datagrid.service.DistributedStore;
import java.util.concurrent.ConcurrentMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        log.info("calling rest controller for echo: message = \"{}\"",message);
        return message;
    }
    
    @PostMapping("/put")
    public String put(@RequestParam(value = "key") String key, 
            @RequestParam(value = "value") String value) {
        this.distributedStore.put(key, value);
        log.info("calling rest controller for put: key = \"{}\":value = \"{}\"",key,value);
        return key+"="+value;
    }

    @GetMapping("/get")
    public String get(@RequestParam(value = "key") String key) {
        String value = this.distributedStore.get(key);
        log.info("calling rest controller for get: key = \"{}\":value = \"{}\"",key,value);
        return key+"="+value;
    }
    
}
