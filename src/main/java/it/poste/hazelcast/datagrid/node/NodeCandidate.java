/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.hazelcast.datagrid.node;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.leader.Context;
import org.springframework.integration.leader.DefaultCandidate;

/**
 *
 * @author alex
 */
@Slf4j
public class NodeCandidate extends DefaultCandidate {        
    
    
    public NodeCandidate(String nodeId, String role) {
        super(nodeId, role);    
    }
    
    @Override
    public void onGranted(Context ctx) {
        super.onGranted(ctx);
        log.info("Leader granted to: {}",ctx.toString());
    }

    @Override
    public void onRevoked(Context ctx) {
        super.onRevoked(ctx);
        log.info("Leader revoked to: {}",ctx.toString());        
    }      
    
}
