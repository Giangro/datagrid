/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.hazelcast.datagrid.config;

import com.hazelcast.core.HazelcastInstance;
import it.poste.hazelcast.datagrid.node.NodeCandidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.hazelcast.leader.LeaderInitiator;
import org.springframework.integration.leader.Candidate;
import org.springframework.integration.leader.event.DefaultLeaderEventPublisher;
import org.springframework.integration.leader.event.LeaderEventPublisher;
import it.poste.hazelcast.datagrid.service.ScheduledJob;
import jakarta.annotation.PostConstruct;
//import javax.annotation.PostConstruct;

/**
 *
 * @author alex
 */
@Configuration
@EnableIntegration
@Slf4j
public class Config {

    private final String nodeId;
    private final String role;
    private final String distributedMapName;
    private final String cronJobExpr;

    public Config(@Value("${app.nodeId}") String nodeid,
            @Value("${app.role}") String role,
            @Value("${app.mapName}") String map,
            @Value("${app.cronJobExpr}") String cronjobexpr
    ) {
        this.nodeId = nodeid;
        this.role = role;
        this.distributedMapName = map;
        this.cronJobExpr = cronjobexpr;
    }

    @Bean
    public String nodeId() {
        return nodeId;
    }

    @Bean
    public String role() {
        return role;
    }

    @Bean
    public String mapName() {
        return distributedMapName;
    }

    @Bean
    public String cronJobExpr() {
        return cronJobExpr;
    }
    
    @Bean
    public LeaderEventPublisher leaderEventPublisher() {
        return new DefaultLeaderEventPublisher();
    }
    
    @Bean
    public Candidate nodeCandidate(ScheduledJob scheduledJob, String nodeId, String role) {
        final NodeCandidate candidate
                = new NodeCandidate(scheduledJob, nodeId, role);
        return candidate;
    }

    @Bean
    public LeaderInitiator initiator(HazelcastInstance hazelcastInstance, 
            Candidate nodeCandidate,
            LeaderEventPublisher leaderEventPublisher) {
        final LeaderInitiator leaderInitiator
                = new LeaderInitiator(hazelcastInstance, nodeCandidate);        
        leaderInitiator.setLeaderEventPublisher(leaderEventPublisher);
        return leaderInitiator;
    }

    @PostConstruct
    private void postConfiguration() {
        log.info("Node Id \"{}\" has been configured. Map Name \"{}\"", nodeId, distributedMapName);
    }

}
