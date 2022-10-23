/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.hazelcast.datagrid.node;

import it.poste.hazelcast.datagrid.service.ScheduledJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.leader.Context;
import org.springframework.integration.leader.DefaultCandidate;

/**
 *
 * @author alex
 */
@Slf4j
public class NodeCandidate extends DefaultCandidate {        
    
    private final ScheduledJob scheduledJob;
    
    public NodeCandidate(ScheduledJob scheduledJob, String nodeId, String role) {
        super(nodeId, role);
        this.scheduledJob = scheduledJob;
    }
    
    @Override
    public void onGranted(Context ctx) {
        super.onGranted(ctx);
        log.info("Leader granted to: {}",ctx.toString());
        scheduledJob.start();
    }

    @Override
    public void onRevoked(Context ctx) {
        super.onRevoked(ctx);
        log.info("Leader revoked to: {}",ctx.toString());        
        scheduledJob.stop();
    }      
    
}
