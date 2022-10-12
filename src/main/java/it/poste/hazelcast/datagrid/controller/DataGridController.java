/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.hazelcast.datagrid.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alex
 */
@RestController
@Slf4j
public class DataGridController {
    
    @GetMapping("/echo")
    public String get(@RequestParam(value = "message") String message) {       
        log.info("calling rest controller for echo: message = \"{}\"",message);
        return message;
    }
    
}
